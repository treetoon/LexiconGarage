package com.lexicon.garage.vehicles;

import com.lexicon.garage.Vehicle;

public class Car extends Vehicle {
    private FuelType GASOLINE;

    public Car(String regNum, String color, int numOfWheels, FuelType GASOLINE){
        super(regNum, color, numOfWheels);
        this.GASOLINE = GASOLINE;
    }

    @Override
    public boolean hasColor(){
        if(color != null) {
            System.out.println("This car is " + color);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Registration Number: " + regNum + ", Color: " + color + ", Wheels: "
                + numOfWheels + ", Gasoline: " + GASOLINE;
    }
}
