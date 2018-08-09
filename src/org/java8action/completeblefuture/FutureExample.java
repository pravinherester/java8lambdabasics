package org.java8action.completeblefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
   public static void main(String[] args) {
    ExecutorService exec=Executors.newCachedThreadPool();
    Future<Double> future=exec.submit(new Callable<Double>() {
        @Override
        public Double call() throws Exception {
            // TODO Auto-generated method stub
            return 2D;
        }
    });
    doSomething();
    try {
        Double result=future.get();
        
        System.out.println(result);
        
    } catch (Exception e) {
        // TODO: handle exception
    }
}

private static void doSomething() {
    
    System.out.println("pravin");
}

}
