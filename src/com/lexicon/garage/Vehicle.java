package com.lexicon.garage;

public abstract class Vehicle {
    protected String regNum;
    protected String color;
    protected int numOfWheels;

    public Vehicle(String regNum, String color, int numOfWheels){
        this.regNum = regNum;
        this.color = color;
        this.numOfWheels = numOfWheels;
    }

    public abstract boolean hasColor();

    //Getters & Setters

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getRegNum() {
        return regNum;
    }

    public int getNumOfWheels() {
        return numOfWheels;
    }

    public String getColor() {
        return color;
    }

    @Override
    public abstract String toString();
}
