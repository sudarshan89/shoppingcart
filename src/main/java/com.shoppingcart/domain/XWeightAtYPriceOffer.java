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

    private final float unitOfOfferWeight;
    private final Price offerPrice;

    public XWeightAtYPriceOffer(float unitOfOfferWeight, Price offerPrice) {
        this.offerPrice = offerPrice;
        this.unitOfOfferWeight = unitOfOfferWeight;
    }

    @Override
    public List<CartItem> applyOffer(CartItem cartItem) {
        List<CartItem> cartItems = Lists.newArrayList();
        float totalWeight = cartItem.getQuantity();
        float weightOfItemsWithOfferPrice = totalWeight - (totalWeight % this.unitOfOfferWeight);
        cartItems.add(cartItemWithOfferPrice(cartItem, weightOfItemsWithOfferPrice));
        float weightOfItemsWithStandardPrice = totalWeight - weightOfItemsWithOfferPrice;
        if (weightOfItemsWithStandardPrice > 0)
            cartItems.add(cartItemsWithStandardPrice(cartItem, weightOfItemsWithStandardPrice));
        return cartItems;
    }

    private CartItem cartItemsWithStandardPrice(CartItem cartItem, float weightOfItemsWithStandardPrice) {
        CartItem standardPricedCartItem = new CartItem(weightOfItemsWithStandardPrice, cartItem.getProduct());
        return standardPricedCartItem;
    }

    private CartItem cartItemWithOfferPrice(CartItem cartItem, float weightOfItemsWithOfferPrice) {
        float quantityAfterFactoringInUnitOfOffer = weightOfItemsWithOfferPrice / unitOfOfferWeight;
        CartItem offerPricedCartItem = CartItem.newCartItemWithOfferPrice(weightOfItemsWithOfferPrice, cartItem,
                offerPrice.multipliedByQuantity(quantityAfterFactoringInUnitOfOffer));
        return offerPricedCartItem;
    }
}
