package com.lexicon.garage;

import com.lexicon.garage.vehicles.*;

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

    public void printAllVehicleTypes() throws VehicleNotFoundException {
        if(vehicles.isEmpty())
            throw new VehicleNotFoundException();

        boolean airplane = false, boat = false, bus = false,
                car = false, motorcycle = false;

        for (Vehicle v : vehicles)
        {
            if(Airplane.class == v.getClass()){
                airplane = true;
            }else if(Boat.class == v.getClass()){
                boat = true;
            }else if(Bus.class == v.getClass()){
                bus = true;
            }else if(Car.class == v.getClass()){
                car = true;
            }else if(Motorcycle.class == v.getClass()){
                motorcycle = true;
            }
        }

        if(airplane) System.out.println("Aeroplane");
        if(boat) System.out.println("Boat");
        if(bus) System.out.println("Bus");
        if(car) System.out.println("Car");
        if(motorcycle) System.out.println("Motorcycle");

        if(!airplane && !boat && !bus && !car && !motorcycle)
            throw new VehicleNotFoundException();
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
