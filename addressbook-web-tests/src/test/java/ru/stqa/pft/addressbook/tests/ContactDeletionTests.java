package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){  //создание контакта, если его не было
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
    public void testContactDeletion(){

        List<contactData> before = app.contact().list(); //подсчет количества групп до создания
        app.contact().selectContact(before.size() - 1);
        app.contact().deleteContact();
        app.goTo().acceptAlert();
        app.goTo().homePage();
        List<contactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1); //удалили контакт из списка и проверили
        Assert.assertEquals(before,after);
    }
}
