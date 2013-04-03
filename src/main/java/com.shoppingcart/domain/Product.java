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

    private IOffer offer = new StandardOffer();

    public Product(String name, String code) {
        this.name = name;
        this.code = code;
    }


    public Product salePrice(Price salePrice){
        this.salePrice = salePrice;
        // May want to issue event from here to audit changes in the sales calculatePrice of a product
        return this;
    }

    Price getSalePrice() {
        return salePrice;
    }

    public Product withOffer(IOffer offer){
        this.offer = offer;
        return this;
    }

    public IOffer getOffer(){
        return offer;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (code != null ? !code.equals(product.code) : product.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
