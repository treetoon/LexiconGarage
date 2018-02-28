package com.lexicon.garage.vehicles;

import com.lexicon.garage.Vehicle;

public class Bus extends Vehicle {
    private int numOfSeats;

    public Bus(String regNum, String color, int numOfWheels, int numOfSeats){
        super(regNum, color, numOfWheels);
        this.numOfSeats = numOfSeats;
    }

    @Override
    public boolean hasColor(){
        if(color != null) {
            System.out.println("This bus is " + color);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Registration Number: " + regNum + ", Color: " + color + ", Wheels: "
                + numOfWheels + ", Seats: " + numOfSeats;
    }
}
