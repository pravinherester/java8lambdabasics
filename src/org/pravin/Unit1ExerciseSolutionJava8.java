package org.pravin;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Unit1ExerciseSolutionJava8 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person("Pravin", "Jagadeesan", 32), new Person("Rahul", "Dravid", 43), new Person("Sachin", "Tendulkar", 44), new Person("Mahendar", "Dhoni", 36),
                new Person("Virat", "Hholi", 29));
        Comparator<Person> c = (p1, p2) -> p1.getFirstName()
                .compareTo(p2.getFirstName());

        Collections.sort(people, c);
        printLastNameConditionally(people, p -> true);

        printLastNameConditionally(people, p -> p.getFirstName()
                .startsWith("P"));
        // System.out.println(people);

    }

    private static void printall(List<Person> people) {
        for (Person person : people) {
            System.out.println(person);

        }

    }

    private static void printLastNameConditionally(List<Person> people, Condition  condition) {
        for (Person person : people) {
            if (condition.test(person))
                System.out.println(person);

        }
    }

}


interface Condition 
{
    boolean test(Person P);
}
