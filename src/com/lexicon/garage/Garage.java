package com.lexicon.garage;

import com.lexicon.garage.exceptions.VehiclesListOutOfBoundsException;
import com.lexicon.garage.exceptions.VehicleNotFoundException;
import com.lexicon.garage.vehicles.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Garage implements Serializable {
    private int maxCars; //max parking spots
    private String name;
    private List<Vehicle> vehiclesList = new ArrayList<>();

    public Garage(int maxCars, String name){
        this.maxCars = maxCars;
        this.name = name;
    }

    public int getMaxCars(){
        return maxCars;
    }

    public int getVehiclesListSize(){
        return vehiclesList.size();
    }

    /**
     * Changes the maximum capacity of the Garage to the maxSize
     *
     * @param maxSize maximum size of the Garage
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
                vehiclesList = list;
            }
            this.maxCars = maxSize;

        } else
            throw new VehiclesListOutOfBoundsException();
    }

    /**
     * Adds a Vehicle to the ArrayList of Vehicles
     * @param v Vehicle to be added
     * @throws VehiclesListOutOfBoundsException
     */
    public void addVehicle(Vehicle v) throws VehiclesListOutOfBoundsException{
        if (!(vehiclesList.size() + 1 > maxCars)) {
            vehiclesList.add(v);
        } else {
            throw new VehiclesListOutOfBoundsException();
        }
    }

    /**
     * Removes a Vehicle from the ArrayList of Vehicles, indicated by the registration number
     * @param regNr registration number
     */
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

    /**
     * Prints all Vehicles
     * @throws VehicleNotFoundException
     */
    public void printAllVehicles() throws VehicleNotFoundException {
        if(vehiclesList.isEmpty())
            throw new VehicleNotFoundException();

        for (Vehicle v : vehiclesList)
            System.out.println(v);
    }

    /**
     * Prints the types of Vehicles in the ArrayList of Vehicles
     * @throws VehicleNotFoundException
     */
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

    /**
     * Finds a Vehicle by registration number
     * @param regNr registration number
     * @return found Vehicle
     * @throws VehicleNotFoundException
     */
    public Vehicle findVehicle(String regNr) throws VehicleNotFoundException {
        for (Vehicle vehicle : vehiclesList) {
            if (vehicle.getRegNum().equals(regNr)) {
                return vehicle;
            }
        }
        throw new VehicleNotFoundException();
    }

    /**
     * Finds a List of Vehicles by number of wheels
     * @param numOfWheels number of wheels
     * @return List of Vehicles
     * @throws VehicleNotFoundException
     */
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

    /**
     * Finds a List of Vehicles by color
     * @param color color of Vehicle
     * @return List of Vehicles
     * @throws VehicleNotFoundException
     */
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
    public String toString() {
        return  "Name: " + name + ", Max parking spots: " + maxCars;
    }
}
