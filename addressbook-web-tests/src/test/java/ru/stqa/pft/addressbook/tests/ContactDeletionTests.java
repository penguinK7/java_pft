package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.List;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()){  //создание контакта, если его не было
            app.getNavigationHelper().goToContactCreation();
            app.getContactHelper().createContact(new contactData("firstName",

                    "lastName",
                    "address",
                    "123456",
                    "email@address.com",
                    "test"));
            app.getNavigationHelper().goToHomePage();
        }
        List<contactData> before = app.getContactHelper().getContactList(); //подсчет количества групп до создания
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().acceptAlert();
        app.getNavigationHelper().goToHomePage();
        List<contactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1); //удалили контакт из списка и проверили
        Assert.assertEquals(before,after);
    }
}
