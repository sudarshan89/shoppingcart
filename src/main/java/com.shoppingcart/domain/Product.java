package com.shoppingcart.domain;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 3:40 PM
 */
public class Product {

    private final String name;
    private final String code;

    private Price salePrice;


    public Product(String name, String code) {
        this.name = name;
        this.code = code;
    }

    Price getSalePrice() {
        return salePrice;
    }

    public Product salePrice(Price salePrice){
        this.salePrice = salePrice;
        // May want to issue event from here to audit changes in the sales calculatePrice of a product
        return this;
    }




}
