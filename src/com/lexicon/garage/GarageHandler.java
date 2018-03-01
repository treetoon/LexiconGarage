package com.lexicon.garage;

import com.lexicon.garage.exceptions.VehicleListOutOfBoundsException;

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
        }catch (VehicleListOutOfBoundsException e){
            System.out.println("Invalid Vehicle size...");
        }
    }

    //functions
    public void addGarage(int totSpots) throws VehicleListOutOfBoundsException {
        if(totSpots > 0)
            garagesList.add(new Garage(totSpots));
        else
            throw new VehicleListOutOfBoundsException();
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
}
