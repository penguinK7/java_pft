package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

      public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage()
    {
        if(isElementPresent(By.tagName("h1"))     //проверка что уже находишься на странице с созданием групп
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))){
         return;
        }

          click(By.linkText("groups"));
    }

    public void acceptAlert(){
        wd.switchTo().alert().accept();
    }
    public void homePage() {
          if(isElementPresent(By.id("maintable"))){  //проверка что уже находишься на нужной странице
              return;
          }

        click(By.linkText("home"));
    }

    public void goToContactCreation() {
        click(By.linkText("add new"));
    }

}
