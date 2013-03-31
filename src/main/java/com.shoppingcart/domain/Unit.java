package com.shoppingcart.domain;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 31/3/13
 * Time: 3:15 PM
 */
public enum Unit {
    QUANTITY(""),WEIGHTED("kg");
    private String measure="";

    private Unit(String measure){
        this.measure = measure;
    }

    public String toString(){
        return measure;
    }


}
