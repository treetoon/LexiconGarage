package com.lexicon.ui;

import com.lexicon.garage.Garage;
import com.lexicon.garage.GarageHandler;

public class UI {
    private GarageHandler gh = null;

    public UI(){
        //gh = new Garage(SIZE);
    }

    public static void main(String[] args){
        UI ui = new UI();
    }

    public void setGh(GarageHandler gh){
        this.gh = gh;
    }
}
