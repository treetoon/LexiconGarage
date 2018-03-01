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

    //Getters & Setters

    public Garage get(int index) {
        return garagesList.get(index);
    }

    public int getGarageListSize() {
        return garagesList.size();
    }


    //Functions

    public void addGarage(int totSpots) throws VehiclesListOutOfBoundsException{
        if(totSpots > 0)
            garagesList.add(new Garage(totSpots));
        else
            throw new VehiclesListOutOfBoundsException();
    }

    public void removeGarage(int index) throws VehiclesListOutOfBoundsException {
        if(index > 0)
            garagesList.remove(index);
        else
            throw new VehiclesListOutOfBoundsException();
    }

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

    /***
     * Check if the entered garage id is within bounds of the ArrayList
     *
     * @param id : used to select a garage in the ArrayList
     * @return bool : returns true if within bounds
     * @throws GaragesListOutOfBoundsException
     */
    public boolean isIdValid(int id) throws GaragesListOutOfBoundsException {
        if(garagesList.isEmpty())
            throw new GaragesListOutOfBoundsException();

        if(id < garagesList.size() && id <= 0)
            return true;

        throw new GaragesListOutOfBoundsException();
    }
}
