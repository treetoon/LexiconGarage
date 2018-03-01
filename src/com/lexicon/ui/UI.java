package com.lexicon.ui;

import com.lexicon.garage.GarageHandler;
import com.lexicon.garage.Vehicle;
import com.lexicon.garage.VehicleNotFoundException;
import com.lexicon.garage.vehicles.*;

import java.util.List;
import java.util.Scanner;

public class UI {
    private GarageHandler allGarages = null;

    public UI(){
        exec();
    }

    public static void main(String[] args){
        UI ui = new UI(); //run program
    }

    public void exec(){
        boolean run = true;
        int currentGarage = 0;

        System.out.println("-Welcome to Garage 1.0-");
        System.out.print("Write total parking spots: ");

        int totSpots = new Scanner(System.in).nextInt();
        System.out.println(totSpots + " parking spots created...");
        allGarages = new GarageHandler(totSpots);

        while (run) {
            listMainFunctions();

            int choice = new Scanner(System.in).nextInt();

            //List all parked vehicles in the current garage
            if (choice == 1) {
                System.out.println("All Parked Vehicles");
                allGarages.get(currentGarage).printAllVehicles();
            }
            else if (choice == 2) { //list specific parked vehicles
                System.out.println("Specific Parked vehicles");
            }
            else if (choice == 3) {
                System.out.println("What kind of vehicle would you like to park?");
                Scanner in=new Scanner(System.in);
                String type=in.next();

                System.out.println("Input registration number");
                String regNr=in.next();

                System.out.println("Input color");
                String color=in.next();

                if (type.toLowerCase().equals("airplane")) {
                    System.out.println("Input number of wheels");
                    int numOfWheels = in.nextInt();

                    System.out.println("Input number of engines");
                    int numOfEngines = in.nextInt();

                    allGarages.get(currentGarage).addVehicle(new Airplane(regNr, color, numOfWheels, numOfEngines));

                    System.out.println("Airplane parked!");
                } else if (type.toLowerCase().equals("boat")) {
                    System.out.println("Input cylinder volume");
                    double cylinderVolume = in.nextDouble();

                    allGarages.get(currentGarage).addVehicle(new Boat(regNr, color, 0, cylinderVolume));

                    System.out.println("Boat parked!");
                } else if (type.toLowerCase().equals("bus")) {
                    System.out.println("Input number of wheels");
                    int numOfWheels = in.nextInt();

                    System.out.println("Input number of seats");
                    int numOfSeats = in.nextInt();

                    allGarages.get(currentGarage).addVehicle(new Bus(regNr, color, numOfWheels, numOfSeats));

                    System.out.println("Bus parked!");
                } else if (type.toLowerCase().equals("car")) {
                    System.out.println("Input number of wheels");
                    int numOfWheels = in.nextInt();

                    System.out.println("Input fuel type");
                    String fuelType = in.next();

                    //Hur fixa enum FuelType??
                    //allGarages.get(currentGarage).addVehicle(new Car(regNr, color, numOfWheels, FuelType));

                    //System.out.println("Car parked!");
                } else if (type.toLowerCase().equals("motorcycle")) {
                    System.out.println("Input number of wheels");
                    int numOfWheels = in.nextInt();

                    System.out.println("Input length");
                    int length = in.nextInt();

                    allGarages.get(currentGarage).addVehicle(new Motorcycle(regNr, color, numOfWheels, length));

                    System.out.println("Motorcycle parked!");
                }

            }
            else if (choice == 4) { //unpark vehicle
                //allGarages.get(currentGarage).removeVehicle();
            }
            else if (choice == 5) {
                System.out.println("Set max capacity");
            } else if (choice == 6) {
                System.out.println("Input find parameter");
                System.out.println("1. Registration number");
                System.out.println("2. Number of wheels");
                System.out.println("3. Color");
                Scanner in = new Scanner(System.in);
                int findChoice = in.nextInt();

                if (findChoice==1) {
                    System.out.println("Input registration number to search for");
                    in = new Scanner(System.in);
                    String regNr = in.next();

                    try {
                        Vehicle vehicle=allGarages.get(currentGarage).findVehicle(regNr);
                        System.out.println(vehicle);

                    } catch (VehicleNotFoundException e) {
                        System.out.println("Vehicle not found");
                    }
                }
                else if (findChoice==2) {
                    System.out.println("Input number of wheels");
                    in = new Scanner(System.in);
                    int numOfWheels = in.nextInt();

                    try {
                        List<Vehicle> vehicles=allGarages.get(currentGarage).findVehicles(numOfWheels);
                        for (Vehicle v : vehicles) {
                            System.out.println(v);
                        }

                    } catch (VehicleNotFoundException e) {
                        System.out.println("Vehicle not found");
                    }
                } else if (findChoice == 3) {
                    System.out.println("Input color");
                    in = new Scanner(System.in);
                    String color = in.next();

                    try {
                        List<Vehicle> vehicles = allGarages.get(currentGarage).findVehicles(color);
                        for (Vehicle v : vehicles) {
                            System.out.println(v);
                        }

                    } catch (VehicleNotFoundException e) {
                        System.out.println("Vehicle not found");
                    }
                }

            }
            else if (choice == 7){
                System.out.println("Select Garage");
                //change currentGarage var
            }
            else if (choice == 8){
                System.out.println("Add Garages");
            }
            else if (choice == 9){
                System.out.println("Remove Garages");
            }
            else if (choice == 10){ //exit
                run = false;
            }
        }


    }

    public static void listMainFunctions() {
        System.out.println("--Garage functions--");
        System.out.println("1. List all parked vehicles");
        System.out.println("2. List all vehicle types parked");
        System.out.println("3. Park specific vehicle");
        System.out.println("4. Unpark specific vehicle");
        System.out.println("5. Set maximum capacity");
        System.out.println("6. Find vehicle");
        System.out.println("7. Quit");
    }


}
