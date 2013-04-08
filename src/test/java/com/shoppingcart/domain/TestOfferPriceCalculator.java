package com.shoppingcart.domain;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.shoppingcart.domain.CartItem.STANDARD_CART_ITEM;
import static com.shoppingcart.domain.CartItem.OFFER_CART_ITEM;

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
        standardProduct = standardProduct.salePrice(new Price(BigDecimal.valueOf(10)));
        CartItem cartItem = new CartItem(10, standardProduct);

        Assert.assertEquals(new Price(BigDecimal.valueOf(100)), cartItem.calculatePrice().get(STANDARD_CART_ITEM)
                .getSalePrice());

        standardProduct = standardProduct.salePrice(new Price(BigDecimal.valueOf(1.99)));
        cartItem = new CartItem(5, standardProduct);
        Assert.assertEquals(new Price(BigDecimal.valueOf(9.95)), cartItem.calculatePrice().get(STANDARD_CART_ITEM)
                .getSalePrice());
    }

    @Test
    public void testSizeOfCartItemsForXQuantityAtYPriceOffer() {
        Product offerProduct = new Product("Wallet", "ProductCodeForWallets").salePrice(new Price(BigDecimal.valueOf(2)));
        offerProduct.withOffer(new XQuantityAtYPriceOffer( 3,new Price(BigDecimal.valueOf(1))));
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
                                   .salePrice(new Price(BigDecimal.valueOf(1.99)));

        offerProduct.withOffer(new XQuantityAtYPriceOffer(3,new Price(BigDecimal.valueOf(4))));
        CartItem cartItem = new CartItem(10, offerProduct);
        List<CartItem> cartItems = cartItem.calculatePrice();
        Assert.assertEquals(cartItems.get(STANDARD_CART_ITEM).getSalePrice(),new Price(BigDecimal.valueOf(12)));
        Assert.assertEquals(cartItems.get(STANDARD_CART_ITEM).getQuantity(),9,0);
    }

    @Test
    public void testCartItemStandardPriceForXQuantityAtYPriceOffer() {
        Product offerProduct = new Product("Wallet", "ProductCodeForWallets")
                .salePrice(new Price(BigDecimal.valueOf(1.99)));

        offerProduct.withOffer(new XQuantityAtYPriceOffer( 3,new Price(BigDecimal.valueOf(4))));
        CartItem cartItem = new CartItem(10, offerProduct);
        List<CartItem> cartItems = cartItem.calculatePrice();
        Assert.assertEquals(cartItems.get(OFFER_CART_ITEM).getSalePrice(),new Price(BigDecimal.valueOf(1.99)));
        Assert.assertEquals(cartItems.get(OFFER_CART_ITEM).getQuantity(), 1,0);
    }

    @Test
    public void testCartItemBillOutputXQuantityAtYPriceOffer() {
        Product offerProduct = new Product("Wallet", "ProductCodeForWallets")
                .salePrice(new Price(BigDecimal.valueOf(1.99)));

        offerProduct.withOffer(new XQuantityAtYPriceOffer(3,new Price(BigDecimal.valueOf(4))));
        CartItem cartItem = new CartItem(10, offerProduct);
        List<CartItem> cartItems = cartItem.calculatePrice();
        Assert.assertEquals("[quantity 9.0 @ 12, quantity 1.0 @ 1.99]",
                cartItems.toString());
    }


    @Test
    public void testSizeOfCartItemsForXWeightAtYPriceOffer() {
        Product offerProduct = new Product("Apples", "ProductCodeForApples")
                .salePrice(new Price(BigDecimal.valueOf(0.99)));

        offerProduct.withOffer(new XWeightAtYPriceOffer(2,new Price(BigDecimal.valueOf(1.80))));
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
                .salePrice(new Price(BigDecimal.valueOf(0.99)));

        offerProduct.withOffer(new XWeightAtYPriceOffer(2,new Price(BigDecimal.valueOf(1.80))));
        CartItem cartItem = new CartItem(13.50F, offerProduct);
        List<CartItem> cartItems = cartItem.calculatePrice();
        Assert.assertEquals("[quantity 12.0 @ 10.8, quantity 1.5 @ 1.485]",cartItems.toString());
    }



    @Test
    public void testCartItemBillOutputForBuyXGetYFreeOffer() {
        Product offerProduct = new Product("Wallet", "ProductCodeForWallets").salePrice(new Price(BigDecimal.valueOf(1.99)));
        offerProduct.withOffer(new BuyXGetYFree(3,2));
        CartItem cartItem = new CartItem(10, offerProduct);
        List<CartItem> cartItems = cartItem.calculatePrice();
        Assert.assertEquals(6,cartItems.get(STANDARD_CART_ITEM).getQuantity(), 0);
        Assert.assertTrue(new Price(BigDecimal.valueOf(11.94)).equals(cartItems.get(STANDARD_CART_ITEM).getSalePrice()));

        cartItem = new CartItem(15, offerProduct);
        cartItems = cartItem.calculatePrice();
        Assert.assertEquals(6,cartItems.get(OFFER_CART_ITEM).getQuantity(),0);


    }

}
