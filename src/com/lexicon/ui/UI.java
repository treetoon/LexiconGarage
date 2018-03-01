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
        printMenu();
    }

    public static void main(String[] args){
        UI ui = new UI(); //run program
    }

    public void printMenu(){
        boolean run = true;
        int currentGarage = 0;

        System.out.println("-Welcome to Garage 1.0-");
        System.out.print("Write total parking spots: ");

        int totSpots = new Scanner(System.in).nextInt();
        System.out.println(totSpots + " parking spots created inside one garage...");
        allGarages = new GarageHandler(totSpots);

        Scanner sc = new Scanner(System.in);
        byte choice = 0;

        while (run) {
            listMainFunctions();
            choice = sc.nextByte();


            switch (choice){
                case 1:
                    System.out.println("Listing all parked vehicles in the current garage: ");
                    allGarages.get(currentGarage).printAllVehicles();
                    break;
                case 2:
                    System.out.println("Listing all parked vehicle types in the current garage: ");
                    break;
                case 3:
                    System.out.println("What kind of vehicle would you like to park in the current garage?");
                    System.out.println("Choose type (Aeroplane, Boat, Bus, Car, Motorcycle), 1 - 5: ");

                    chooseVehicle(new Scanner(System.in).nextByte());
                    break;
                case 4:
                    System.out.println("Which vehicle would you like to unpark in the current garage?");
                    System.out.println("Write registration number: ");
                    //allGarages.get(currentGarage).removeVehicle();
                    break;
                case 5:
                    System.out.println("Resize the garage to how many parking spaces? The current size is: " /* + func() */);
                    System.out.println("WARNING: This may completely destroy and annihilate currently parked vehicles!");
                    System.out.println("Write parking spaces: ");
                    break;
                case 6:
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
                    break;
                case 7:
                    //change currentGarage var
                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:
                    run = false; //exit
                    break;
            }
        }
    }

    public void listMainFunctions() {
        System.out.println("--Garage functions--");
        System.out.println("1. List all parked vehicles in the garage");
        System.out.println("2. List all vehicle types parked in the garage");
        System.out.println("3. Park a specific vehicle");
        System.out.println("4. Unpark a specific vehicle");
        System.out.println("5. Set number of parking spaces in the garage");
        System.out.println("6. Search by vehicle property, i.e registration number or other");
        System.out.println("7. Select a garage to be used");
        System.out.println("8. Add one garage");
        System.out.println("9. Remove one garage");
        System.out.println("10. Quit");
    }

    public void chooseVehicle(byte input){
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
    }
}
