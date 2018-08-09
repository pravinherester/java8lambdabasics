package org.java8action.completeblefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Shop {
    private String shopname;
    private static final Random random = new Random();

    public String getShopname() {
        return shopname;
    }

    Shop() {

    }

    Shop(String shopname) {
        this.shopname = shopname;
    }

    public String getPrice(String product)

    {
        double price = calculatePrice(product);
        Random random = new Random();
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        System.out.println(String.format(" %s : %.2f : %s", shopname, price, code));
        return String.format(" %s : %.2f : %s", shopname, price, code);

    }

    public static double calculatePrice(String product) {

        delay();
        Random random = new Random();

        return random.nextDouble() * product.charAt(0) + product.charAt(1);

    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> fut = new CompletableFuture<>();
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
        /*
         * try {
         * 
         * new Thread(() -> {
         * 
         * double price = calculatePrice(product); fut.complete(price); }).start(); } catch (Exception e) { fut.completeExceptionally(e); }
         */

        // return fut;

    }

    public static void delay() {
        int delay=500+random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Shop shop = new Shop();
        long start = System.nanoTime();
        // System.out.println(shop.getPirce("ball"));
        Future<Double> future = shop.getPriceAsync("ball");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after" + invocationTime + "msecs");
        doSomethingElse();
        try {
            Double price = future.get();
            System.out.printf("price is %.2f%n", price);
            long priceTime = ((System.nanoTime() - start) / 1_000_000);
            System.out.println("Price returned after " + priceTime + "msecs");
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void doSomethingElse() {
        System.out.println("I am doing something else");

    }
}
