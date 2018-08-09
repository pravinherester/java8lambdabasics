package org.java8action.completeblefuture;

public class Discount {
    public enum Code {
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20);

        private int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        double price = quote.getPrice();
        Code code = quote.getCode();
        return quote.getShopName()+ " price is " + calculateDiscount(price, code);
    }       

    private static double calculateDiscount(double price, Code code) {
        Shop.delay();
        return (price * (100 -code.percentage) / 100);

    }
    
    public static void main(String[] args) {
        Discount discount=new Discount();
      System.out.println(Discount.Code.valueOf("PLATINUM "));
    }
}
