package ru.appline.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CatalogPage extends BasePage {
    @FindBy(xpath = "//div[@class='product-info__title-link']/a")
    private List<WebElement> searchItemList;

    public ItemPage picNeededItem(String value) {
        for (WebElement element : searchItemList) {
            if (element.getText().contains(value)) {
                element.click();
                break;
            }
        }
        return app.getItemPage();
    }
}
