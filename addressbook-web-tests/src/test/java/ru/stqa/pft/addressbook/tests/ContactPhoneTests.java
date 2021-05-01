package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
//тест для сравнения номеров на странице со всеми контактами и на странице редактирования контакста
public class ContactPhoneTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        app.goTo().homePage();
        if (app.contact().contactAll().size() == 0) {  //создание контакта, если его не было
            app.goTo().goToContactCreation();
            app.contact().create(new ContactData()
                    .withFirstname("firstName")
                    .withLastname("lastName")
                    .withAddress("address")
                    .withHomephone("55555")
                    .withMobilephone("79201111111")
                    .withEmail("email@address.com")
                 //   .withGroup("test")
            , true);
            app.goTo().homePage();
        }
    }

    @Test()
    public void  testContactPhone(){
        app.goTo().homePage();
        ContactData contact = app.contact().contactAll().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

    }

    private String mergePhones(ContactData contact) {
        return  Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));

    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

}
