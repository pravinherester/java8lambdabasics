package org.java8action;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class MethodReferenceExamples {
    public static void main(String[] args) {

        Function<String, Integer> stringtoInt = (s1) -> Integer.parseInt(s1);
        Integer convertedInt = stringtoInt.apply("5");

        Function<String, Integer> stringtoIntMethodRef = Integer::parseInt;
        Integer convertedIntMethodRef = stringtoIntMethodRef.apply("5");

        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
        System.out.println(contains.test(Arrays.asList("Pravin", "Rahul"), "Test"));
        
        BiPredicate<List<String>, String> containsMethodRef = List::contains;
        System.out.println(containsMethodRef.test(Arrays.asList("Pravin", "Rahul"), "Rahul"));
        
        
        BiFunction<String, String, String> s3=String::concat;
        BiFunction<String, String, String> s4= (s1,s2)->s1.concat(s2);
        System.out.println(s4);
        
        System.out.println(s3.apply("abc", "def"));
        
        
    }

}
