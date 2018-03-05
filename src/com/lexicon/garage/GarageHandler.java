package com.lexicon.garage;

import com.lexicon.garage.exceptions.GaragesListOutOfBoundsException;
import com.lexicon.garage.exceptions.VehiclesListOutOfBoundsException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GarageHandler {
    private List<Garage> garagesList = new ArrayList<>();

    public GarageHandler()
    {
    }

    public GarageHandler(int totSpots, String name) {
        try{
            addGarage(totSpots, name); //total parking spots to add
        }catch (VehiclesListOutOfBoundsException e){
            System.out.println("Invalid size...");
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
        if(index >= 0 && index < garagesList.size())
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
     * @param id used to select a garage in the ArrayList
     * @return bool returns true if within bounds
     * @throws GaragesListOutOfBoundsException when the array is empty or out of bounds
     */
    public boolean isIdValid(int id) throws GaragesListOutOfBoundsException {
        if(garagesList.isEmpty())
            throw new GaragesListOutOfBoundsException();

        if(id <= garagesList.size() && id >= 0)
            return true;

        throw new GaragesListOutOfBoundsException();
    }

    /**
     * Reads objects from text file
     * @return returns true if reading file was a success, otherwise false
     */
    @SuppressWarnings("unchecked")
    public boolean readFile(){
        try{
            File file = new File("garages.txt");

            if(file.exists()){
                ObjectInputStream oi = new ObjectInputStream(new FileInputStream(file));
                garagesList = (ArrayList<Garage>) oi.readObject(); //cast should be safe, dw compiler

                oi.close();
                return true;
            }
        }catch(FileNotFoundException e){
            System.out.println("File could not be found...");
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("Could not find the class... ");
            e.printStackTrace();
        }catch(EOFException e){
            System.out.println("End of file, likely occurred due to the file being empty...");
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        return false; //file could not be read
    }

    /**
     * Writes objects to file
     */
    public void writeFile() {
        try{
            FileOutputStream f = new FileOutputStream(new File("garages.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(garagesList);
            System.out.println("Write successful...");
        }catch(FileNotFoundException e){
            System.out.println("File could not be found...");
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
