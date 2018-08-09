package org.pravin.unit2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.pravin.Person;

public class StandardFunctinoalInterface2 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person("Pravin", "Jagadeesan", 32), new Person("Rahul", "Dravid", 43), new Person("Sachin", "Tendulkar", 44), new Person("Mahendar", "Dhoni", 36),
                new Person("Virat", "Hholi", 29));
        Comparator<Person> c = (p1, p2) -> p1.getFirstName()
                .compareTo(p2.getFirstName());

        Collections.sort(people, c);
        performConditionally(people, p -> true, p -> System.out.println(p));

        performConditionally(people, p -> p.getFirstName()
                .startsWith("P"), p -> System.out.println(p.getFirstName()));
        // System.out.println(people);

    }

    private static void printall(List<Person> people) {
        for (Person person : people) {
            System.out.println(person);

        }

    }

    private static void performConditionally(List<Person> people, Predicate<Person> condition, Consumer<Person> consumer) {
        for (Person person : people) {
            if (condition.test(person))
                consumer.accept(person);

        }
    }

}
