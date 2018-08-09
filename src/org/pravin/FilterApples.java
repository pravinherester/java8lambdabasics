package org.pravin;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilterApples {

    List<Apple> inventory = Arrays.asList(new Apple(150, "green"), new Apple(200, "red"), new Apple(300, "yellow"));

 /*   void filterGreenApples() {
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColour())) {
                System.out.println(apple.getColour());
            }
        }
    }

    void filterHeavyApples() {
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                System.out.println(apple.getColour() + " , " + apple.getWeight());
            }
        }

    }*/

    
    void filterApples(Predicate<Apple> p) {
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                System.out.println(apple.getColour() + " , " + apple.getWeight());
            }
        }

    }
    boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColour());
    }

    boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static void main(String[] args) {
        FilterApples ob = new FilterApples();
     //  ob.filterApples(ob::isGreenApple);
       //ob.filterApples(ob::isHeavyApple);
       ob.filterApples((Apple a)-> "green".equals(a.getColour()));
       ob.filterApples((Apple a)-> a.getWeight() > 150);
    }

}

class Apple {

    String colour;

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    int weight;

    Apple(int weight, String colour) {
        this.colour = colour;
        this.weight = weight;

    }

}