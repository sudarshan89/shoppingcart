package com.shoppingcart.domain;

import com.google.common.base.Preconditions;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 3:50 PM
 */
public class CartItem {

    public static final int STANDARD_CART_ITEM = 0;
    public static final int OFFER_CART_ITEM = 1;
    private float quantity;
    private Product product;

    private Price salePrice;

    private Price productSalePrice;


    public CartItem(float quantity, Product product) {
        Preconditions.checkArgument(quantity > 0);
        Preconditions.checkNotNull(product.getSalePrice());
        this.quantity = quantity;
        this.productSalePrice = product.getSalePrice();
        this.salePrice = this.productSalePrice.multipliedByQuantity(this.quantity);
        this.product = product;
    }

    public static CartItem newCartItemWithOfferPrice(float quantity, CartItem originalCartItem, Price offerPrice){
        CartItem cartItem =  new CartItem(quantity,originalCartItem.getProduct());
        cartItem.setSalePrice(offerPrice);
        return cartItem;
    }


    List<CartItem> calculatePrice() {
        IOffer offer = product.getOffer();
        return offer.applyOffer(this);
    }

    CartItem standardSalePrice(){
        salePrice = productSalePrice.multipliedByQuantity(quantity);
        return this;
    }

    public Price getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Price salePrice) {
        this.salePrice = salePrice;
    }

    public float getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public String toString(){
        return "quantity " + quantity  + " @ " + salePrice;
    }
}
