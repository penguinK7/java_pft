package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


    @Test()
    public void testContactCreation() {
       Contacts before = app.contact().contactAll();


        app.goTo().goToContactCreation();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData()
                .withFirstname("firstName")
                .withLastname("lastName")
                .withAddress("address")
               // .withHomephone("55555555")
                .withMobilephone("79201111111")
               // .withWorkphone("333")
                .withEmail("email@address.com")
                .withGroup("test")
                .withPhoto(photo);
        app.contact().fillContactForm(contact, true);
        app.contact().submitContactCreation();
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().contactAll();



        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }
    @Test(enabled = false)
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}