package com.lexicon.ui;

import com.lexicon.garage.Garage;
import com.lexicon.garage.GarageHandler;
import java.util.Scanner;
import com.lexicon.garage.Vehicle;

public class UI {
    private GarageHandler gh = new GarageHandler();

    public UI(){
        superMetod();
    }

    public static void main(String[] args){
        UI start = new UI();
    }

    public void createGarage() {
        System.out.println("Write total parking spots: ");
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();

        //gh.addGarage();
    }

    public void superMetod(){
        System.out.println("Welcome to Garage 1.0");
        createGarage();

        while (true) {
            listMainFunctions();

            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();

            if (choice == 1) { //list all parked vehicles
                System.out.println("Listing all parked vehicles: ");

            }
            else if (choice == 2) {
                //list specific parked vehicles
                System.out.println("Specific Parked vehicles");
            }
            else if (choice==3) {
                System.out.println("Input vehicle type");
                in = new Scanner(System.in);
                String s=in.next();


            }
            else if (choice == 4) {
                //unpark vehicle
                System.out.println("Unpark vehicle");
            }
            else if (choice == 5) {
                System.out.println("Set max capacity");
            }
            else if (choice == 6) {
                System.out.println("Find vehicle");
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

    }


}
