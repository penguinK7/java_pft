package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {  //создание контакта, если его не было
            app.goTo().goToContactCreation();
            app.contact().create(new contactData()
                    .withFirstname("firstName")
                    .withLastname("lastName")
                    .withAddress("address")
                    .withMobile("123456")
                    .withEmail("email@address.com")
                    .withGroup("test"));
            app.goTo().homePage();
        }
    }

    @Test()
    public void testContactDeletion() {

        Set<contactData> before = app.contact().all(); //подсчет количества групп до создания
        contactData deleteContact = before.iterator().next();
        int index = before.size()-1;
        app.contact().selectContact(index);
        app.contact().deleteSelectedContact();
        app.goTo().acceptAlert();
        app.goTo().homePage();
        Set<contactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deleteContact); //удалили контакт из списка и проверили
        Assert.assertEquals(before, after);
    }




}
