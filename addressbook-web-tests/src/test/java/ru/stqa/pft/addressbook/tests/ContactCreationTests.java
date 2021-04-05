package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.contactData;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{



  @Test
  public void testContactCreation() {
    List<contactData> before = app.getContactHelper().getContactList(); //подсчет количества групп до создания
    app.getNavigationHelper().goToContactCreation();

    contactData contact = new contactData("firstName",

            "lastName",
            "address",
            "123456",
            "email@address.com",
            "test");
    app.getContactHelper().fillContactForm(contact, true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();

    List<contactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size(), after.size() - 1);


    before.add(contact);
    Comparator<? super contactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}