package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.contactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {


    @Test()
    public void testContactCreation() {
        Contacts before = app.contact().contactAll(); //подсчет количества групп до создания
        app.goTo().goToContactCreation();

        contactData contact = new contactData()
                .withFirstname("firstName")
                .withLastname("lastName")
                .withAddress("address")
                .withMobile("123456")
                .withEmail("email@address.com")
                .withGroup("test");
        app.contact().fillContactForm(contact, true);
        app.contact().submitContactCreation();
        app.goTo().homePage();

        Contacts after = app.contact().contactAll();
        assertEquals(before.size(), after.size() - 1);

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }
}