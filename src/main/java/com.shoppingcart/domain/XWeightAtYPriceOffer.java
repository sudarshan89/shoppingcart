package com.shoppingcart.domain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 5:13 PM
 */
public class XWeightAtYPriceOffer implements IOffer {

    private final float offerWeight;
    private final Price offerPrice;

    public XWeightAtYPriceOffer(Price offerPrice, float offerWeight) {
        this.offerPrice = offerPrice;
        this.offerWeight = offerWeight;
    }

    @Override
    public List<CartItem> applyOffer(CartItem cartItem) {
        List<CartItem> cartItems = Lists.newArrayList();
        float weight = cartItem.getQuantity();
        float weightOfItemsWithOfferPrice = weight - (weight % this.offerWeight);
        cartItems.addAll(itemsWithOfferPrice(cartItem, weightOfItemsWithOfferPrice));
        float weightOfItemsWithStandardPrice = weight - weightOfItemsWithOfferPrice;
        if (weightOfItemsWithStandardPrice > 0)
            cartItems.addAll(itemsWithStandardPrice(cartItem, weightOfItemsWithStandardPrice));
        return cartItems;
    }

    private List<CartItem> itemsWithStandardPrice(CartItem cartItem, float weightOfItemsWithStandardPrice) {
        List<CartItem> cartItems = Lists.newArrayList();
        CartItem standardPricedCartItem = new CartItem(weightOfItemsWithStandardPrice, cartItem.getProduct());
        standardPricedCartItem = standardPricedCartItem.standardSalePrice();
        cartItems.add(standardPricedCartItem);
        return cartItems;
    }

    private List<CartItem> itemsWithOfferPrice(CartItem cartItem, float weightOfItemsWithOfferPrice) {
        List<CartItem> cartItems = Lists.newArrayList();
        CartItem offerPricedCartItem = new CartItem(weightOfItemsWithOfferPrice, cartItem.getProduct());
        offerPricedCartItem.setSalePrice(offerPrice.multipliedByQuantity(weightOfItemsWithOfferPrice));
        cartItems.add(offerPricedCartItem);
        return cartItems;
    }
}
