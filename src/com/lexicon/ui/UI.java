package com.lexicon.ui;

import com.lexicon.garage.Garage;
import com.lexicon.garage.GarageHandler;
import java.util.Scanner;

public class UI {
    private GarageHandler gh = new GarageHandler();

    public UI(){


        welcome();
        createGarage();

        while (true) {
            listMainFunctions();

            Scanner in = new Scanner(System.in);
            int choice=in.nextInt();

            if (choice == 1) {
                //list all parked vehicles
                System.out.println("Parked vehicles");
                //gh.listParkedVehicles();

            }
            else if (choice==2) {
                //list specific parked vehicles
                System.out.println("Specific Parked vehicles");
            }
            else if (choice==3) {
                System.out.println("Park vehicle");
            }
            else if (choice==4) {
                //unpark vehicle
                System.out.println("Unpark vehicle");
            }
            else if (choice==5) {
                System.out.println("Set max capacity");
            }
            else if (choice==6) {
                System.out.println("Find vehicle");
            }
        }
    }

    public static void main(String[] args){
        UI ui = new UI();
    }

    public void setGh(GarageHandler gh){
        this.gh = gh;
    }

    public static void welcome() {
        System.out.println("Welcome to Garage 1.0");


    }

    public void createGarage() {
        System.out.println("Create garage: Input size");
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        gh.addGarage(new Garage(size));

        //add garage
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
