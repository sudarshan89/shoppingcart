package com.shoppingcart.domain;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 4:43 PM
 */
public interface IOffer {

    List<CartItem> applyOffer(CartItem cartItem);
}
