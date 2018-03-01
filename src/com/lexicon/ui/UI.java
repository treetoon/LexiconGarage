package com.lexicon.ui;

import com.lexicon.garage.GarageHandler;
import com.lexicon.garage.vehicles.Airplane;
import com.lexicon.garage.vehicles.Boat;

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

                    //allGarages.get(currentGarage).addVehicle(new Airplane("ABB 022", "RED", 2, 8));
                    //allGarages.get(currentGarage).addVehicle(new Boat("FFD 123", "BLUE", 0, 78.78));
                    break;
                case 4:
                    System.out.println("Which vehicle would you like to unpark in the current garage?");
                    System.out.println("Write registration number: ");
                    break;
                case 5:
                    System.out.println("Resize the garage to how many parking spaces? The current size is: " /* + func() */);
                    System.out.println("WARNING: This may completely destroy and annihilate currently parked vehicles!");
                    System.out.println("Write parking spaces: ");
                    break;
                case 6:
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

    public void chooseVehicle(byte b){

    }
}
