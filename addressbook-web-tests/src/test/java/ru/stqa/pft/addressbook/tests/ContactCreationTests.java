package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{



  @Test ()
  public void testContactCreation() {
    List<contactData> before = app.contact().list(); //подсчет количества групп до создания
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

    List<contactData> after = app.contact().list();
    Assert.assertEquals(before.size(), after.size() - 1);


    before.add(contact);
    Comparator<? super contactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}