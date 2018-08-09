package org.pravin.unit2;

import java.util.function.BiConsumer;

public class ExceptionHandlingExample {
    public static void main(String[] args) {

        int a[] = { 1, 2, 3, 4, 5, 6 };
        int key = 0;

        process(a, key,(value,k)->
        {
            try
            {
                System.out.println(value/k);  
            }
            catch(ArithmeticException e)
            {
                System.out.println("ArithMetic Excepption occured");
            }
            
        });

    }

    private static void process(int[] a, int key,BiConsumer<Integer, Integer> biConsumer) {
        for (int integer : a) {
         
          biConsumer.accept(integer, key);

        }

    }

}
