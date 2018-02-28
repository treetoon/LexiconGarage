package com.lexicon.garage.vehicles;

import com.lexicon.garage.Vehicle;

public class Motorcycle extends Vehicle {
    private int length;

    public Motorcycle(String regNum, String color, int numOfWheels, int length){
        super(regNum, color, numOfWheels);
        this.length = length;
    }

    @Override
    public boolean hasColor(){
        if(color != null) {
            System.out.println("This motorcycle is " + color);
            return true;
        }
        return false;
    }
}
