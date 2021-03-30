package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.contactData;

public class ContactHelper extends HelperBase{


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillContactForm(contactData contactData, boolean creation) {

        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("middlename"),contactData.getMiddlename());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("address"),contactData.getAddress());
        type(By.name("mobile"),contactData.getMobile());
        type(By.name("email"),contactData.getEmail());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup()); //выпадающий список с группами должен быть
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group"))); //выпадающий список с группами должен отсутствовать
        }


    }

    public void goToContactCreation() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }


    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));

            }
    public void updateContact(){
        click(By.xpath("(//input[@name='update'])[2]"));
    }


    /* public void createContact(ContactData contact) {
        fillContactData(contact, true);
        submitContactCreation();
    }*/
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

}
