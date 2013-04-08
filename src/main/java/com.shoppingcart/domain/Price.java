package com.shoppingcart.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 3:14 PM
 */
public final class Price {

    private BigDecimal money;

    public Price(BigDecimal money) {
        this.money = money;
    }

    BigDecimal getMoney() {
        return money;
    }

    void setMoney(BigDecimal money) {
        this.money = money;
    }


    public Price multipliedByQuantity(float quantity){
        return new Price(money.multiply(new BigDecimal(quantity)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (money != null ? !money.equals(price.money) : price.money != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = money != null ? money.hashCode() : 0;
        return result;
    }

    public String toString(){
        return money.toPlainString() ;
    }
}
