package com.shoppingcart.domain;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 7/4/13
 * Time: 7:32 PM
 */
public class BuyXGetYFree implements IOffer {

    private int unitsPurchased;
    private int unitsFree;

    public BuyXGetYFree(int unitsPurchased,int unitsFree) {
        this.unitsPurchased = unitsPurchased;
        this.unitsFree = unitsFree;
    }

    @Override
    public List<CartItem> applyOffer(CartItem cartItem) {
        List<CartItem> cartItems = Lists.newArrayList();
        int noOfItems = (int) cartItem.getQuantity();

        final int unitOfPurchase = unitsFree + unitsPurchased;
        final int countOfUnitOfPurchase = noOfItems/unitOfPurchase;
        final int freeUnits = countOfUnitOfPurchase * unitsFree;
        final int unitsPaidFor = noOfItems  - freeUnits;

        cartItems.add(new CartItem(unitsPaidFor, cartItem.getProduct()));
        CartItem freeCartItem = new CartItem(freeUnits, cartItem.getProduct());
        freeCartItem.setSalePrice(new Price(BigDecimal.ZERO));
        cartItems.add(freeCartItem);
        return cartItems;
    }
/*
    private CartItem cartItemWithOfferPrice(CartItem cartItem, int noOfItemsWithOfferPrice) {
        int quantityAfterFactoringInUnitOfOffer = noOfItemsWithOfferPrice / unitOfOffer;
        CartItem offerPricedCartItem = CartItem.newCartItemWithOfferPrice(noOfItemsWithOfferPrice, cartItem,
                offerPrice.multipliedByQuantity(quantityAfterFactoringInUnitOfOffer));
        return offerPricedCartItem;
    }*/
}
