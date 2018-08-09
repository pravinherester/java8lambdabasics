package org.java8action;

public class Apple {
    String colour;

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    Integer weight;

    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    Apple(int weight, String colour) {
        this.colour = colour;
        this.weight = weight;

    }

    Apple() {
        this.colour = "Red";
        this.weight = 100;

    }

    Apple(int weight, String colour, String type) {
        this.colour = colour;
        this.weight = weight;
        this.type = type;

    }
}
