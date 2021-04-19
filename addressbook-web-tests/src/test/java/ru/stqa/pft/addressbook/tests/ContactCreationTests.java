package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


    @Test()
    public void testContactCreation() {
       Contacts before = app.contact().contactAll();


        app.goTo().goToContactCreation();

        ContactData contact = new ContactData()
                .withFirstname("firstName")
                .withLastname("lastName")
                .withAddress("address")
               // .withHomephone("55555555")
                .withMobilephone("79201111111")
               // .withWorkphone("333")
                .withEmail("email@address.com")
                .withGroup("test");
        app.contact().fillContactForm(contact, true);
        app.contact().submitContactCreation();
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().contactAll();



        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }
}