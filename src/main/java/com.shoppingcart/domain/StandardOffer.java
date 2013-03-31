package com.shoppingcart.domain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 4:44 PM
 */
public final class StandardOffer implements  IOffer {
    @Override
    public List<CartItem> applyOffer(CartItem cartItem) {
        return Lists.newArrayList(cartItem.standardSalePrice());
    }
}
