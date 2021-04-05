package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){  //создание контакта, если его не было
            app.goTo().goToContactCreation();
            app.contact().create(new contactData("firstName",

                    "lastName",
                    "address",
                    "123456",
                    "email@address.com",
                    "test"));
            app.goTo().homePage();
        }
    }

    @Test()
    public void ContactModification() {

        List<contactData> before = app.contact().list(); //подсчет количества контактов до создания
        int index = before.size()-1;
        contactData contact = new contactData("firstName",
                "lastName",
                "address",
                "123456",
                "email@address.com",
                null);
        app.contact().modifyContact(index, contact);

        List<contactData> after = app.contact().list();
        Assert.assertEquals(before.size(), after.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super contactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }




}
