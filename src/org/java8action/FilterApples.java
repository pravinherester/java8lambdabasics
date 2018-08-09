package org.java8action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.java8action.*;

public class FilterApples {

    void filterApples(List<Apple> inventory, ApplePredicate predicate) {
        for (Apple apple : inventory) {

            if (predicate.test(apple)) {
                System.out.println(apple.getColour());
                System.out.println(apple.getWeight());
            }
        }

    }

    <T> List<T> filterApplesStep1(List<T> inventory, Predicate<T> predicate) {
        List<T> result = new ArrayList<T>();
        for (T e : inventory) {

            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    void printApples(List<Apple> inventory, ApplePrintPretty formatter) {
        for (Apple apple : inventory) {

            String output = formatter.accept(apple);
            System.out.println(output);

        }

    }

}
