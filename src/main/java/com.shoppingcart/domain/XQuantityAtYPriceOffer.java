package com.shoppingcart.domain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 5:13 PM
 */
public class XQuantityAtYPriceOffer implements IOffer {

    private final int unitOfOffer;
    private final Price offerPrice;

    public XQuantityAtYPriceOffer(int unitOfOffer,Price offerPrice) {
        this.offerPrice = offerPrice;
        this.unitOfOffer = unitOfOffer;
    }

    @Override
    public List<CartItem> applyOffer(CartItem cartItem) {
        List<CartItem> cartItems = Lists.newArrayList();
        int noOfItems = (int) cartItem.getQuantity();
        int noOfItemsWithOfferPrice = noOfItems - (noOfItems % unitOfOffer);
        cartItems.add(cartItemWithOfferPrice(cartItem, noOfItemsWithOfferPrice));
        int noOfItemsWithStandardPrice = noOfItems - noOfItemsWithOfferPrice;
        if (noOfItemsWithStandardPrice > 0)
            cartItems.add(cartItemWithStandardPrice(cartItem, noOfItemsWithStandardPrice));
        return cartItems;
    }

    private CartItem cartItemWithStandardPrice(CartItem cartItem, int noOfItemsWithStandardPrice) {
        CartItem standardPricedCartItem = new CartItem(noOfItemsWithStandardPrice, cartItem.getProduct());
        return standardPricedCartItem;
    }

    private CartItem cartItemWithOfferPrice(CartItem cartItem, int noOfItemsWithOfferPrice) {
        int quantityAfterFactoringInUnitOfOffer = noOfItemsWithOfferPrice / unitOfOffer;
        CartItem offerPricedCartItem = CartItem.newCartItemWithOfferPrice(noOfItemsWithOfferPrice, cartItem,
                offerPrice.multipliedByQuantity(quantityAfterFactoringInUnitOfOffer));
        return offerPricedCartItem;
    }
}
