package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;
import java.util.Set;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0){  //создание контакта, если его не было
            app.goTo().goToContactCreation();
            app.contact().create(new contactData()
                    .withFirstname("firstName")
                    .withLastname("lastName")
                    .withAddress("address")
                    .withMobile("123456")
                    .withEmail("email@address.com")
                    .withGroup("test"));
            app.goTo().homePage();
        }
    }

    @Test()
    public void ContactModification() {

        Set<contactData> before = app.contact().all(); //подсчет количества контактов до создания
        int index = before.size()-1;
        contactData modifiedContact = before.iterator().next();
        contactData contact = new contactData()
                .withId(modifiedContact.getId())
                .withFirstname("firstName")
                .withLastname("lastName")
                .withAddress("address")
                .withMobile("123456")
                .withEmail("email@address.com");
        app.contact().modifyContact(index, contact);

        Set<contactData> after = app.contact().all();
        Assert.assertEquals(before.size(), after.size());

        before.remove(modifiedContact);
        before.add(contact);
      // Comparator<? super contactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
     // before.sort(byId);
      // after.sort(byId);
        Assert.assertEquals(before, after);

    }




}
