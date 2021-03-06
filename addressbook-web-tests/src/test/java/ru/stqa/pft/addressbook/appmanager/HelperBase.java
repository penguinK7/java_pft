package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class HelperBase {

    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {    //если текст в названии не равен нулю
            String existingText = wd.findElement(locator).getAttribute("value"); //если текст существует
            if (!text.equals(existingText)) {  //если текст не равен существующему
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }

        }
    }
    protected void attach(By locator, File file) {
        if (file != null) {    //если текст в названии не равен нулю
                wd.findElement(locator).sendKeys(file.getAbsolutePath());
            }

        }

  /*  private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }*/

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


    protected boolean isElementPresent(By locator) {   //метод на проверку наличия или отсутствия элементов
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
