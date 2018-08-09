package org.java8action.completeblefuture;

public class ExchangeService {
    public enum Money {
        USD(60),
        EUR(90);

        private double rate;

        Money(double rate) {
            this.rate = rate;
        }
    }

    public static double getRate(Money source, Money destination) {
        return getRateWithDelay(source, destination);
    }

    private static double getRateWithDelay(Money source, Money destinatation) {
        Shop.delay();
        return destinatation.rate / source.rate;
    }
}
