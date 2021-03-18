package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.contactData;

public class ContactCreationTests extends TestBase{



  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().goToContactCreation();
    app.getContactHelper().fillContactForm(new contactData("firstName", "middleName", "lastName", "address", "123456", "email@address.com"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

  }


}
