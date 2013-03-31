package com.shoppingcart.domain;

import com.google.common.base.Preconditions;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 3:50 PM
 */
public class CartItem {

    private int quantity;
    private Product product;

    private Price salePrice;


    public CartItem(int quantity, Product product) {
        Preconditions.checkArgument(quantity > 0);
        Preconditions.checkNotNull(product.getSalePrice());
        this.quantity = quantity;
        this.product = product;
    }


    public Price calculatePrice() {
        salePrice = product.getSalePrice().multipliedByQuantity(quantity);
        return salePrice;
    }
}
