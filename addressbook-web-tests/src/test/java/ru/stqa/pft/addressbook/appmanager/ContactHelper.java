package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.contactData;


import java.util.ArrayList;
import java.util.List;

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
        type(By.name("mobile"),contactData.getMobile());
        type(By.name("email"),contactData.getEmail());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }



    }

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
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<contactData> list() {
        List<contactData> contacts = new ArrayList<contactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement e : elements) {
            String firstname = e.findElement(By.xpath(".//td[3]")).getText();
            String lastname = e.findElement(By.xpath("//td[2]")).getText();
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute(("id")));
            contactData contact = new contactData(id, firstname, lastname, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }

}
