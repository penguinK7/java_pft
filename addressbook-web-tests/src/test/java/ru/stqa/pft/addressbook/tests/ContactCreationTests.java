package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.contactData;

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

    /*
    исопльзование цикла для поиска максимального id:
    int max = 0;
    for (ContactData c : after){
      if (c.getId() > max){
        max = c.getId();
      }
    }
    */
    Comparator<? super contactData> byId = new Comparator<contactData>() {
      @Override
      public int compare(contactData o1, contactData o2) {
        return Integer.compare(o1.getId(), o2.getId());
      }
    };


    int max = after.stream().max(byId).get().getId();
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

  }
}