package ru.appline.framework.managers;

import ru.appline.framework.pages.*;

public class ManagerPages {

    private static ManagerPages managerPages;

    BasePage basePage;
    StartPage startPage;
    CatalogPage catalogPage;
    ItemPage itemPage;
    CartPage cartPage;

    private ManagerPages(){

    }

    public static ManagerPages getManagerPages(){
        if(managerPages == null){
            managerPages = new ManagerPages();
        }
        return managerPages;
    }

    public BasePage getBasePage() {
        if(basePage == null){
            basePage = new BasePage();
        }
        return basePage;
    }

    public StartPage getStartPage() {
        if(startPage == null){
            startPage= new StartPage();
        }
        return startPage;
    }

    public CatalogPage getCatalogPage() {
        if(catalogPage == null){
            catalogPage = new CatalogPage();
        }
        return catalogPage;
    }

    public ItemPage getItemPage() {
        if(itemPage == null){
            itemPage = new ItemPage();
        }
        return itemPage;
    }

    public CartPage getCartPage() {
        if(cartPage == null){
            cartPage = new CartPage();
        }
        return cartPage;
    }

}
