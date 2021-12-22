package com.example.ap_willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Game {
    private Hero hero;
    private int totalCoins;

    public float getAbyssLevel() {
        return abyssLevel;
    }

    private float abyssLevel;
    private int totalLocations;
    private ArrayList<Platform> PlatformList;
    private ArrayList<Solid> SolidList;
    private GameController gameController;


    public Game(GameController gc, ImageView heroImage){

        PlatformList = new ArrayList<>();
        SolidList = new ArrayList<>();
        this.gameController = gc;
        this.abyssLevel = 500;
        this.totalLocations = 122;
        System.out.println("Image Position is: " + heroImage.getLayoutX() + " " + heroImage.getLayoutY());

        hero = new Hero(this, heroImage);

    }

    public void addPlatform(Platform platform){
        PlatformList.add(platform);
    }

    public ArrayList<Platform> getPlatformList(){
        return this.PlatformList;
    }

    public GameController getController(){
        return this.gameController;
    }

    public Hero getHero(){
        return this.hero;
    }
}
