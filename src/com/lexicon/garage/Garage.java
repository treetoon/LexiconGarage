package com.lexicon.garage;

import com.lexicon.garage.exceptions.VehiclesListOutOfBoundsException;
import com.lexicon.garage.exceptions.VehicleNotFoundException;
import com.lexicon.garage.vehicles.*;

import java.util.ArrayList;
import java.util.List;

public class Garage {
    private int maxCars; //max parking spots
    private List<Vehicle> vehiclesList = new ArrayList<>();

    public Garage(int maxCars){
        this.maxCars = maxCars;
    }

    //Getters & Setters

    public int getVehiclesListSize(){
        return vehiclesList.size();
    }

    //Functions

    /**
     * Changes the maximum capacity of the garage to the maxSize
     *
     * @param maxSize maximum size of the garage
     * @throws VehiclesListOutOfBoundsException
     */
    public void changeMaxCapacity(int maxSize) throws VehiclesListOutOfBoundsException{
        List<Vehicle> list = new ArrayList<>();

        if (maxSize >= 0) {
            if (maxSize < vehiclesList.size()) {

                //Add vehicles to the new list until maxSize
                for (Vehicle vehicle : vehiclesList) {
                    if (list.size() < maxSize) {
                        list.add(vehicle);
                    }
                }
            }
            this.maxCars = maxSize;
            vehiclesList = list;
        } else
            throw new VehiclesListOutOfBoundsException();
    }

    public void addVehicle(Vehicle v) throws VehiclesListOutOfBoundsException{
        if (!(vehiclesList.size() + 1 > maxCars)) {
            vehiclesList.add(v);
        } else {
            throw new VehiclesListOutOfBoundsException();
        }
    }

    public void removeVehicle(String regNr) {
        for (int i = 0; i < vehiclesList.size(); i++) {
            if (vehiclesList.get(i).getRegNum().equals(regNr)) {
                vehiclesList.remove(i);
                System.out.println("Vehicle with registration number " + regNr + " unparked");
                return;
            }
        }
        System.out.println("No vehicle unparked. Wrong registration number?");

    }

    public void printAllVehicles() throws VehicleNotFoundException {
        if(vehiclesList.isEmpty())
            throw new VehicleNotFoundException();

        for (Vehicle v : vehiclesList)
            System.out.println(v);
    }

    public void printAllVehicleTypes() throws VehicleNotFoundException {
        if(vehiclesList.isEmpty())
            throw new VehicleNotFoundException();

        boolean airplane = false, boat = false, bus = false,
                car = false, motorcycle = false;

        for (Vehicle v : vehiclesList)
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
        for (Vehicle vehicle : vehiclesList) {
            if (vehicle.getRegNum().equals(regNr)) {
                return vehicle;
            }
        }
        throw new VehicleNotFoundException();
    }

    public List<Vehicle> findVehicles(int numOfWheels) throws VehicleNotFoundException {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle vehicle : this.vehiclesList) {
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

        for (Vehicle vehicle : this.vehiclesList) {
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

    @Override
    public String toString(){
        return "Parking spots: " + maxCars;
    }
}
