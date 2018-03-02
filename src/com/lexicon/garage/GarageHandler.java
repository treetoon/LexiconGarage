package com.lexicon.garage;

import com.lexicon.garage.exceptions.GaragesListOutOfBoundsException;
import com.lexicon.garage.exceptions.VehicleNotFoundException;
import com.lexicon.garage.exceptions.VehiclesListOutOfBoundsException;
import com.lexicon.garage.vehicles.Airplane;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GarageHandler implements Serializable {
    private List<Garage> garagesList = new ArrayList<>();

    public GarageHandler()
    {
    }

    public GarageHandler(int totSpots, String name) {
        try{
            addGarage(totSpots, name); //total parking spots to add
        }catch (VehiclesListOutOfBoundsException e){
            System.out.println("Invalid Vehicle size...");
        }
    }

    public Garage get(int index) {
        return garagesList.get(index);
    }

    public int getGarageListSize() {
        return garagesList.size();
    }

    //Functions

    /**
     * Adds a Garage with a maximum capacity (total parking spots)
     * @param totSpots number of total parking spots
     * @throws VehiclesListOutOfBoundsException
     */
    public void addGarage(int totSpots, String name) throws VehiclesListOutOfBoundsException{
        if(totSpots > 0)
            garagesList.add(new Garage(totSpots, name));
        else
            throw new VehiclesListOutOfBoundsException();
    }

    /**
     * Removes a specific Garage, indicated by the index
     * @param index index of the Garage to be removed
     * @throws VehiclesListOutOfBoundsException
     */
    public void removeGarage(int index) throws VehiclesListOutOfBoundsException {
        if(index >= 0)
            garagesList.remove(index);
        else
            throw new VehiclesListOutOfBoundsException();
    }

    /**
     * Prints all Garages
     * @throws GaragesListOutOfBoundsException
     */
    public void printAllGarages() throws GaragesListOutOfBoundsException {
        if(garagesList.isEmpty())
            throw new GaragesListOutOfBoundsException();

        int i = 0;

        for (Iterator<Garage> it = garagesList.iterator(); it.hasNext(); i++) {
            System.out.print("ID: " + i + " : ");
            System.out.print(it.next());
            System.out.println();
        }
    }

    /**
     * Checks if the entered Garage id is within bounds of the ArrayList
     *
     * @param id : used to select a garage in the ArrayList
     * @return bool : returns true if within bounds
     * @throws GaragesListOutOfBoundsException
     */
    public boolean isIdValid(int id) throws GaragesListOutOfBoundsException {
        if(garagesList.isEmpty())
            throw new GaragesListOutOfBoundsException();

        if(id <= garagesList.size() && id >= 0)
            return true;

        throw new GaragesListOutOfBoundsException();
    }

//    public static void readFile(){
//        try(BufferedReader buff = new BufferedReader(new FileReader("garages.txt")))
//        {
//            StringBuilder builder = new StringBuilder();
//            String oneRow = "";
//
//            System.out.println("Garage file read successfully...");
//
//            while(oneRow != null) {
//                oneRow = buff.readLine();
//                builder.append(oneRow);
//            }
//
//            System.out.println(builder);
//        }catch (IOException e){
//            System.out.println("Could not locate the garage file... " +
//                    "Please name it \"garages.txt\" and place it in the corresponding directory. ");
//        }
//    }

    public static void readFile(){

    }

    public void writeFile(GarageHandler gh) {
        try {
            FileOutputStream f = new FileOutputStream(new File("garages.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(gh);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
