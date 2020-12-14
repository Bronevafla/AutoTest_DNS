package ru.appline.framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {
    @FindBy(xpath = "//input[@type = 'search' and @placeholder = 'Поиск по сайту']")
    private WebElement searchAreaElement;


    public CatalogPage searchInCatalog(String value) {
        searchAreaElement.click();
        searchAreaElement.sendKeys(value);
        searchAreaElement.sendKeys(Keys.ENTER);

        return app.getCatalogPage();
    }



}
