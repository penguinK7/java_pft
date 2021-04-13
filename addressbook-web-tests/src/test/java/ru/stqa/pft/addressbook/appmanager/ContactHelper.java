package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.contactData;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase{


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }
    public void selectContact(int i){ click(By.name("selected[]"));}

    public void fillContactForm(contactData contactData, boolean creation) {

        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("address"),contactData.getAddress());
        type(By.name("mobile"),contactData.getMobilephone());
        type(By.name("email"),contactData.getEmail());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }



    }
    public void delete(contactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        contactCache = null;

    }
    private Contacts contactCache = null;
    public void deleteContact(){
        click(By.xpath("//input[@value='Delete']"));
    }
    public void modifyContact(int index, contactData contact) {
        goToEditContact(index);
        fillContactForm(contact, false);
        updateContact();
        homePage();
    }
    public void updateContact(){
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void create(contactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
    }
    public void homePage() {
        if(isElementPresent(By.id("maintable"))){  //проверка что уже находишься на нужной странице
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

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }
    public Contacts contactAll() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement e : elements) {
            String firstname = e.findElement(By.xpath(".//td[3]")).getText();
            String lastname = e.findElement(By.xpath("//td[2]")).getText();
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute(("id")));
            contacts.add(new contactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

    public contactData infoFromEditForm(contactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
       // String company = wd.findElement(By.name("company")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        wd.navigate().back();
        return new contactData().withId(contact.getId()).withFirstname(firstname)
                .withLastname(lastname).withAddress(address)
                .withHomephone(homephone).withMobilephone(mobilephone).withWorkphone(workphone)
                .withEmail(email);
    }

    private void initContactModificationById(int id){
        WebElement chekbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = chekbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

     //   wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }
}
