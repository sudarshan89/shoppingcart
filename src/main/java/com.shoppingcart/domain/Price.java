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
    private Unit perUnit;

    public Price(BigDecimal money, Unit perUnit) {
        this.money = money;
        this.perUnit = perUnit;
    }

    public static Price PricePerQuantityUnit(BigDecimal money){
        Price price = new Price(money,Unit.QUANTITY);
        return price;
    }

    public static Price PricePerWieghtedUnit(BigDecimal money){
        Price price = new Price(money,Unit.WEIGHTED);
        return price;
    }

    BigDecimal getMoney() {
        return money;
    }

    void setMoney(BigDecimal money) {
        this.money = money;
    }

    Unit getPerUnit() {
        return perUnit;
    }

    void setPerUnit(Unit perUnit) {
        this.perUnit = perUnit;
    }

    public Price multipliedByQuantity(float quantity){
        return new Price(money.multiply(new BigDecimal(quantity)),perUnit);
    }

    public Price dividedByQuantity(float quantity){
        return new Price(money.divide(new BigDecimal(quantity)),perUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (money != null ? !money.equals(price.money) : price.money != null) return false;
        if (perUnit != price.perUnit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = money != null ? money.hashCode() : 0;
        result = 31 * result + (perUnit != null ? perUnit.hashCode() : 0);
        return result;
    }

    public String toString(){
        return money.toPlainString() ;
    }
}
