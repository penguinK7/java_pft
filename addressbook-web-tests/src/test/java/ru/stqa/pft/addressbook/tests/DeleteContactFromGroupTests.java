package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactFromGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditionsContacts() throws IOException {
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
                    // .withGroup("test")
                    , true);
            app.goTo().homePage();
        }
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {  //создание группы, если ее не было
            app.group().create(new GroupData().withName("test1"));
        }
    }

@Test
    public void testContactDeleteFromGroup(){
    ContactData contact = selectContact();
    GroupData groupContactToRemovedFrom = selectGroup(contact);
    Groups before = contact.getGroups();
    app.goTo().homePage();
    app.contact().selectGroupFromList(groupContactToRemovedFrom.getId());
    app.contact().removeContactFromGroup(contact, groupContactToRemovedFrom);

    ContactData contactsAfter = selectContactById(contact);
    Groups after = contactsAfter.getGroups();
    assertThat(after, equalTo(before.without(groupContactToRemovedFrom)));
}

    private ContactData selectContactById(ContactData contact) {
        Contacts contactsById = app.db().contacts();
        return contactsById.iterator().next().withId(contact.getId());
    }


    private GroupData selectGroup(ContactData removeContact) {

        ContactData contact = selectContactById(removeContact);
        Groups removedContact = contact.getGroups();
        return removedContact.iterator().next();

    }

    private ContactData selectContact() {
        Contacts allContacts = app.db().contacts();
        for (ContactData contact : allContacts) {
            if (contact.getGroups().size() > 0) {
                return contact;
            }
        }

        ContactData addContact = app.db().contacts().iterator().next();
        app.contact().addContactToGroup(addContact, app.db().groups().iterator().next());
        return addContact;
    }
}