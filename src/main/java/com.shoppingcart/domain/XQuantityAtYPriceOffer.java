package com.shoppingcart.domain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 5:13 PM
 */
public class XQuantityAtYPriceOffer implements IOffer  {

    private final int quantity;
    private final Price offerPrice;

    public XQuantityAtYPriceOffer(Price offerPrice, int quantity) {
        this.offerPrice = offerPrice;
        this.quantity = quantity;
    }

    @Override
    public List<CartItem> applyOffer(CartItem cartItem) {
        List<CartItem> cartItems = Lists.newArrayList();
        int noOfItems = (int) cartItem.getQuantity();
        int noOfItemsWithOfferPrice = noOfItems - (noOfItems%quantity);
        cartItems.addAll(itemsWithOfferPrice(cartItem, noOfItemsWithOfferPrice));
        int noOfItemsWithStandardPrice = noOfItems - noOfItemsWithOfferPrice;
        if(noOfItemsWithStandardPrice>0)
        cartItems.addAll(itemsWithStandardPrice(cartItem, noOfItemsWithStandardPrice));
        return cartItems;
    }

    private List<CartItem> itemsWithStandardPrice(CartItem cartItem, int noOfItemsWithStandardPrice) {
        List<CartItem> cartItems = Lists.newArrayList();
        for (int i = 0; i < noOfItemsWithStandardPrice ; i++) {
            CartItem standardPricedCartItem =  new CartItem(1,cartItem.getProduct());
            standardPricedCartItem = standardPricedCartItem.standardSalePrice();
            cartItems.add(standardPricedCartItem);
        }
        return cartItems;
    }

    private List<CartItem> itemsWithOfferPrice(CartItem cartItem, int specialOfferIndex) {
        List<CartItem> cartItems = Lists.newArrayList();
        for(int count = 0; count<specialOfferIndex;count=count+quantity){
            CartItem offerPricedCartItem =  new CartItem(quantity,cartItem.getProduct());
            offerPricedCartItem.setSalePrice(offerPrice);
            cartItems.add(offerPricedCartItem);
        }
        return cartItems;
    }
}
