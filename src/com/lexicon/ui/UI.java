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
                System.out.println("Park a vehicle");
                //in = new Scanner(System.in);
                //String s = in.next();
                //what kind of vehicle would you like to park?...
                allGarages.get(currentGarage).addVehicle(new Airplane("ABB 022", "RED", 2, 8));
                allGarages.get(currentGarage).addVehicle(new Boat("FFD 123", "BLUE", 0, 78.78));
            }
            else if (choice == 4) { //unpark vehicle
                System.out.println("Unpark vehicle");
            }
            else if (choice == 5) {
                System.out.println("Set max capacity");
            }
            else if (choice == 6) {
                System.out.println("Find vehicle");
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
