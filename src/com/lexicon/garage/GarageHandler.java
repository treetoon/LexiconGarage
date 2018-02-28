package com.lexicon.garage;

import java.util.ArrayList;
import java.util.List;

public class GarageHandler {

    private List<Garage> garages = new ArrayList<>();

    public GarageHandler(){
    }

    //functions
    public void addGarage(Garage garage){
        garages.add(garage);
    }

    public void removeGarage(){

    }
}
