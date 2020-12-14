package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.NewSessionPayload;
import org.openqa.selenium.support.FindBy;
import ru.appline.framework.Product.Product;

import java.nio.file.Watchable;
import java.util.List;

import static ru.appline.framework.Product.Product.productList;

public class CartPage extends BasePage {
    @FindBy(xpath = "//span[@class='base-ui-radio-button__icon base-ui-radio-button__icon_checked']")
    private WebElement getWarrantyXpath;

    @FindBy(xpath = "//a[@class='ui-link cart-link']")
    private WebElement getCartPriceWithoutWarrantyXpath;

    @FindBy(xpath = "//div[@class='cart-items__product-name']//a")
    private List<WebElement> elementsList;

    @FindBy(xpath = "//span[@class='group-tabs-menu__item group-tabs-menu__item_print-basket']//span")
    private WebElement returnItemsXpath;


    public CartPage checkWarranty(String productName, String warranty) {
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                Assert.assertEquals("гарантия товара выбрана не верно", warranty, product.getWarranty());
            }
        }
        return this;
    }

    public CartPage checkCartPageSum() throws InterruptedException {
        Integer productSumPrice = 0;
        waitUtilElementToBeClickable(getCartPriceWithoutWarrantyXpath);
        waitUtilElementToBeVisible(getCartPriceWithoutWarrantyXpath);

        Thread.sleep(3000);
        String sumPriceInString = priceToInteger(getCartPriceWithoutWarrantyXpath).toString();
        sumPriceInString = sumPriceInString.substring(0, sumPriceInString.length() - 1);
        Integer sumPriceInteger = Integer.parseInt(sumPriceInString);

        for (Product product : productList) {
            productSumPrice += product.getPrice();
        }

        Assert.assertEquals("Цена корзины не совпадает", sumPriceInteger, productSumPrice);
        return this;
    }

    public CartPage deleteElementFromCart(String name) {

        for (WebElement element : elementsList) {
            for (Product product : productList) {
                if (product.getName().equals(name) && product.getName().equals(element.getText())) {
                    WebElement deleteElement = element.findElement(By.xpath("../../..//button[@class='count-buttons__button count-buttons__button_minus']"));
                    deleteElement.click();

                    product.setPrice(0);
                }
            }
        }
        return this;
    }

    public CartPage addElementToCart(String name) throws InterruptedException {
        String newName = "";
        Integer newPrice = 0;
        String newWarranty = "";

        for (WebElement element : elementsList) {
            for (Product product : productList) {
                if (product.getName().equals(name) && product.getName().equals(element.getText())) {
                    WebElement addElement = element.findElement(By.xpath("../../..//button[@class='count-buttons__button count-buttons__button_plus']"));
                    addElement.click();

                    newName = product.getName();
                    newPrice = product.getPrice();
                    newWarranty = product.getWarranty();

                    Product product1 = new Product(newName, newPrice, newWarranty);

                    break;
                }
            }
        }
        Thread.sleep(3000);
        return this;
    }

    public CartPage returnElement() throws InterruptedException {
        returnItemsXpath.click();
        Thread.sleep(1000);
        for (WebElement element: elementsList){
            for (Product product: productList){
                if (product.getPrice() == 0 && element.getText().contains(product.getName())){
                    WebElement returnElement = element.findElement(By.xpath("./../../..//span[@class='price__current']"));
                    product.setPrice(priceToInteger(returnElement));
                }
            }
        }

        return this;
    }
}

