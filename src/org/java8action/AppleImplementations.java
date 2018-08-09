package org.java8action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppleImplementations {
    public static List<Apple> inventory = Arrays.asList(new Apple(100, "yellow"), new Apple(400, "red"), new Apple(400, "green"));

    public static void main(String[] args) {
        FilterApples apples = new FilterApples();

        // Method refernce example
        apples.filterApplesStep1(inventory, new AppleGreenColour()::test);

        apples.filterApples(inventory, new AppleHeavyWeight());
        apples.filterApples(inventory, new AppleHeavyWeightRed());
        apples.printApples(inventory, new AppleSimpleFormatter());
        apples.printApples(inventory, new AppleFancyFormatter());

        /*
         * List<Apple> greenApples = apples.filterApplesStep1(inventory, (Apple a) -> "red".equals(a.getColour())); System.out.println(greenApples. () .map(Apple::getWeight)
         * .collect(Collectors.toList()));
         */

        // using comparing method for sorting custom objects
        inventory.sort(java.util.Comparator.comparing((Apple a) -> a.getWeight()));
        // using comparing method for sorting custom objects using Method reference
        inventory.sort(java.util.Comparator.comparing(Apple::getWeight));
        // using Lambda expression
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight()
                .compareTo(a2.getWeight()));

        inventory.sort((Apple a1, Apple a2) -> a1.getWeight()
                .compareTo(a2.getWeight()));
        System.out.println("Apples by weight-ReversedOrder");
        inventory.sort(java.util.Comparator.comparing(Apple::getWeight)
                .reversed());
        System.out.println(inventory.stream()
                .map(Apple::getColour)
                .collect(Collectors.toList()));
        System.out.println("Apples by weight-ReversedOrder and then by Colour");
        inventory.sort(java.util.Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColour));
        System.out.println(inventory.stream()
                .map(Apple::getColour)
                .collect(Collectors.toList()));

        List<String> fruits = Arrays.asList("Apple", "Orange", "JackFruit", "Bannana", "Pineapple");
        // fruits.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("Before Sorting the furits");
        System.out.println(fruits);
        fruits.sort(String::compareTo);

        System.out.println("After Sorting the furits");
        System.out.println(fruits);

        Comparator<Apple> byweight = (Apple a1, Apple a2) -> a1.getWeight()
                .compareTo(a2.getWeight());
        inventory.sort(byweight);
        System.out.println(inventory.stream()
                .map(Apple::getWeight)
                .collect(Collectors.toList()));

        Predicate<Apple> greaterthan150 = (Apple a) -> a.getWeight() > 2;
        List<Apple> apple15o = apples.filterApplesStep1(inventory, greaterthan150);
        System.out.println(apple15o.stream()
                .map(Apple::getWeight)
                .collect(Collectors.toList()));
    }

}

class AppleGreenColour implements ApplePredicate {

    public boolean test(Apple apple) {

        return "green".equals(apple.getColour());
    }
}

class AppleHeavyWeight implements ApplePredicate {

    public boolean test(Apple apple) {

        return apple.getWeight() > 150;
    }
}

class AppleHeavyWeightRed implements ApplePredicate {

    public boolean test(Apple a) {

        return a.getWeight() > 150 && "red".equals(a.getColour());
    }

}

class AppleSimpleFormatter implements ApplePrintPretty {

    @Override
    public String accept(Apple a) {
        // TODO Auto-generated method stub
        return a.getColour() + " Apple and  the weight is" + a.getWeight();
    }

}

class AppleFancyFormatter implements ApplePrintPretty {

    @Override
    public String accept(Apple a) {
        String characteristic = a.getWeight() > 150 ? "heavy" : "light";
        return a.getColour() + " Apple and  it is " + characteristic;
    }

}
