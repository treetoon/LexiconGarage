package com.lexicon.garage;

import com.lexicon.garage.exceptions.GaragesListOutOfBoundsException;
import com.lexicon.garage.exceptions.VehiclesListOutOfBoundsException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GarageHandler {
    private List<Garage> garagesList = new ArrayList<>();

    public GarageHandler()
    {
    }

    public GarageHandler(int totSpots) {
        try{
            addGarage(totSpots); //total parking spots to add
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

    /**
     * Adds a Garage with a maximum capacity (total parking spots)
     * @param totSpots number of total parking spots
     * @throws VehiclesListOutOfBoundsException
     */
    public void addGarage(int totSpots) throws VehiclesListOutOfBoundsException{
        if(totSpots > 0)
            garagesList.add(new Garage(totSpots));
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
}
