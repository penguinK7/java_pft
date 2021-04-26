package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import ru.stqa.pft.addressbook.model.Contacts;



public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {  //создание контакта, если его не было
            app.goTo().goToContactCreation();
            app.contact().create(new ContactData()
                    .withFirstname("firstName")
                    .withLastname("lastName")
                    .withAddress("address")
                    .withMobilephone("79201111111")
                    .withEmail("email@address.com")
                    .withGroup("test"));
            app.goTo().homePage();
        }
    }

    @Test()
    public void ContactModification() {

        Contacts before = app.db().contacts(); //подсчет количества контактов до создания

        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("firstName")
                .withLastname("lastName")
                .withAddress("address")
                .withMobilephone("79201111111")
                .withEmail("email@address.com");

        app.contact().modifyContact(contact);

        assertThat(app.contact().count(), equalTo(before.size()));

       Contacts after = app.db().contacts();


        assertThat(after,
                equalTo(before.without(modifiedContact).withAdded(contact)));

    }


}
