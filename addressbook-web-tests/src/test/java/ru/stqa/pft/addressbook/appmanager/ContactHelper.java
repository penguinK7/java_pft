package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


import java.io.IOException;
import java.util.List;


public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectContact(int i) {
        click(By.name("selected[]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {

        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());

        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail());
        type(By.name("email3"), contactData.getEmail());
       attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }


    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        contactCache = null;

    }


    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void modifyContact(ContactData contact) {
        editContactById(contact.getId());
        fillContactForm(contact, false);
        updateContact();
        contactCache = null;
        homePage();
    }

    public void updateContact() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void create(ContactData contact, boolean b) throws IOException {
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;

    }

    public void homePage() {
        if (isElementPresent(By.id("maintable"))) {  //проверка что уже находишься на нужной странице
            return;
        }

        click(By.linkText("home"));
    }


    public void goToEditContact(int i) {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void goToEditContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }
    public void editContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public void selectGroupToAdd(GroupData groupName) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName.getName());
    }

    public void addInGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        selectGroupToAdd(group);
        add();
        goToGoup();
        contactCache = null;
    }
    public void add(){click(By.xpath("(//input[@name='add'])"));}
    public void groupFilter(GroupData group){
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());;
    }

    public void goToGoup(){wd.findElement(By.partialLinkText("group page")).click();}
    private Contacts contactCache = null;


    public Contacts contactAll() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute(("value")));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            contactCache.add(new ContactData().withId(id)
                    .withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));

        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname)
                .withLastname(lastname).withAddress(address)
                .withHomephone(homephone).withMobilephone(mobilephone).withWorkphone(workphone)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    private void initContactModificationById(int id) {
        WebElement chekbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = chekbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        //  wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }



    public void selectContactCheckbox(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactCheckboxById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void addContactToGroup(ContactData contactData, GroupData groupData) {

        selectContactCheckboxById(contactData.getId());
        selectGroupFromListToAdd(groupData.getId());
        addToGroupButton();
        goToGroupPageAfterAddingRemovingContact();
        contactCache = null;
    }

    private void addToGroupButton() {
        wd.findElement(By.name("add")).click();
    }

    public void goToGroupPageAfterAddingRemovingContact() {
        wd.findElement(By.partialLinkText("group page")).click();
        //wd.findElement(By.cssSelector(String.format("a[href='./?group=%s']", id))).click();
    }

    public void selectGroupFromListToAdd(int groupId) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(groupId));
    }
    public void removeContactFromGroup(ContactData contact, GroupData groupData) {
        selectContactCheckboxById(contact.getId());
        removeFromGroupButton();
        goToGroupPageAfterAddingRemovingContact();
        contactCache = null;
    }

    public void selectGroupFromList(int groupId) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(groupId));
    }
    public void removeFromGroupButton() {
        wd.findElement(By.name("remove")).click();
    }
}
