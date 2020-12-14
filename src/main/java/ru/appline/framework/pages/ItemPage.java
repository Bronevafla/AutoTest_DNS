package ru.appline.framework.pages;

import com.sun.source.tree.UsesTree;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.appline.framework.Product.Product;

import java.util.List;

import static ru.appline.framework.Product.Product.*;

public class ItemPage extends BasePage {

//    private Product product;

//    public Product getProduct() {
//        if (this.product == null) {
//            this.product = new Product();
//        }
//        return product;
//    }

    @FindBy(xpath = "//div[@class='product-card-price__current-wrap']//span[1]")
    private WebElement xpathPriceItem;

    @FindBy(xpath = "//div//h1")
    private WebElement xPathNameItem;

    @FindBy(xpath = "//select[@class='form-control']//option")
    private List<WebElement> xPathWarranty;

    @FindBy(xpath = "//div[@class='primary-btn']//button")
    private WebElement buyButton;

    @FindBy(xpath = "//input[@type = 'search' and @placeholder = 'Поиск по сайту']")
    private WebElement searchAreaElement;

    @FindBy(xpath = "class='cart-link__price'")
    private WebElement itemPageCartSumPrice;

    @FindBy(xpath = "//div[@class='cart-summary__value']")
    private WebElement altItemPageCartSum;

    @FindBy(xpath = "//a[@class='ui-link cart-link']")
    private WebElement cartPageLinkXpath;

    public ItemPage getItemPrice() {
        new Product(xPathNameItem.getText(), priceToInteger(xpathPriceItem), "---");
        return this;
    }

    public ItemPage picWarranty(String productName, String warranty) {
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                for (WebElement element : xPathWarranty) {
                    if (element.getText().equals(warranty)) {
                        product.setWarranty(warranty);
                        element.click();
                    }
                }
            }
        }
        return this;
    }

    public ItemPage getNewPrice(String productName) {
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                product.setPrice(priceToInteger(xpathPriceItem));
            }
        }
        return this;
    }

    public ItemPage buyButtonClick() {
        buyButton.click();
        return this;
    }

    public ItemPage searchInCatalog(String value) {
        searchAreaElement.click();
        searchAreaElement.sendKeys(value);
        searchAreaElement.sendKeys(Keys.ENTER);

        return this;
    }

    public ItemPage checkItemPageCart() {
        Integer productSumPrice = 0;

        for (Product product : productList) {
            productSumPrice += product.getPrice();
        }

        waitUtilElementToBeVisible(altItemPageCartSum);
        Assert.assertEquals("Цена корзины не совпадает", priceToInteger(altItemPageCartSum), productSumPrice);

        return this;
    }

    public CartPage openCartPage(){
        cartPageLinkXpath.click();
        return app.getCartPage();
    }

}
