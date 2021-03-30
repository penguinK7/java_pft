package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()){  //создание контакта, если его не было
            app.getNavigationHelper().goToContactCreation();
            app.getContactHelper().createContact(new contactData("firstName",
                    "middleName",
                    "lastName",
                    "address",
                    "123456",
                    "email@address.com",
                    "test"));
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().acceptAlert();
    }
}
