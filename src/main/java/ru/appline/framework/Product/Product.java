package ru.appline.framework.Product;

import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private Integer price;
    private String warranty;
    public static List<Product> productList = new ArrayList<Product>();

    public Product(String name, Integer price, String warranty){
        this.name = name;
        this.price = price;
        this.warranty = warranty;
        productList.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getWarranty() {
        return warranty;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", warranty='" + warranty + '\'' +
                '}';
    }
}

