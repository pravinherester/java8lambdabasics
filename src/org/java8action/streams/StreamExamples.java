package org.java8action.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExamples {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER), new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER), new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

        List<String> highCals = menu.stream()
                .filter(d -> d.getCalories() > 200)
                .sorted(java.util.Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(highCals);

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));

        List<String> alist = Arrays.asList("Jammy", "Rahul", "Dravid");
        Stream<String> s = alist.stream();
        s.forEach(System.out::println);
        // s.forEach(System.out::println);

        List<String> highCals1 = menu.stream()
                .filter(d -> {
                    System.out.println("filtering :" + d.getName());
                    return d.getCalories() > 200;
                })
                .map(d ->
                {
                    System.out.println( "map :"+d.getName());
                  return  d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());

    }

}
