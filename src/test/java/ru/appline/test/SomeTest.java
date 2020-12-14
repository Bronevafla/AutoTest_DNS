package ru.appline.test;

import org.junit.Test;
import ru.appline.base.BaseTest;
import ru.appline.framework.Product.Product;
import ru.appline.framework.pages.CatalogPage;
import ru.appline.framework.pages.ItemPage;
import ru.appline.framework.pages.StartPage;

import java.util.*;

import static ru.appline.framework.Product.Product.*;

public class SomeTest extends BaseTest {

    @Test
    public void test() throws InterruptedException {


        app.getStartPage()
                .searchInCatalog("playstation")
                .picNeededItem("PlayStation 4 Slim")
                .getItemPrice()
                .picWarranty("Игровая консоль PlayStation 4 Slim Black 1 TB + 3 игры", "2 года")
                .getNewPrice("Игровая консоль PlayStation 4 Slim Black 1 TB + 3 игры")
                .buyButtonClick()
                .searchInCatalog("Detroit")
                .getItemPrice()
                .buyButtonClick()
                //сверяю стоимость продуктов с корзиной
                .checkItemPageCart()
                //перехожу в корзину
                .openCartPage()
                //проверяю, что для приставки гарантия 2 года
                .checkWarranty("Игровая консоль PlayStation 4 Slim Black 1 TB + 3 игры", "2 года")
                //сверяю стоимость продуктов и корзины
                .checkCartPageSum()
                //удаляю элемент
                .deleteElementFromCart("Игра Detroit: Стать человеком (PS4)")
                //сверяю стоимость продуктов и корзины
                .checkCartPageSum()
                //добавляю еще две playstation
                .addElementToCart("Игровая консоль PlayStation 4 Slim Black 1 TB + 3 игры")
                .addElementToCart("Игровая консоль PlayStation 4 Slim Black 1 TB + 3 игры")
                //проверка суммы
                .checkCartPageSum()
                //возвращаю удаленный товар
                .returnElement()
                //проверка суммы
                .checkCartPageSum();

        for (Product product: productList){
            System.out.println(product);
        }






        //на открывшейся странице каталога клик по нужному элементу
//        String neededItem = "//div//a[contains(text(),'PlayStation 4 Slim Black')]";
//        WebElement neededItemElement = driver.findElement(By.xpath(neededItem));
//        neededItemElement.click();
//
//        //беру цену товара и заполняю ей мапу [товар = цена]
//        String itemPrice = "//div//span[@class='product-card-price__current']";
//        WebElement itemPriceElement = driver.findElement(By.xpath(itemPrice));
//        Thread.sleep(1000);
//        Integer previousPrice = getPriceInInteger(itemPriceElement);
//        prices.put("itemPrice", getPriceInInteger(itemPriceElement));
//
//        System.out.println(prices.entrySet());
//
//        //выбираю гарантию в 2 года и записываю цену гарантии в мапу
//        String warrantyButton = "//select[@class='form-control']//option[text() = '2 года']";
//        WebElement warrantyButtonElement = driver.findElement(By.xpath(warrantyButton));
//        warrantyButtonElement.click();
//        Integer priceWithWarranty = getPriceInInteger(itemPriceElement);
//        if (!previousPrice.equals(priceWithWarranty)) {
//            prices.put("warrantyPrice", (priceWithWarranty - previousPrice));
//        }
//        System.out.println(prices.entrySet());
//
//        //добавляю товар в корзину кнопкой 'купить'
//        String buyButton = "//div[@class='primary-btn']//button[text() = 'Купить']";
//        WebElement buyButtonElement = driver.findElement(By.xpath(buyButton));
//        buyButtonElement.click();
//
//        //ищу в поиске Detroit
//       // WebElement searchElement = driver.findElement(By.xpath(searchArea));
////        searchElement.click();
////        searchElement.sendKeys("Detroit");
//
//        //открываю страницу с Detroit
//        String neededGame = "//div/a[text() = ' become human']//span[text() = 'detroit']";
//        WebElement neededGameElement = driver.findElement(By.xpath(neededGame));
//        neededGameElement.click();
//
//        //записываю в мапу стоимость Detroit
//        WebElement gamePriceElement = driver.findElement(By.xpath(itemPrice));
//        Thread.sleep(1000);
//        prices.put("gamePrice", getPriceInInteger(gamePriceElement));
//        System.out.println(prices.entrySet());
//
//        //добавляю Detroit в корзину
//        WebElement buyGameElement = driver.findElement(By.xpath(buyButton));
//        buyGameElement.click();
//        Thread.sleep(2000);
//
//        //проверяю сумму товаров в мапе и сумму в корзине сайта
//        String sumCartPrice = "//a//span[@class='cart-link__price']";
//        WebElement sumCartPriceElement = driver.findElement(By.xpath(sumCartPrice));
//        Assert.assertTrue(getPriceInInteger(sumCartPriceElement).equals(getSumCartPrice(prices)));
//
//        //перехожу в корзину
//        String cartButton = "//div[@class='buttons']//a[@class='ui-link cart-link']";
//        WebElement cartButtonElement = driver.findElement(By.xpath(cartButton));
//        cartButtonElement.click();
//        Thread.sleep(2000);
//
//        //проверяю наличие страховки на 2 года
//        String checkWarranty = "//span[@class='base-ui-radio-button__icon base-ui-radio-button__icon_checked']";
//        WebElement checkWarrantyElement = driver.findElement(By.xpath(checkWarranty));
//        Assert.assertTrue(checkWarrantyElement.getText().contains("24"));
//
//        //проверяю цену каждого товара и сумму
//        List<WebElement> cartPriceList = driver.findElements(By.xpath("//span[@class='price__current']"));
//        Assert.assertTrue(checkPriceMapInCartList(prices, cartPriceList));
//
//        //удаляю Detroit из корзины и проверяю,что Detroit нет больше в корзине и что сумма уменьшилась на цену Detroit
//        Assert.assertTrue(deleteAndCheckThatItemRemove("Detroit"));
//        prices.remove("gamePrice");







    }


//    //метод возвращает сумму мапы с учетом "гарантий"
//    public static Integer getSumCartPrice(Map<String, Integer> cart) {
//        Integer cartSum = 0;
//        for (Integer prc : cart.values()) {
//            cartSum += prc;
//        }
//        return cartSum;
//    }
//
//    //метод проверяет стоимость товаров в мапе и на странице, а также их сумму
//    public static boolean checkPriceMapInCartList(Map<String, Integer> priceMap, List<WebElement> elemList) {
//        boolean isEquals = false;
//        int sumWithoutWarranty = 0;
//        int count = 0;
//        int countItemsWithoutWarranty = 0;
//
//        //перебераю все ключи прйс-мапы
//        for (String keyPrice : priceMap.keySet()) {
//            //если ключ не является "гарантией товара"
//            if (!keyPrice.contains("warranty")) {
//                countItemsWithoutWarranty++;
//                //сравниваю его с веб-элементами - ценами в корзине
//                for (WebElement element : elemList) {
//                    //если стоимость элемента мапы равна цене веб-элемента count++
//                    if (getPriceInInteger(element).equals(priceMap.get(keyPrice))) {
//                        count++;
//                        sumWithoutWarranty += getPriceInInteger(element);
//                    }
//                }
//            }
//        }
//
//        for (WebElement element : elemList) {
//            if (getPriceInInteger(element).equals(sumWithoutWarranty))
//                isEquals = true;
//        }
//        return isEquals && count == countItemsWithoutWarranty;
//
//    }
//
//
//    //метод добавляет товар в корзину
//    public static void addToCart(String element, Map<String,Integer> cartMap){
//    String addXpath = "//div//a[contains(text(), '" + element + "')]//..//..//..//div[@class='cart-items__product-block-amount']//button[@class='count-buttons__button count-buttons__button_plus']";
//    WebElement addableElement = driver.findElement(By.xpath(addXpath));
//
//    try{
//        String addElementPriceXpathIfMoreOne = "//div//a[contains(text(), '" + element + "')]//..//..//..//div[@class= 'cart-items__item-price']";
//        WebElement addElementPriceElement = driver.findElement(By.xpath(addElementPriceXpathIfMoreOne));
//        cartMap.put(element + " index: " + ((int)(Math.random()*10)), getPriceInInteger(addElementPriceElement));
//
//    }catch (NoSuchElementException e){
//        String addElementPriceXpathIfMoreOne = "//div//a[contains(text(), '" + element + "')]//..//..//..//div[@class='cart-items__product-price']//span[@class='price__current']";
//    }
//
//    waitUtilElementToBeClickable(addableElement);
//    addableElement.click();
//
//    }
//
//
//    public static void scrollToElementJs(WebElement element) {
//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
//        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
//    }
//
//    public static void waitUtilElementToBeVisible(WebElement element) {
//        wait.until(ExpectedConditions.visibilityOf(element));
//    }
//
//    public static void waitUtilElementToBeClickable(WebElement element) {
//        wait.until(ExpectedConditions.elementToBeClickable(element));
//    }
//
//    //public static boolean addToCartAndCheckSum()

    //метод, проверяющий удален ли товар из списка в корзине
//    protected static boolean checkThatItemDeleted(String item) {
//        boolean check = true;
//        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='cart-items__product-name']//a"));
//        for (WebElement element : elementList) {
//            if (element.getText().contains(item))
//                check = false;
//        }
//        return check;
//    }
//
//    //метод, удаляющий товар из корзины
//    protected static void removeFromCart(String element) throws InterruptedException {
//        String removeXpath = "//div//a[contains(text(), '" + element + "')]/../.././/..//div//button[@class= 'count-buttons__button count-buttons__button_minus']";
//        WebElement removableElement = driver.findElement(By.xpath(removeXpath));
//        waitUtilElementToBeVisible(removableElement);
//        removableElement.click();
//        Thread.sleep(3000);
//    }
//
//    //метод, удаляющий товар, проверяющий удален ли он,
//    //и проверяющий, что стоимость корзины уменьшилась на стоимость удаленного товара
//    protected static boolean deleteAndCheckThatItemRemove(String checkedElem) throws InterruptedException {
//        String cartSumXpath = "//div[@class='total-amount']//span[@class='price__current']";
//        String priceItemXpath = "//div//a[contains(text(), '" + checkedElem + "')]/../.././div[@class='menu-product menu-product_position-absolute']//button[text() = 'Удалить']//..//..//..//..//div[@class= 'price']//span[@class='price__current']";
//        WebElement cartSumElement = driver.findElement(By.xpath(cartSumXpath));
//        WebElement priceItemElement = driver.findElement(By.xpath(priceItemXpath));
//        Integer priceItem = getPriceInInteger(priceItemElement);
//        Integer previousCartSum = getPriceInInteger(cartSumElement);
//
//        removeFromCart(checkedElem);
//        Thread.sleep(1000);
//        Assert.assertTrue(checkThatItemDeleted(checkedElem));
//
//        return (previousCartSum - getPriceInInteger(cartSumElement)) == priceItem;
//    }

}
