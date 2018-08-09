package org.java8action.optionals;

import java.net.NetworkInterface;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Predicate;

public class OptionalExample {
    static Insurance ins;

    public static void main(String[] args) {

        Optional<Person> optp1 = Optional.of(new Person());
        Car carob = null;
        Insurance insur = null;
        /*
         * Optional<Car> carthorwNullpointer = Optional.of(carob); Optional<Insurance> insthrowNullPointer = Optional.of(insur);
         * 
         * Optional<String> name = insthrowNullPointer.map(Insurance::getName); System.out.println(name); System.out.println(insthrowNullPointer); System.out.println(carthorwNullpointer);
         */

        carob = null;
        insur = null;
        Optional<Car> carallowNull = Optional.ofNullable(carob);
        Optional<Insurance> insallowNull = Optional.ofNullable(insur);
        Optional<String> name = insallowNull.map(Insurance::getName);
        /*
         * System.out.println(name); System.out.println(carallowNull); System.out.println(insallowNull);
         */

        String name1 = optp1.filter(p -> p.getAge() == 25)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
        System.out.println(name1);

        Optional<Insurance> ins1 = Optional.ofNullable(new Insurance());
        ins1.filter(insurance -> "hellp".equals(insurance.getName()))
                .ifPresent(x -> System.out.println("ok"));

        String s = "2";
        System.out.println(Optional.ofNullable(s)
                .flatMap(OptionalExample::stringtoInt));

        Properties prop = new Properties();
        prop.setProperty("a", "5");
        prop.setProperty("b", "true");
        prop.setProperty("c", "-1");
        System.out.println(readDuration1(prop, "a"));
    }

    public static int readDuration1(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalExample::stringtoInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

    public static int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        return 0;
    }

    public static Optional<String> getCarInsuranceName(Optional<Insurance> opt) {

        // Optional<Insurance> opt = Optional.of(insurance);
        Optional<String> name = opt.map(Insurance::getName);
        System.out.println(name.isPresent() ? name.get() : name);
        return name;
    }

    /*
     * public static Optional<Insurance> nullSafeCheepestInsurance(Optional<Person> person, Optional<Car> car) {
     * 
     * return person.flatMap(p -> car.map(c -> findCheepestInsurance(Optional.of(p), Optional.of(c))));
     * 
     * 
     * }
     * 
     * public static Optional<Insurance> findCheepestInsurance(Optional<Person> person, Optional<Car> car) {
     * 
     * return Optional.of(new Insurance()); }
     */

    public static Optional<Integer> stringtoInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        }

        catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

}

class Person {
    private Optional<Car> car;
    private int age;

    public Optional<Car> getCar() {
        return Optional.of(new Car());
    }

    public int getAge() {
        return 25;
    }

}

class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {

        return Optional.of(new Insurance());
    }

}

class Insurance {
    private String name;

    public String getName() {
        return "hell";
    }

}