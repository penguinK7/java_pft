package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {  //создание контакта, если его не было
           app.goTo().goToContactCreation();
            app.contact().create(new ContactData()
                    .withFirstname("firstName")
                    .withLastname("lastName")
                    .withAddress("address")
                    .withMobilephone("79201111111")
                    .withEmail("email@address.com")
                  //  .withGroup("test")
            , true);
            app.goTo().homePage();
        }
    }

    @Test()
    public void testContactDeletion() {

       Contacts before = app.db().contacts();

        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().acceptAlert();
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()-1));
        Contacts after = app.db().contacts();




        assertThat(after, equalTo(before.without(deletedContact)));

        verifyContactListInUI();
    }




}
