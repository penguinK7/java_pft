package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{
    @Test
    public void ContactModification() {
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
        List<contactData> before = app.getContactHelper().getContactList(); //подсчет количества контактов до создания
        app.getNavigationHelper().goToEditContact(before.size() - 1);
        contactData contact = new contactData("firstName",
                "lastName",
                "address",
                "123456",
                "email@address.com",
                null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        List<contactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super contactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
