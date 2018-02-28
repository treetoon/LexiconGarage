package com.lexicon.garage;

import java.util.ArrayList;
import java.util.List;

public class Garage extends GarageHandler {
    private int MAX_CARS;
    private List<Vehicle> vehicles = new ArrayList<>();

    public Garage(int MAX_CARS){
        this.MAX_CARS = MAX_CARS;
    }

    //set, getters
    public void setMAX_CARS(int MAX_CARS){
        this.MAX_CARS = MAX_CARS;
    }

    //functions
    public void addVehicle(Vehicle v){
        vehicles.add(v);
    }

    public void removeVehicle(){

    }
}
