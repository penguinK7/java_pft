package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddContactToGroupTests extends TestBase{
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
    public void testAddContactToGroup() throws IOException {
        ContactData addContact = selectContact();
        GroupData groupContactToBeAddedTo = selectGroup(addContact);
        Groups before = addContact.getGroups();
        app.goTo().homePage();
        app.contact().addContactToGroup(addContact, groupContactToBeAddedTo);
        app.goTo().homePage();
        ContactData addContactAfter = selectContactById(addContact);
        Groups after = addContactAfter.getGroups();
        assertThat(after, equalTo(before.withAdded(groupContactToBeAddedTo)));
    }

    private ContactData selectContactById(ContactData addContact) {
        Contacts contactsById = app.db().contacts();
        return contactsById.iterator().next().withId(addContact.getId());
    }

    private GroupData selectGroup(ContactData contact) {
        Groups allGroups = app.db().groups();
        Groups contactsInGroups = contact.getGroups();

        Collection<GroupData> contactGroups = new HashSet<>(contactsInGroups);
        Collection<GroupData> avaliableGroups = new HashSet<>(allGroups);
        avaliableGroups.removeAll(contactGroups);
        return avaliableGroups.iterator().next();
    }

    private ContactData selectContact() throws IOException {

        Contacts allContacts = app.db().contacts();
        Groups allGroups = app.db().groups();
        for (ContactData contact : allContacts) {
            if (contact.getGroups().size() < allGroups.size()) {
                return contact;
            }
        }
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("group created by test").withHeader("header for group from test"));
        return allContacts.iterator().next();
    }
}