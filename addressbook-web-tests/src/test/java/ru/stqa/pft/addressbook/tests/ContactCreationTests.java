package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.contactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {


    @Test()
    public void testContactCreation() {
        Set<contactData> before = app.contact().all(); //подсчет количества групп до создания
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

        Set<contactData> after = app.contact().all();
        Assert.assertEquals(before.size(), after.size() - 1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

    }
}