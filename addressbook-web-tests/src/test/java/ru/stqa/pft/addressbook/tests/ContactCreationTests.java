package ru.stqa.pft.addressbook.tests;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @DataProvider
//заполнение данных для создания группы из файла xml
    public Iterator<Object[]> validContactsFromXml() throws IOException {
       try( BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
           String xml = "";
           String line = reader.readLine();
           while (line != null) {
               xml += line;
               line = reader.readLine();
           }
           XStream xstream = new XStream();
           xstream.processAnnotations(ContactData.class);
           List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
           return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
       }

    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }

    }
   // dataProvider = "validContactsFromJson"
    @Test()
    public void testContactCreation() throws Exception {

        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withFirstname("testName")
                .withLastname("testSurname").withPhoto(photo).inGroup(groups.iterator().next());

        app.goTo().homePage();
        Contacts before = app.db().contacts();
        app.goTo().goToContactCreation();
        app.contact().create(contact, true);
        app.goTo().homePage();
       assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();


        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

        verifyContactListInUI();

    }

    @Test(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());


    }
}