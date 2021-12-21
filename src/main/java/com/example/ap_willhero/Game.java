package com.example.ap_willhero;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Game {
    private Hero hero;
    private int totalCoins;
    private float abyssLevel;
    private int totalLocations;
    private ArrayList<Rectangle> PlatformList;
    AnchorPane root;
    Pane platformsPane;
    private ArrayList<Solid> SolidList = new ArrayList<>();
    private GameController gameController;

    @FXML
    Pane platformPane = new Pane();

    public Game(GameController gameController){
        hero = new Hero(this);
        PlatformList = new ArrayList<>();
        SolidList = new ArrayList<>();
        this.gameController = gameController;
    }

    public void addPlatform(Rectangle platform){
        PlatformList.add(platform);
        //System.out.println(platform.getLayoutX());
    }

    public ArrayList<Rectangle> getPlatformList(){
        return this.PlatformList;
    }

    public GameController getController(){
        return this.gameController;
    }

    public Hero getHero(){
        return this.hero;
    }
}
