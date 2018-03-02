package com.lexicon.ui;

import com.lexicon.garage.Garage;
import com.lexicon.garage.GarageHandler;
import com.lexicon.garage.Vehicle;
import com.lexicon.garage.exceptions.GaragesListOutOfBoundsException;
import com.lexicon.garage.exceptions.VehiclesListOutOfBoundsException;
import com.lexicon.garage.exceptions.VehicleNotFoundException;
import com.lexicon.garage.vehicles.*;

import java.util.List;
import java.util.Scanner;

public class UI {
    private GarageHandler allGarages = null;

    public UI() {
        printMenu();
    }

    public static void main(String[] args) {
        UI ui = new UI(); //run program
    }

    public void printMenu() {
        boolean run = true;
        int currentGarage = 0;

        System.out.println("---Welcome to Garage 1.0---");

        int totSpots = 0;

        do{
            System.out.print("Write the total parking spots in your first garage: ");

            totSpots = new Scanner(System.in).nextInt();
            allGarages = new GarageHandler(totSpots);
        }while (allGarages.getGarageListSize() <= 0);

        System.out.println(totSpots + " parking spots created inside one garage...");

        Scanner sc = new Scanner(System.in);
        byte choice = 0;

        while (run) {
            listMainFunctions();
            choice = sc.nextByte();

            switch (choice) {
                case 1:
                    System.out.println("Listing all parked vehicles in the current garage: ");

                    try {
                        allGarages.get(currentGarage).printAllVehicles();
                    }catch (VehicleNotFoundException e){
                        System.out.println("No vehicles to list...");
                    }
                    break;
                case 2:
                    System.out.println("Listing all parked vehicle types in the current garage: ");

                    try {
                        allGarages.get(currentGarage).printAllVehicleTypes();
                    }catch (VehicleNotFoundException e){
                        System.out.println("No vehicle types to list...");
                    }
                    break;
                case 3:
                    System.out.println("What kind of vehicle would you like to park in the current garage?");
                    System.out.println("Choose type (1. Aeroplane, 2. Boat, 3. Bus, 4. Car, 5. Motorcycle): ");

                    parkVehicle(new Scanner(System.in).nextByte(), currentGarage);
                    break;
                case 4:
                    System.out.println("Which vehicle would you like to unpark in the current garage?");
                    System.out.println("Write registration number: ");
                    unParkVehicle(new Scanner(System.in).next(), currentGarage);
                    break;
                case 5:
                    System.out.println("Resize the garage to how many parking spaces? The current size is: "
                            + allGarages.get(currentGarage).getVehiclesListSize());
                    System.out.println("WARNING: This may completely destroy and annihilate currently parked vehicles!");
                    System.out.println("Write parking spaces: ");

                    int input = new Scanner(System.in).nextInt();

                    try {
                        allGarages.get(currentGarage).changeMaxCapacity(input);
                    }catch (VehiclesListOutOfBoundsException e){
                        System.out.println("Invalid size...");
                    }
                    break;
                case 6:
                    System.out.println("What kind of property would you like to search by?");
                    System.out.println("Choose type (1. Registration number, 2. Number of wheels, 3. Color): ");
                    findVehicle(new Scanner(System.in).nextByte(), currentGarage);
                    break;
                case 7:
                    System.out.println("Listing all garages by their ID: ");

                    try {
                        allGarages.printAllGarages();

                        System.out.println("Write the ID to select: ");
                        int id = new Scanner(System.in).nextInt();

                        if(allGarages.isIdValid(id)){
                            currentGarage = id;
                            System.out.println("Garage with ID of " + id + " selected...");
                        }
                    }catch (GaragesListOutOfBoundsException e){
                        System.out.println("Garage list out of bounds...");
                    }
                    break;
                case 8:
                    System.out.print("How many parking spots would you like in your new garage?");
                    int parkingSpots = new Scanner(System.in).nextInt();

                    try {
                        allGarages.addGarage(parkingSpots);
                    } catch (VehiclesListOutOfBoundsException e) {
                        System.out.println("Creating garage failed");
                    }
                    break;
                case 9:
                    try {
                        allGarages.printAllGarages();
                    } catch (GaragesListOutOfBoundsException e) {
                        System.out.println("Printing error..");
                    }
                    System.out.println("Which garage would you like to remove?");
                    int garageIndex = new Scanner(System.in).nextInt();

                    try {
                        if (currentGarage != garageIndex) {
                            allGarages.removeGarage(garageIndex);
                        } else {
                            System.out.println("You chosed the current garage. You have to select another garage before removing current garage!");
                        }
                        System.out.println("Garage " + garageIndex + " removed!");
                    } catch (VehiclesListOutOfBoundsException e) {
                        System.out.println("Removing garage failed");
                    }

                    break;
                case 10:
                    run = false; //exit
                    break;
            }
        }
    }

    public void listMainFunctions() {
        System.out.println();
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
        System.out.println();
    }

    public void unParkVehicle(String regNr, int currentGarage) {
        allGarages.get(currentGarage).removeVehicle(regNr);
    }

    public void findVehicle(byte input, int currentGarage) {
        Scanner sc = new Scanner(System.in);

        if (input == 1) {
            System.out.println("Write the registration number to search for: ");
            String regNr = sc.next();

            try {
                Vehicle vehicle = allGarages.get(currentGarage).findVehicle(regNr);
                System.out.println(vehicle);
            } catch (VehicleNotFoundException e) {
                System.out.println("Vehicle not found...");
            }
        } else if (input == 2) {
            System.out.println("Write the number of wheels: ");
            int numOfWheels = sc.nextInt();

            try {
                List<Vehicle> vehicles = allGarages.get(currentGarage).findVehicles(numOfWheels);

                for (Vehicle v : vehicles) {
                    System.out.println(v);
                }
            } catch (VehicleNotFoundException e) {
                System.out.println("No vehicles found...");
            }
        } else if (input == 3) {
            System.out.println("Write the color: ");
            String color = sc.next();

            try {
                List<Vehicle> vehicles = allGarages.get(currentGarage).findVehicles(color);
                for (Vehicle v : vehicles) {
                    System.out.println(v);
                }

            } catch (VehicleNotFoundException e) {
                System.out.println("No vehicles found...");
            }
        }
    }

    public void parkVehicle(byte input, int currentGarage) {
        System.out.println("Input registration number");

        Scanner in = new Scanner(System.in);
        String regNr = in.next();

        System.out.println("Input color");
        String color = in.next();

        if (input == 1) {
            System.out.println("Input number of wheels");
            int numOfWheels = in.nextInt();

            System.out.println("Input number of engines");
            int numOfEngines = in.nextInt();

            try {
                allGarages.get(currentGarage).addVehicle(new Airplane(regNr, color, numOfWheels, numOfEngines));
                System.out.println("Aeroplane parked!");
            } catch (VehiclesListOutOfBoundsException e) {
                System.out.println("Parking vehicle failed!");
            }
        } else if (input == 2) {
            System.out.println("Input cylinder volume");
            double cylinderVolume = in.nextDouble();

            try {
                allGarages.get(currentGarage).addVehicle(new Boat(regNr, color, 0, cylinderVolume));
                System.out.println("Boat parked!");
            } catch (VehiclesListOutOfBoundsException e) {
                System.out.println("Parking vehicle failed!");
            }
        } else if (input == 3) {
            System.out.println("Input number of wheels");
            int numOfWheels = in.nextInt();

            System.out.println("Input number of seats");
            int numOfSeats = in.nextInt();

            try {
                allGarages.get(currentGarage).addVehicle(new Bus(regNr, color, numOfWheels, numOfSeats));
                System.out.println("Bus parked!");
            } catch (VehiclesListOutOfBoundsException e) {
                System.out.println("Parking vehicle failed!");
            }
        } else if (input == 4) {
            System.out.println("Input number of wheels");
            int numOfWheels = in.nextInt();

            System.out.println("Input fuel type");
            System.out.println("1. Gasoline");
            System.out.println("2. Diesel");
            System.out.println("3. Autogas");
            System.out.println("4. Biodiesel");
            System.out.println("5. Ethanol");

            byte fuelChoice = in.nextByte();

            if (fuelChoice == 1) {
                try {
                    allGarages.get(currentGarage).addVehicle(new Car(regNr, color, numOfWheels, FuelType.GASOLINE));
                    System.out.println("Car parked!");
                } catch (VehiclesListOutOfBoundsException e) {
                    System.out.println("Parking vehicle failed!");
                }
            } else if (fuelChoice == 2) {
                try {
                    allGarages.get(currentGarage).addVehicle(new Car(regNr, color, numOfWheels, FuelType.DIESEL));
                    System.out.println("Car parked!");
                } catch (VehiclesListOutOfBoundsException e) {
                    System.out.println("Parking vehicle failed!");
                }
            } else if (fuelChoice == 3) {
                try {
                    allGarages.get(currentGarage).addVehicle(new Car(regNr, color, numOfWheels, FuelType.AUTOGAS));
                    System.out.println("Car parked!");
                } catch (VehiclesListOutOfBoundsException e) {
                    System.out.println("Parking vehicle failed!");
                }
            } else if (fuelChoice == 4) {
                try {
                    allGarages.get(currentGarage).addVehicle(new Car(regNr, color, numOfWheels, FuelType.BIODIESEL));
                    System.out.println("Car parked!");
                } catch (VehiclesListOutOfBoundsException e) {
                    System.out.println("Parking vehicle failed!");
                }
            } else if (fuelChoice == 5) {
                try {
                    allGarages.get(currentGarage).addVehicle(new Car(regNr, color, numOfWheels, FuelType.ETHANOL));
                    System.out.println("Car parked!");
                } catch (VehiclesListOutOfBoundsException e) {
                    System.out.println("Parking vehicle failed!");
                }
            }
        } else if (input == 5) {
            System.out.println("Input number of wheels");
            int numOfWheels = in.nextInt();

            System.out.println("Input length");
            int length = in.nextInt();

            try {
                allGarages.get(currentGarage).addVehicle(new Motorcycle(regNr, color, numOfWheels, length));
                System.out.println("Motorcycle parked!");
            } catch (VehiclesListOutOfBoundsException e) {
                System.out.println("Parking vehicle failed!");
            }
        }

    }
}
