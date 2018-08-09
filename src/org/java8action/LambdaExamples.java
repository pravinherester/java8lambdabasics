package org.java8action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
//import static org.testng.AssertJUnit.assertEquals;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LambdaExamples {

    void process(Runnable r) {
        r.run();
    }

    Callable<String> fetch() {
        return () -> "Hello World";

    }

    public static void main(String[] args) {
        LambdaExamples ob = new LambdaExamples();
        // Lambda Expression
        Runnable r1 = () -> System.out.println("Hello world1");
        // Anonymous class
        Runnable r2 = new Runnable() {
            public void run() {
                System.out.println("Hello World 2");
            }
        };
        ob.process(r1);
        ob.process(r2);
        ob.process(() -> System.out.println("Hello World3"));

        // Below lambda expression takes one attribute(String object here, and returns Integer). So the datatype of this
        // lambda expression is of type Function
        Function<String, Integer> ft = (String s) -> s.length();

        test("Pravin", ft);

        Predicate<Apple> greaterthan150 = (Apple a) -> a.getWeight() > 150;
        Predicate<Apple> negategreaterthan150 = greaterthan150.negate();
        System.out.println("Testing negagte");
        checkAppleWeight(new Apple(200, "green"), negategreaterthan150);
        Predicate<Apple> greaterthan200 = (Apple a) -> a.getWeight() > 200;
        Predicate<Apple> greaterthan300 = (Apple a) -> a.getWeight() > 300;
        Supplier<Apple> applecreation = () -> new Apple(300, "green");
        checkAppleWeight(new Apple(200, "green"), greaterthan150);
        checkAppleWeight(applecreation.get(), greaterthan200);
        checkAppleWeight(new Apple(100, "green"), greaterthan300);

        Comparator<Apple> byweight = (Apple a1, Apple a2) -> a1.getWeight()
                .compareTo(a2.getWeight());

        Comparator<String> stringCompare = (String s1, String s2) -> s2.compareTo(s1);
        IntUnaryOperator inttest = (int x) -> {
            if (x >= 0)
                return 1;
            return x;
        };

        Predicate<List<String>> list = (List<String> list1) -> list1.isEmpty();
        ArrayList<String> al = new ArrayList<String>();
        al.add("Pravin");
        al.add("Rahim");
        al.add("Karthik");
        al.add("Balaji");
        al.sort((s1, s2) -> s1.compareTo(s2));

        System.out.println(al);

        System.out.println("Predicate List empty?" + list.test(new ArrayList<String>()));

        System.out.println("Predicate List empty?" + list.test(al));

        String[] arr = new String[al.size()];
        arr = al.toArray(arr);
        Comparator<String> cmp = (first, second) -> Integer.compare(first.length(), second.length());
        Arrays.sort(arr, (first, second) -> Integer.compare(first.length(), second.length()));
        System.out.println(Arrays.asList(arr));

        IntBinaryOperator twonumberAddition = (int x, int y) -> {
            System.out.println("Result");
            System.out.println(x + y);
            return x + y;
        };

        BiConsumer<Integer, Integer> twoNumberAdditionnoreturn = (Integer x, Integer y) -> {
            System.out.println("Result");
            System.out.println(x + y);
        };
        twoNumberAdditionnoreturn.accept(5, 5);
        twonumberAddition.applyAsInt(5, 5);

        Map<String, Integer> nameMap = new HashMap<>();
        nameMap.put("Pravin", 5);
        Integer value = nameMap.computeIfAbsent("John", s -> s.length());
        Integer value1 = nameMap.computeIfPresent("Pravin", (k, v) -> nameMap.remove(k));
        System.out.println("value1==" + value1);
        System.out.println("nameMap===" + nameMap);
        // testAdd(5, 5, twonumberAddition);

        Function<Integer, String> intToString = Object::toString;
        Function<String, String> quote = s -> "'" + s + "'";
        Function<String, String> doublequote = s -> "\"" + s + "\"";

        Function<Integer, String> quoteIntToString = doublequote.compose(quote.compose(intToString));
        // assert("'5'", quoteIntToString.apply(5));
        System.out.println(quoteIntToString.apply(545));
        Supplier<Integer> sup = () -> 42;
        System.out.println(sup.get());

        Supplier<String> st = () -> "Rahul";
        System.out.println(st.get());

        Map<String, Integer> salaries = new HashMap<>();
        salaries.put("John", 40000);
        salaries.put("Freddy", 30000);
        salaries.put("Samuel", 50000);
        salaries.replaceAll((name, oldValue) -> name.equals("Freddy") ? oldValue : oldValue + 10000);
        System.out.println(salaries);

        // Runnable implementation:
        Runnable r = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Test" + i);

            }
        };
        // Thread execution
        new Thread(r).run();

        // Test MethodRefernce:
        TestClass test = new TestClass();
        test.testMethodReferenceImplementation(test::testmethodrefernce);

        // Test MethodReference2
        TestClass2 test1 = new TestClass2();
        test1.testMethodReferenceImplementation(test1::testmethodrefernce);

        // Scope
        int number = 5;
        Runnable r5 = () -> System.out.println(number);
        // number=6;

    }

    private static void testAdd(int i, int j, IntBinaryOperator twonumberAddition) {
        System.out.println(twonumberAddition.applyAsInt(i, j));

    }

    private static void checkAppleWeight(Apple i, Predicate<Apple> isgreaterthan) {
        System.out.println(isgreaterthan.test(i));
        
    }

    static void test(String s, Function<String, Integer> ft) {
        // apply method will make use of the lambda expression
        System.out.println(ft.apply(s));
    }
}
