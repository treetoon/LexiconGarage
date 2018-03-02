package com.lexicon.garage.vehicles;

import com.lexicon.garage.Vehicle;

public class Airplane extends Vehicle {
    private int numOfEngines;

    public Airplane(String regNum, String color, int numOfWheels, int numOfEngines){
        super(regNum, color, numOfWheels);
        this.numOfEngines = numOfEngines;
    }

    @Override
    public boolean hasColor() {
        if(color != null) {
            System.out.println("This airplane is " + color);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Registration Number: " + regNum + ", Color: " + color + ", Wheels: "
                + numOfWheels + ", Engines: " + numOfEngines;
    }
}
