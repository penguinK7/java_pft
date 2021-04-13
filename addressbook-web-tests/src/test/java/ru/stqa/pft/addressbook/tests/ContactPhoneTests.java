package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

public class ContactPhoneTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().contactAll().size() == 0) {  //создание контакта, если его не было
            app.goTo().goToContactCreation();
            app.contact().create(new contactData()
                    .withFirstname("firstName")
                    .withLastname("lastName")
                    .withAddress("address")
                    .withHomephone("55555")
                    .withMobilephone("79201111111")
                    .withEmail("email@address.com")
                    .withGroup("test"));
            app.goTo().homePage();
        }
    }

    @Test(enabled = false)
    public void  testContactPhone(){
        app.goTo().homePage();
        contactData contact = app.contact().contactAll().iterator().next();
        contactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
