package com.example.ap_willhero;

import java.io.File;
import java.util.ArrayList;

public class LoadGames {

    private ArrayList<String> listOfLoadableGames;
    public LoadGames() {
        listOfLoadableGames = new ArrayList<>();
    }

    public ArrayList<String> getListOfLoadableGames() {
        File [] directory = new File("AP_WillHero\\src\\SavedGames").listFiles();
        for(File file : directory) {
            listOfLoadableGames.add(file.getName());
        }
        return listOfLoadableGames;
    }

}
