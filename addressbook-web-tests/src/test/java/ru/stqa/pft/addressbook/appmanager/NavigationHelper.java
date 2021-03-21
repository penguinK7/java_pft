package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

      public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {

          click(By.linkText("groups"));
    }

    public void acceptAlert(){
        wd.switchTo().alert().accept();
    }
    public void goToHomePage() {
        click(By.linkText("home"));
    }
    public void goToEditContact() {
        click(By.xpath("(//img[@alt='Edit'])[2]"));
    }

}
