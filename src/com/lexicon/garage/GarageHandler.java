package com.lexicon.garage;

import com.lexicon.garage.exceptions.GaragesListOutOfBoundsException;
import com.lexicon.garage.exceptions.VehiclesListOutOfBoundsException;

import java.util.ArrayList;
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

    public void addGarage(int totSpots) throws VehiclesListOutOfBoundsException{
        if(totSpots > 0)
            garagesList.add(new Garage(totSpots));
        else
            throw new VehiclesListOutOfBoundsException();
    }

    public void removeGarage(int index) {
        garagesList.remove(index);
    }

    public Garage get(int index) {
        return garagesList.get(index);
    }

    public int getGarageListSize() {
        return garagesList.size();
    }

    public void printAllGarages() throws GaragesListOutOfBoundsException {
        if(garagesList.isEmpty())
            throw new GaragesListOutOfBoundsException();

        for (Garage garage : garagesList)
            System.out.println(garage);
    }
}
