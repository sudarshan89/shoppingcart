package com.shoppingcart.domain;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 3:08 PM
 */
public class TestOfferPriceCalculator {

    Product standardProduct = new Product("Tropicano","stdTropicano");

    @Test
    public void standardOfferCalculator(){
        standardProduct = standardProduct.salePrice(new Price(BigDecimal.valueOf(10),Unit.QUANTITY));
        CartItem cartItem = new CartItem(10,standardProduct);

        Assert.assertEquals(new Price(BigDecimal.valueOf(100),Unit.QUANTITY),cartItem.calculatePrice());

        standardProduct = standardProduct.salePrice(new Price(BigDecimal.valueOf(1.99),Unit.QUANTITY));
        cartItem = new CartItem(5,standardProduct);
        Assert.assertEquals(new Price(BigDecimal.valueOf(9.95),Unit.QUANTITY),cartItem.calculatePrice());
    }


}
