package ru.appline.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.base.BaseTest;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SomeTest extends BaseTest {

    @Test
    public void test() throws InterruptedException {

        Map<String, Integer> prices = new HashMap<>();

        //ввожу поисковой запрос
        String searchArea = "//input[@type = 'search' and @placeholder = 'Поиск по сайту']";
        WebElement searchAreaElement = driver.findElement(By.xpath(searchArea));
        searchAreaElement.click();
        searchAreaElement.sendKeys("playstation");

        //клик по нужному элементу
        String itemSearchNeeded = "//a[text() = ' 4 slim']";
        WebElement itemSearchElement = driver.findElement(By.xpath(itemSearchNeeded));
        itemSearchElement.click();

        //на открывшейся странице каталога клик по нужному элементу
        String neededItem = "//div//a[contains(text(),'PlayStation 4 Slim Black')]";
        WebElement neededItemElement = driver.findElement(By.xpath(neededItem));
        neededItemElement.click();

        //беру цену товара и заполняю ей мапу [товар = цена]
        String itemPrice = "//div//span[@class='product-card-price__current']";
        WebElement itemPriceElement = driver.findElement(By.xpath(itemPrice));
        Thread.sleep(1000);
        Integer previousPrice = getPriceInInteger(itemPriceElement);
        prices.put("itemPrice", getPriceInInteger(itemPriceElement));

        System.out.println(prices.entrySet());

        //выбираю гарантию в 2 года и записываю цену гарантии в мапу
        String warrantyButton = "//select[@class='form-control']//option[text() = '2 года']";
        WebElement warrantyButtonElement = driver.findElement(By.xpath(warrantyButton));
        warrantyButtonElement.click();
        Integer priceWithWarranty = getPriceInInteger(itemPriceElement);
        if (!previousPrice.equals(priceWithWarranty)) {
            prices.put("warrantyPrice", (priceWithWarranty - previousPrice));
        }
        System.out.println(prices.entrySet());

        //добавляю товар в корзину кнопкой 'купить'
        String buyButton = "//div[@class='primary-btn']//button[text() = 'Купить']";
        WebElement buyButtonElement = driver.findElement(By.xpath(buyButton));
        buyButtonElement.click();

        //ищу в поиске Detroit
        WebElement searchElement = driver.findElement(By.xpath(searchArea));
        searchElement.click();
        searchElement.sendKeys("Detroit");

        //открываю страницу с Detroit
        String neededGame = "//div/a[text() = ' become human']//span[text() = 'detroit']";
        WebElement neededGameElement = driver.findElement(By.xpath(neededGame));
        neededGameElement.click();

        //записываю в мапу стоимость Detroit
        WebElement gamePriceElement = driver.findElement(By.xpath(itemPrice));
        Thread.sleep(1000);
        prices.put("gamePrice", getPriceInInteger(gamePriceElement));
        System.out.println(prices.entrySet());

        //добавляю Detroit в корзину
        WebElement buyGameElement = driver.findElement(By.xpath(buyButton));
        buyGameElement.click();
        Thread.sleep(2000);

        //проверяю сумму товаров в мапе и сумму в корзине сайта
        String sumCartPrice = "//a//span[@class='cart-link__price']";
        WebElement sumCartPriceElement = driver.findElement(By.xpath(sumCartPrice));
        Assert.assertTrue(getPriceInInteger(sumCartPriceElement).equals(getSumCartPrice(prices)));

        //перехожу в корзину
        String cartButton = "//div[@class='buttons']//a[@class='ui-link cart-link']";
        WebElement cartButtonElement = driver.findElement(By.xpath(cartButton));
        cartButtonElement.click();
        Thread.sleep(2000);

        //проверяю наличие страховки на 2 года
        String checkWarranty = "//span[@class='base-ui-radio-button__icon base-ui-radio-button__icon_checked']";
        WebElement checkWarrantyElement = driver.findElement(By.xpath(checkWarranty));
        Assert.assertTrue(checkWarrantyElement.getText().contains("24"));

        //проверяю цену каждого товара и сумму
        List<WebElement> cartPriceList = driver.findElements(By.xpath("//span[@class='price__current']"));
        Assert.assertTrue(checkPriceMapInCartList(prices, cartPriceList));

        //удаляю Detroit из корзины
        String dellFromCart = "//div//a[contains(text(), 'Detroit')]/../.././div[@class='menu-product menu-product_position-absolute']//button[text() = 'Удалить']";
        WebElement dellFromCartElement = driver.findElement(By.xpath(dellFromCart));
        dellFromCartElement.click();
        prices.remove("gamePrice");
        System.out.println(prices.entrySet());
        Thread.sleep(4000);

        //проверка, что Detroit удален из корзины и сумма уменьшилась на его стоимость
        Assert.assertTrue(checkThatItemDeleted("Detroit"));


    }

    //метод приводит цену товара к инту
    public static Integer getPriceInInteger(WebElement elem) {
        if (elem.getText().contains(" ₽")) {
            String[] priceArr = elem.getText().substring(0, elem.getText().length() - 1).trim().split(" ");
            return Integer.parseInt(priceArr[0] + priceArr[1]);
        } else {
            String[] priceArr = elem.getText().trim().split(" ");
            return Integer.parseInt(priceArr[0] + priceArr[1]);
        }
    }

    //метод возвращает сумму мапы с учетом "гарантий"
    public static Integer getSumCartPrice(Map<String, Integer> cart) {
        Integer cartSum = 0;
        for (Integer prc : cart.values()) {
            cartSum += prc;
        }
        return cartSum;
    }

    //метод проверяет стоимость товаров в мапе и на странице, а также их сумму
    public static boolean checkPriceMapInCartList(Map<String, Integer> priceMap, List<WebElement> elemList) {
        boolean isEquals = false;
        int sumWithoutWarranty = 0;
        int count = 0;
        int countItemsWithoutWarranty = 0;

        //перебераю все ключи прйс-мапы
        for (String keyPrice : priceMap.keySet()) {
            //если ключ не является "гарантией товара"
            if (!keyPrice.contains("warranty")) {
                countItemsWithoutWarranty++;
                //сравниваю его с веб-элементами - ценами в корзине
                for (WebElement element : elemList) {
                    //если стоимость элемента мапы равна цене веб-элемента count++
                    if (getPriceInInteger(element).equals(priceMap.get(keyPrice))) {
                        count++;
                        sumWithoutWarranty += getPriceInInteger(element);
                    }
                }
            }
        }

        for(WebElement element : elemList){
            if(getPriceInInteger(element).equals(sumWithoutWarranty))
                isEquals = true;
        }
        return isEquals && count == countItemsWithoutWarranty;

    }

    //метод, проверяющий удален ли товар из списка в корзине
    public static boolean checkThatItemDeleted(String item){
        boolean check = true;
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='cart-items__product-name']//a"));
        for(WebElement element: elementList){
            System.out.println(element.getText());
            if (element.getText().contains(item))
                check = false;
        }
        return check;
    }
}
