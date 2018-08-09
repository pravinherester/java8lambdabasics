package org.java8action.FPTechniques;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.java8action.Apple;
import org.java8action.AppleImplementations;

public class ClassB {
    DTO obj = new DTO();

    void testmethodA() {

        obj.setTest("Pravin");
    }

    void testmethodB() {

        obj.setTest("Rravin");
    }

    void testMethodC() {
        System.out.println(obj.getTest());
    }

    public static void main(String[] args) {
        ClassB obj1 = new ClassB();
        obj1.testmethodA();
        obj1.testmethodB();
        obj1.testMethodC();

        String.join(",", "pravin,rahul");
        Function<String, Integer> strToInt = Integer::parseInt;

        obj1.testMethodD(strToInt, "1");
        Comparator<Apple> co = Comparator.comparing(org.java8action.Apple::getColour);
        List<Apple> apples = AppleImplementations.inventory;
        apples.sort(co);

        List<String> sorted = apples.stream()
                .map(Apple::getColour)
                .collect(Collectors.toList());

        System.out.println(sorted);

        System.out.println(AppleImplementations.inventory);

        DoubleUnaryOperator milestokms = obj1.curriedConverter(0.6, 0);
        DoubleUnaryOperator convertUSDtoGBP = obj1.curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = obj1.curriedConverter(0.6214, 0);
        System.out.println(milestokms.applyAsDouble(1));
    }

    private void testMethodD(Function<String, Integer> strToInt, String test) {
        Integer in = strToInt.apply(test);
        System.out.println(in);

    }

    private DoubleUnaryOperator curriedConverter(Double f, int b) {
        return (double x) -> x * f + b;
    }

}
