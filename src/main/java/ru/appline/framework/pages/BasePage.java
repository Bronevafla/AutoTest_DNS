package ru.appline.framework.pages;

import static ru.appline.framework.managers.DriverManager.getDriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.ManagerPages;

import java.util.List;


public class BasePage {

    protected ManagerPages app = ManagerPages.getManagerPages();

    public BasePage(){
        PageFactory.initElements(getDriver(), this);
    }

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    public WebDriverWait getWait() {
        return wait;
    }

    public Integer priceToInteger(WebElement price){
        waitUtilElementToBeVisible(price);
        return Integer.parseInt(price.getText().replaceAll("[^0-9]", ""));
    }




//    protected static void scrollToElementJs(WebElement element) {
//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
//    }
//
    public void waitUtilElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
//
    protected void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
//
//    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();
//
}
