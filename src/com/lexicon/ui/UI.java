package com.lexicon.ui;

import com.lexicon.garage.GarageHandler;
import com.lexicon.garage.Vehicle;
import com.lexicon.garage.exceptions.GaragesListOutOfBoundsException;
import com.lexicon.garage.exceptions.VehiclesListOutOfBoundsException;
import com.lexicon.garage.exceptions.VehicleNotFoundException;
import com.lexicon.garage.vehicles.*;
import com.lexicon.garage.vehicles.types.FuelType;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class UI {
    private GarageHandler allGarages = null;
    private int currentGarage = 0;

    public UI() { printMenu();
    }

    public static void main(String[] args) {
        UI ui = new UI(); //run program
    }

    public void printMenu() {
        boolean run = true;
        String firstGarageName;

        System.out.println("---Welcome to Garage 0.1---");

        int totSpots = 0;

        do{
            if (new File("garages.txt").exists()) {
                allGarages=new GarageHandler();
                allGarages.readFile();
           } else {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Give the garage a name: ");
                firstGarageName = scanner.next();

                System.out.print("Write the total parking spots in your first garage: ");
                totSpots = scanner.nextInt();

                allGarages = new GarageHandler(totSpots, firstGarageName);
            }

        }while (allGarages.getGarageListSize() <= 0);

        System.out.println(totSpots + " parking spots created inside one garage...");

        Scanner sc = new Scanner(System.in);
        byte choice = 0;

        while (run) {
            menuDisplay();
            choice = sc.nextByte();

            switch (choice) {
                case 1:
                    menuDisplay_AllVehicles();
                    break;
                case 2:
                    menuDisplay_AllVehicleTypes();
                    break;
                case 3:
                    menuDisplay_ParkVehicle();
                    break;
                case 4:
                    menuDisplay_UnparkVehicle();
                    break;
                case 5:
                    menuDisplay_setGarageSize();
                    break;
                case 6:
                    menuDisplay_searchByVehicleProperty();
                    break;
                case 7:
                    menuDisplay_selectGarage();
                    break;
                case 8:
                    menuDisplay_addOneGarage();
                    break;
                case 9:
                    menuDisplay_RemoveOneGarage();
                    break;
                case 10:
                    menuDisplay_saveToFile();
                    break;
                case 11:
                    run = false; //exit
                    break;
            }
        }
    }

    private void menuDisplay() {
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
        System.out.println("10. Save to file");
        System.out.println("11. Quit");
        System.out.println();
        System.out.print("Write to select: ");

    }

    private void menuDisplay_AllVehicles(){
        System.out.println("Listing all parked vehicles in the current garage: ");

        try {
            allGarages.get(currentGarage).printAllVehicles();
        }catch (VehicleNotFoundException e){
            System.out.println("No vehicles to list...");
        }
    }

    private void menuDisplay_AllVehicleTypes(){
        System.out.println("Listing all parked vehicle types in the current garage: ");

        try {
            allGarages.get(currentGarage).printAllVehicleTypes();
        }catch (VehicleNotFoundException e){
            System.out.println("No vehicle types to list...");
        }
    }

    private void menuDisplay_ParkVehicle(){
        System.out.println("What kind of vehicle would you like to park in the current garage?");
        System.out.println("Choose type (1. Aeroplane, 2. Boat, 3. Bus, 4. Car, 5. Motorcycle): ");

        parkVehicle(new Scanner(System.in).nextByte(), currentGarage);
    }

    private void menuDisplay_UnparkVehicle(){
        System.out.println("Which vehicle would you like to unpark in the current garage?");
        System.out.println("Write registration number: ");
        unParkVehicle(new Scanner(System.in).next(), currentGarage);
    }

    private void menuDisplay_setGarageSize(){
        System.out.println("Resize the garage to how many parking spaces? There are currently " +
                allGarages.get(currentGarage).getVehiclesListSize() +
                " parked vehicles, and the size of the garage is " + allGarages.get(currentGarage).getMaxCars());
        System.out.println("WARNING: This may completely destroy and annihilate currently parked vehicles!");
        System.out.print("Write parking spaces: ");

        int input = new Scanner(System.in).nextInt();

        try {
            allGarages.get(currentGarage).changeMaxCapacity(input);
            System.out.println("Changed max capacity");
        }catch (VehiclesListOutOfBoundsException e){
            System.out.println("Invalid size...");
        }
    }

    private void menuDisplay_searchByVehicleProperty(){
        System.out.println("What kind of property would you like to search by?");
        System.out.println("Choose type (1. Registration number, 2. Number of wheels, 3. Color): ");
        findVehicle(new Scanner(System.in).nextByte(), currentGarage);
    }

    private void menuDisplay_selectGarage(){
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
    }

    private void menuDisplay_addOneGarage(){
        Scanner in = new Scanner(System.in);

        System.out.print("What do you want the garage to be called? ");
        String name = in.next();

        System.out.print("How many parking spots would you like in your new garage? ");
        int parkingSpots = in.nextInt();

        try {
            allGarages.addGarage(parkingSpots, name);
            System.out.print("Garage created...");
        } catch (VehiclesListOutOfBoundsException e) {
            System.out.println("Creating garage failed...");
        }
    }

    private void menuDisplay_RemoveOneGarage(){
        try {
            allGarages.printAllGarages();
        } catch (GaragesListOutOfBoundsException e) {
            System.out.println("Printing error..");
        }
        System.out.println("Which garage would you like to remove? ");
        int garageIndex = new Scanner(System.in).nextInt();

        try {
            if (currentGarage != garageIndex) {
                allGarages.removeGarage(garageIndex);

                if (allGarages.getGarageListSize() >= garageIndex-1) {
                    currentGarage--;
                }

                System.out.println("Garage " + garageIndex + " removed!");
            } else {
                System.out.println("You chosed the current garage. You have to select another garage before removing current garage!");
            }
        } catch (VehiclesListOutOfBoundsException e) {
            System.out.println("Removing garage failed...");
        }
    }

    private void menuDisplay_saveToFile(){
        allGarages.writeFile();
    }

    /**
    * Unparks a specific Vehicle (indicated by registration number) in the current Garage
    * @param regNr registration number
    * @param currentGarage index of the current Garage
    */
    public void unParkVehicle(String regNr, int currentGarage) {
        allGarages.get(currentGarage).removeVehicle(regNr);
    }

    /**
     * Finds a Vehicle in the current Garage
     * @param input indicating the function the user has chosen
     * @param currentGarage index of the current Garage
     */
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

    /**
     * Parks a specific Vehicle in the current Garage
     * @param input input indicating the type of Vehicle
     * @param currentGarage index of current Garage
     */
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
