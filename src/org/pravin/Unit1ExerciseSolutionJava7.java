package org.pravin;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Unit1ExerciseSolutionJava7 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person("Pravin", "Jagadeesan", 32), new Person("Rahul", "Dravid", 43), new Person("Sachin", "Tendulkar", 44), new Person("Mahendar", "Dhoni", 36),
                new Person("Virat", "Hholi", 29));

        Collections.sort(people, new PersonSortByName());
        printall(people);
        printLastNameConditionally(people, new Predicate<Person>() {

            @Override
            public boolean test(Person p) {
                return p.getFirstName()
                        .startsWith("R");
            }
        });
        // System.out.println(people);

    }

    private static void printall(List<Person> people) {
        for (Person person : people) {
            System.out.println(person);

        }

    }

    private static void printLastNameConditionally(List<Person> people, Predicate<Person> predicate) {
        for (Person person : people) {
            if (predicate.test(person))
                System.out.println(person);

        }
    }

}

class PersonSortByName implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        // TODO Auto-generated method stub
        return o1.getFirstName()
                .compareTo(o2.getFirstName());
    }

}

