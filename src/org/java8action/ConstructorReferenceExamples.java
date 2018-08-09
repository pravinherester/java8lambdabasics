package org.java8action;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorReferenceExamples {
    public static void main(String[] args) {

        // Constructor Reference 2 param
        BiFunction<Integer, String, Apple> aapobj = Apple::new;
        Apple apple = aapobj.apply(100, "Red");

        BiFunction<Integer, String, Apple> aapobj1 = (weight, colour) -> new Apple(weight, colour);
        Apple apple1 = aapobj1.apply(100, "Red");

        // Constructor Reference no param default constr
        Supplier<Apple> sup = () -> new Apple();
        Apple noparamObj = sup.get();
        System.out.println(noparamObj.getColour());
        // Custom Fuctional Interface
        TriFunction<Integer, String, String, Apple> triapp = Apple::new;
        System.out.println(triapp.apply(400, "Green", "Salem")
                .getType());
        
        
      Apple salem=test.get("Salem").apply(300, "Green");
      Apple kashmir=test.get("Kashmir").apply(200, "Red");

    }

    static Map<String, BiFunction<Integer, String,Apple>> test = null;
    static{
        test.put("Salem", Apple::new);
        test.put("Kashmir", Apple::new);
    }

    
}

@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
