package org.java8action.completeblefuture;

import org.java8action.completeblefuture.Discount.Code;

public class Quote {
    private final String shopName;
    private final double price;
    private final Discount.Code code;

    public Quote(String shopName, double price, Code code) {
        super();
        this.shopName = shopName;
        this.price = price;
        this.code = code;
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getCode() {
        return code;
    }

    public static Quote parse(String s) {
        String split[] = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code code = Discount.Code.valueOf(split[2].trim());
        return new Quote(shopName, price, code);
    }
}
