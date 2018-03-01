package com.lexicon.garage;

import com.lexicon.garage.exceptions.VehicleListOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

public class GarageHandler {

    private List<Garage> garages = new ArrayList<>();

    public GarageHandler()
    {
    }

    public GarageHandler(int totSpots) throws VehicleListOutOfBoundsException {
        if(totSpots > 0)
            addGarage(totSpots); //total parking spots to add
        else
            throw new VehicleListOutOfBoundsException();
    }

    //functions
    public void addGarage(int totSpots){
        garages.add(new Garage(totSpots));
    }

    public void removeGarage(int index){
        garages.remove(index);
    }

    public Garage get(int index){
        return garages.get(index);
    }
}
