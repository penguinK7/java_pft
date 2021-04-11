package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().contactAll().size() == 0){  //создание контакта, если его не было
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

        Contacts before = app.contact().contactAll(); //подсчет количества контактов до создания
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

        Contacts after = app.contact().contactAll();

        assertEquals(before.size(), after.size());
        assertThat(after,
                equalTo(before.without(modifiedContact).withAdded(contact)));

    }




}
