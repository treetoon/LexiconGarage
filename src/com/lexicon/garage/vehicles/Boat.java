package com.lexicon.garage.vehicles;

import com.lexicon.garage.Vehicle;

public class Boat extends Vehicle {
    private double cylinderVolume;

    public Boat(String regNum, String color, int numOfWheels, double cylinderVolume){
        super(regNum, color, numOfWheels);
        this.cylinderVolume = cylinderVolume;
    }

    @Override
    public boolean hasColor(){
        if(color != null) {
            System.out.println("This boat is " + color);
            return true;
        }
        return false;
    }
}
