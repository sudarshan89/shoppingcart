package com.shoppingcart.domain;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 3:08 PM
 */
public class TestOfferPriceCalculator {

    @Test
    public void standardOfferCalculator() {
        Product standardProduct = new Product("Tropicano", "stdTropicano");
        standardProduct = standardProduct.salePrice(Price.PricePerQuantityUnit(BigDecimal.valueOf(10)));
        CartItem cartItem = new CartItem(10, standardProduct);

        Assert.assertEquals(Price.PricePerQuantityUnit(BigDecimal.valueOf(100)), cartItem.calculatePrice().get(0)
                .getSalePrice());

        standardProduct = standardProduct.salePrice(Price.PricePerQuantityUnit(BigDecimal.valueOf(1.99)));
        cartItem = new CartItem(5, standardProduct);
        Assert.assertEquals(Price.PricePerQuantityUnit(BigDecimal.valueOf(9.95)), cartItem.calculatePrice().get(0)
                .getSalePrice());
    }

    @Test
    public void testSizeOfCartItemsForXQuantityAtYPriceOffer() {
        Product offerProduct = new Product("Wallet", "ProductCodeForWallets").salePrice(Price.PricePerQuantityUnit
                (BigDecimal.valueOf(2)));
        offerProduct.withOffer(new XQuantityAtYPriceOffer( 3,Price.PricePerQuantityUnit(BigDecimal.valueOf(1))));
        CartItem cartItem = new CartItem(10, offerProduct);
        List<CartItem> cartItems = cartItem.calculatePrice();
        Assert.assertEquals(2, cartItems.size());

        cartItem = new CartItem(11, offerProduct);
        cartItems = cartItem.calculatePrice();
        Assert.assertEquals(2, cartItems.size());

    }


    @Test
    public void testSalesPriceOfCartItemWithOfferPriceForXQuantityAtYPriceOffer() {
        Product offerProduct = new Product("Wallet", "ProductCodeForWallets")
                                   .salePrice(Price.PricePerQuantityUnit(BigDecimal.valueOf(1.99)));

        offerProduct.withOffer(new XQuantityAtYPriceOffer(3,Price.PricePerQuantityUnit(BigDecimal.valueOf(4))));
        CartItem cartItem = new CartItem(10, offerProduct);
        List<CartItem> cartItems = cartItem.calculatePrice();
        Assert.assertEquals(cartItems.get(0).getSalePrice(),Price.PricePerQuantityUnit(BigDecimal.valueOf(12)));
        Assert.assertEquals(cartItems.get(0).getQuantity(),9,0);
    }

    @Test
    public void testCartItemStandardPriceForXQuantityAtYPriceOffer() {
        Product offerProduct = new Product("Wallet", "ProductCodeForWallets")
                .salePrice(Price.PricePerQuantityUnit(BigDecimal.valueOf(1.99)));

        offerProduct.withOffer(new XQuantityAtYPriceOffer( 3,Price.PricePerQuantityUnit(BigDecimal.valueOf(4))));
        CartItem cartItem = new CartItem(10, offerProduct);
        List<CartItem> cartItems = cartItem.calculatePrice();
        Assert.assertEquals(cartItems.get(1).getSalePrice(),Price.PricePerQuantityUnit(BigDecimal.valueOf(1.99)));
        Assert.assertEquals(cartItems.get(1).getQuantity(),1,0);
    }

    @Test
    public void testCartItemBillOutputXQuantityAtYPriceOffer() {
        Product offerProduct = new Product("Wallet", "ProductCodeForWallets")
                .salePrice(Price.PricePerQuantityUnit(BigDecimal.valueOf(1.99)));

        offerProduct.withOffer(new XQuantityAtYPriceOffer(3,Price.PricePerQuantityUnit(BigDecimal.valueOf(4))));
        CartItem cartItem = new CartItem(10, offerProduct);
        List<CartItem> cartItems = cartItem.calculatePrice();
        Assert.assertEquals("[quantity 9.0 @ 12, quantity 1.0 @ 1.99]",
                cartItems.toString());
    }


    @Test
    public void testSizeOfCartItemsForXWeightAtYPriceOffer() {
        Product offerProduct = new Product("Apples", "ProductCodeForApples")
                .salePrice(Price.PricePerWieghtedUnit(BigDecimal.valueOf(0.99)));

        offerProduct.withOffer(new XWeightAtYPriceOffer(2,Price.PricePerWieghtedUnit(BigDecimal.valueOf(1.80))));
        CartItem cartItem = new CartItem(13.50F, offerProduct);
        List<CartItem> cartItems = cartItem.calculatePrice();
        Assert.assertEquals(2, cartItems.size());

        cartItem = new CartItem(12, offerProduct);
        cartItems = cartItem.calculatePrice();
        Assert.assertEquals(1, cartItems.size());


    }

    @Test
    public void testCartItemBillOutputXWeightAtYPriceOffer() {
        Product offerProduct = new Product("Apples", "ProductCodeForApples")
                .salePrice(Price.PricePerWieghtedUnit(BigDecimal.valueOf(0.99)));

        offerProduct.withOffer(new XWeightAtYPriceOffer(2,Price.PricePerWieghtedUnit(BigDecimal.valueOf(1.80))));
        CartItem cartItem = new CartItem(13.50F, offerProduct);
        List<CartItem> cartItems = cartItem.calculatePrice();
        Assert.assertEquals("[quantity 12.0kg @ 10.8, quantity 1.5kg @ 1.485]",cartItems.toString());
    }


}
