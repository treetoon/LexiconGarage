package com.lexicon.garage;

public class Vehicle {
    private String regNum;
    private String color;
    private int numOfWheels;

    public Vehicle(){}

    public Vehicle(String regNum, String color, int numOfWheels){
        this.regNum = regNum;
        this.color = color;
        this.numOfWheels = numOfWheels;
    }
}
