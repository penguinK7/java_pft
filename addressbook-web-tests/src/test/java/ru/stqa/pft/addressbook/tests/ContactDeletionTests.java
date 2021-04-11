package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().contactAll().size() == 0) {  //создание контакта, если его не было
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
    public void testContactDeletion() {

        Contacts before = app.contact().contactAll();
        contactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().acceptAlert();
        app.goTo().homePage();
        Contacts after = app.contact().contactAll();


        assertEquals(before.size(), after.size() + 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }




}
