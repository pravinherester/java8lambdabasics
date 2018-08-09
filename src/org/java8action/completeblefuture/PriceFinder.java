package org.java8action.completeblefuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.CompletableFuture;

import javax.sound.midi.SysexMessage;

import org.java8action.completeblefuture.ExchangeService.Money;

public class PriceFinder {

    private final Executor exec = Executors.newFixedThreadPool(Math.min(getShopList().size(), 100), new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    List<Shop> getShopList() {

        List<Shop> shops = Arrays.asList(new Shop("BestPrice"), new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"), new Shop("BuyItAll"), new Shop("BestPrice"), new Shop("BestPrice"),
                new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"), new Shop("BuyItAll"));
        return shops;
    }

    public List<String> findPrices(String product) {

        List<Shop> shops = getShopList();

        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {

        List<Shop> shops = getShopList();
        System.out.println("-------------Prallel--------------");
        return shops.parallelStream()
                .map(shop -> (shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPricesAsync(String product) {

        List<Shop> shops = getShopList();

        List<CompletableFuture<String>> fut = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product)))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote))))
                .collect(java.util.stream.Collectors.toList());

        return fut.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }

    public List<String> findPricesAsyncCustomExecutor(String product) {

        List<Shop> shops = getShopList();

        System.out.println("-------------Custom Executor--------------");
        List<CompletableFuture<String>> fut = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), exec))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), exec)))
                .collect(java.util.stream.Collectors.toList());

        return fut.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }

    public List<String> findPricesInUSD3(String product) {
        List<Shop> shops = getShopList();
        Stream<CompletableFuture<String>> priceFuturesStream = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.calculatePrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD)), (price, rate) -> price * rate)
                        .thenApply(price -> shop.getShopname() + "price is " + price));

        // However, we should gather the CompletableFutures into a List so that the asynchronous
        // operations are triggered before being "joined."
        List<CompletableFuture<String>> priceFutures = priceFuturesStream.collect(Collectors.toList());
        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        return prices;
    }

    public List<String> findPricesInUSDJava7(String product) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Shop> shops = getShopList();
        List<Future<Double>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            Future<Double> futureRate = executor.submit(new Callable<Double>() {

                @Override
                public Double call() throws Exception {
                    // TODO Auto-generated method stub
                    return ExchangeService.getRate(Money.EUR, Money.USD);

                }
            });

            Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {

                @Override
                public Double call() throws Exception {
                    // TODO Auto-generated method stub
                    double priceInEUR = shop.calculatePrice(product);
                    return priceInEUR * futureRate.get();
                }
            });
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures) {
            try {
                prices.add(priceFuture.get()
                        .toString());
            } catch (InterruptedException | ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return prices;
    }

    public Stream<CompletableFuture<String>> findPricesStream(String product) {

        List<Shop> shops = getShopList();

        Stream<CompletableFuture<String>> fut = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product)))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote))));
        return fut;

    }

    public static void main(String[] args) {
        System.out.println("Number of processoors" + Runtime.getRuntime()
                .availableProcessors());
        PriceFinder priceFinder = new PriceFinder();
        /*
         * 
         * long starTime1 = System.nanoTime(); List<String> futList = priceFinder.findPricesAsync("ball"); long duration1 = (System.nanoTime() - starTime1) / 1_000_000; futList.forEach(price -> {
         * System.out.println(price); }); System.out.printf("Time taken %d msec", duration1);
         * 
         * 
         * long starTime = System.nanoTime(); List<String> priceList = priceFinder.findPrices("ball"); long duration = (System.nanoTime() - starTime) / 1_000_000; priceList.forEach(price -> {
         * System.out.println(price); }); System.out.printf("Time taken %d msec", duration);
         * 
         * long starTime2 = System.nanoTime(); List<String> priceListParallel = priceFinder.findPricesParallel("ball"); long duration2 = (System.nanoTime() - starTime2) / 1_000_000;
         * priceListParallel.forEach(price -> { System.out.println(price); }); System.out.printf("Time taken %d msec", duration2);
         */
        /*
         * long starTime3 = System.nanoTime(); List<String> priceListCustomThread = priceFinder.findPricesAsyncCustomExecutor("ball"); long duration3 = (System.nanoTime() - starTime3) / 1_000_000;
         * priceListCustomThread.forEach(price -> { System.out.println(price); }); System.out.printf("Time taken %d msec", duration3);
         * 
         * long starTime4 = System.nanoTime(); List<String> priceListCustomThreadCurrencyConversion = priceFinder.findPricesInUSD3("ball"); long duration4 = (System.nanoTime() - starTime4) /
         * 1_000_000; priceListCustomThreadCurrencyConversion.forEach(price -> { System.out.println(price); }); System.out.printf("Time taken %d msec", duration4);
         */
        /*
         * long starTime5 = System.nanoTime(); List<String> priceListCustomThreadCurrencyConversionJava7 = priceFinder.findPricesInUSDJava7("ball"); long duration5 = (System.nanoTime() - starTime5) /
         * 1_000_000; priceListCustomThreadCurrencyConversionJava7.forEach(price -> { System.out.println(price); }); System.out.printf("Time taken %d msec", duration5);
         */
        long start = System.nanoTime();
        CompletableFuture[] futures = priceFinder.findPricesStream("ball")
                .map(f -> f.thenAccept(
                     s -> System.out.println(s + " (done in " +
                          ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in "
                           + ((System.nanoTime() - start) / 1_000_000) + " msecs");

    }
}
