package org.java8action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CalRemoveExample {
    public static void main(String[] args) {
        Test1 arr[] = { new Test1("7M7313791D4765819", "501"), new Test1("7M7313791D4765819", "400"), new Test1("7M7313791D4765819", "200"), new Test1("7GY5428846055014K", "400"),
                new Test1("7GY5428846055014K", "400"), new Test1("7GY5428846055014K", "400"), new Test1("7GY5428846055014K", "400"), new Test1("7GY5428846055014K", "400"),
                new Test1("7GY5428846055014K", "400"), new Test1("7GY5428846055014K", "400"), new Test1("2BS577770W4362809", "400"), new Test1("5XH5551531810882T", "400"),
                new Test1("5XH5551531810882T", "400") };
        ArrayList<Test1> test = new ArrayList<Test1>();
        Collections.addAll(test, arr);

        Map<String, List<Test1>> groupbyId = test.stream()
                .collect(Collectors.groupingBy(Test1::getCartId));
        
        ArrayList<Test1> filtered = (ArrayList<Test1>) Test1.filter(test, (Test1 t) -> t.getStatus()
                .equals("200"));

        ArrayList<Test1> error = (ArrayList<Test1>) Test1.filter(test, (Test1 t) -> t.getStatus()
                .equals("400")
                || t.getStatus()
                        .equals(("501")));

        System.out.println(test.stream()
                .filter(t -> (t.getStatus()
                        .equals("200")
                        && (t.getStatus()
                                .equals("400")
                                || t.getStatus()
                                        .equals("501"))))
                .map(Test1::getCartId)
                .collect(Collectors.toList()));

        /*
         * System.out.println(filtered.stream() .map(Test1::getCartId) .collect(Collectors.toList()));
         * 
         * System.out.println(error.stream() .map(Test1::getCartId) .collect(Collectors.toList()));
         */

        List<Test1> unavailable = error.stream()
                .filter(e -> (filtered.stream()
                        .filter(d -> d.getCartId()
                                .equals(e))
                        .count()) < 1)
                .collect(Collectors.toList());

        /*
         * System.out.println(unavailable.stream() .map(Test1::getCartId) .collect(Collectors.toList()));
         */
    }

}

class Test1 {
    String cartId;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    Test1(String cartId, String status) {
        this.cartId = cartId;
        this.status = status;
    }

    static <T> List<T> filter(List<T> inventory, Predicate<T> predicate) {
        List<T> result = new ArrayList<T>();
        for (T e : inventory) {

            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    static <T> List<T> filterError(List<T> inventory, List<T> filtered, Predicate<T> predicate) {
        List<T> result = new ArrayList<T>();
        for (T e : inventory) {

            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
