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

    /**
     * Returns a String of useful information about a specific vehicle
     * @return a String of vehicle data
     */
    @Override
    public abstract String toString();

    /**
     * Returns a message containing the color of the specified vehicle
     * @return a String message containing a color
     */
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
}
