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

    public void removeVehicle(int index){
        vehicles.remove(index);
    }

    public void printAllVehicles() throws VehicleNotFoundException {
        if(vehicles.isEmpty())
            throw new VehicleNotFoundException();

        for (Vehicle v : vehicles)
            System.out.println(v);
    }

    public Vehicle findVehicle(String regNr) throws VehicleNotFoundException {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegNum().equals(regNr)) {
                return vehicle;
            }
        }
        throw new VehicleNotFoundException();
    }

    public List<Vehicle> findVehicles(int numOfWheels) throws VehicleNotFoundException {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.getNumOfWheels() == numOfWheels) {
                vehicles.add(vehicle);
            }
        }

        if (vehicles.size() != 0) {
            return vehicles;
        } else {
            throw new VehicleNotFoundException();
        }
    }

    public List<Vehicle> findVehicles(String color) throws VehicleNotFoundException {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.getColor().equals(color)) {
                vehicles.add(vehicle);
            }
        }

        if (vehicles.size() != 0) {
            return vehicles;
        } else {
            throw new VehicleNotFoundException();
        }
    }
}
