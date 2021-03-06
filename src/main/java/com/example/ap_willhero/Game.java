package com.example.ap_willhero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Serializable {
    private Hero hero;
    private int totalCoins;
    private boolean hasBeenResurrected;


    private double gravity;
    transient Random rand = new Random();

    private float abyssLevel;
    private int totalLocations;

    private BossOrc boss;
    private ArrayList<Platform> PlatformList;
    private ArrayList<Solid> SolidList;
    private ArrayList<Orc> OrcList;
    private ArrayList<Solid> WeaponList;
    private ArrayList<Coin> CoinList;



    public ArrayList<FallingPlatform> getFallingPlatformList() {
        return FallingPlatformList;
    }

    private ArrayList<FallingPlatform> FallingPlatformList;
    private ArrayList<TreasureChest> TreasureChestList;

    public boolean isHasBeenResurrected() {
        return hasBeenResurrected;
    }

    public void setHasBeenResurrected(boolean hasBeenResurrected) {
        this.hasBeenResurrected = hasBeenResurrected;
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public void setTotalCoins(int totalCoins) {
        this.totalCoins = totalCoins;
    }

    public BossOrc getBoss() {
        return boss;
    }

    public ArrayList<TreasureChest> getTreasureChestList() {
        return TreasureChestList;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    transient private GameController gameController;


    public ArrayList<Orc> getOrcList() {
        return OrcList;
    }

    public float getAbyssLevel() {
        return abyssLevel;
    }
    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
    public void killHero(){
        System.out.println("Hero is Dead");
        gameController.getTimeline().pause();
        totalCoins += hero.getCurrCoins();
        if(hasBeenResurrected){
            System.out.println("Hero has already resurrected");
            gameController.getEndGameMenu().getChildren().remove(gameController.getResurrectButton());
        }

        gameController.getRoot().getChildren().add(gameController.getEndGameMenu());
        gameController.getResurrectCoins().setText(Integer.toString(hero.getCurrCoins()));
        gameController.getResurrectLocation().setText(Integer.toString(hero.getCurrentLocation()));




    }

    public Game(GameController gc, ImageView heroImage) {

        PlatformList = new ArrayList<>();
        SolidList = new ArrayList<>();
        OrcList  = new ArrayList<>();
        WeaponList = new ArrayList<>();
        TreasureChestList = new ArrayList<>();
        FallingPlatformList = new ArrayList<>();
        CoinList = new ArrayList<>();
        this.gameController = gc;
        this.abyssLevel = 500;
        this.totalLocations = 122;
        this.gravity = 0.0008;
        this.totalCoins = 0;
        this.hasBeenResurrected =false;
//        System.out.println("Image Position is: " + heroImage.getLayoutX() + " " + heroImage.getLayoutY());

        hero = new Hero(this, heroImage);

    }

    public void addPlatform(Platform platform) {
        PlatformList.add(platform);
        SolidList.add(platform);
    }

    public void addFallingPlatform(FallingPlatform fallingPlatform){
        FallingPlatformList.add(fallingPlatform);
        SolidList.add(fallingPlatform);
    }

    public ArrayList<Platform> getPlatformList() {
        return this.PlatformList;
    }

    public GameController getController() {
        return this.gameController;
    }

    public Hero getHero() {
        return this.hero;
    }

    public ArrayList<Solid> getSolidList() {
        return this.SolidList;
    }

    public ArrayList<Coin> getCoinList() {
        return CoinList;
    }

    public void heroWon(){
        System.out.println("==================HERO WON====================");
        totalCoins += hero.getCurrCoins();
        System.exit(0);

    }

    public void startBossFight(){
        System.out.println("===================BOSS FIGHT=====================");
        BossOrc b = new BossOrc(hero.getPos().getxPos() + 100 * 9 , 0);
        ImageView bossImage = new ImageView(new Image("greenOrc.png"));
        b.setOrcImage(bossImage);
        this.SolidList.add(b);
        this.OrcList.add(b);
        this.gameController.getGameObjectsPane().getChildren().add(bossImage);
        this.boss = b;
    }

    public void generateOrc(int half, Platform platform){
            double startingPoint;
            if(half == 1){
                startingPoint = platform.getPos().getxPos();
            }
            else{
                startingPoint = platform.getPos().getxPos() + platform.getWidth() / 2;
            }
            int maximumOrcWidth = 65;
            int randPos = rand.nextInt(100 - maximumOrcWidth);
            int orcType = rand.nextInt(2) + 1;

            if(orcType == 1){
                RedOrc r = new RedOrc(randPos + startingPoint, platform.getPos().getyPos() - RedOrc.redOrcHeight - 5);
                this.SolidList.add(r);
                this.OrcList.add(r);
                }
            else{
                GreenOrc g = new GreenOrc(randPos + startingPoint, platform.getPos().getyPos() - GreenOrc.greenOrcHeight - 5);
                this.SolidList.add(g);
                this.OrcList.add(g);

            }
    }

    public void generateCoin(int half, Platform platform){
        double startingPoint;
        double coinOffsetY = -50;
        double coinOffsetX = 5;
        if(half == 1){
            startingPoint = platform.getPos().getxPos();
        }
        else{
            startingPoint = platform.getPos().getxPos() + platform.getWidth() / 2;
        }
        int maximumCoinWidth = 30;
        double distanceBetweenCoins = 50;

        Coin coin1 = new Coin(this, new Position(startingPoint + coinOffsetX, platform.getPos().getyPos() + coinOffsetY));
        SolidList.add(coin1);
        CoinList.add(coin1);

        if (platform.getWidth() / 2 < 100)
            return;

        Coin coin2 = new Coin(this, new Position(startingPoint + coinOffsetX + distanceBetweenCoins, platform.getPos().getyPos() + coinOffsetY));


        SolidList.add(coin2);
        CoinList.add(coin2);



        if (platform.getWidth() / 2 > 100) {
            // We generate a batch of three coins

            Coin coin3 = new Coin(this, new Position(startingPoint + coinOffsetX + 2 * distanceBetweenCoins, platform.getPos().getyPos() + coinOffsetY));
            SolidList.add(coin3);
            CoinList.add(coin3);



        }

    }

    public void generateChest(int half, Platform platform){
        double startingPoint;
        if(half == 1){
            startingPoint = platform.getPos().getxPos();
        }
        else{
            startingPoint = platform.getPos().getxPos() + platform.getWidth() / 2;
        }

        int offsetY = -3;

        int randPos = rand.nextInt(100 - TreasureChest.chestWidth);
        int chestType = rand.nextInt(2) + 1;

        if(chestType == 1){
            WeaponChest chest= new WeaponChest(randPos + startingPoint, platform.getPos().getyPos() - TreasureChest.chestHeight - offsetY);

            this.SolidList.add(chest);
            this.TreasureChestList.add(chest);
        }
        else{
            CoinChest chest = new CoinChest(randPos + startingPoint, platform.getPos().getyPos() - TreasureChest.chestHeight - offsetY);
            this.SolidList.add(chest);
            this.TreasureChestList.add(chest);


        }

    }

    public void generateGameObjects(){

        //Four cases : Three objects to generate + Blank platform
        //Probability for orcs is 0.4 -> Further both orcs can be generated with a probability of 0.5
        //Probability for coins is 0.2
        //Probability for chest is 0.1
        //Probability for empty platform is 0.2
        //We divide the platform into two halves and then generate combinations of two


        int maxRandomNum = 10;
        boolean firstPlatform = false;
        for (Platform currPlatform : PlatformList){
            if(! firstPlatform){
                firstPlatform = true;
                continue;
            }
            int objectForFirstHalf = rand.nextInt(maxRandomNum);
            int objectForSecondHalf = rand.nextInt(maxRandomNum);

            //For the first half
            if(objectForFirstHalf <= 3){
                generateOrc(1, currPlatform);
            }
            else if(objectForFirstHalf > 3 && objectForFirstHalf < 6){
                generateCoin(1, currPlatform);
            }

            else if(objectForFirstHalf >= 6 && objectForFirstHalf <  7){
                generateChest(1, currPlatform);
            }


            //For second half
            if(objectForSecondHalf <= 3){
                generateOrc(2, currPlatform);
            }
            else if(objectForSecondHalf > 3 && objectForSecondHalf < 6){
                generateCoin(2, currPlatform);
            }

            else if(objectForSecondHalf >= 6 && objectForSecondHalf < 7){
                generateChest(2, currPlatform);

            }

        }

    }

    public ArrayList<Solid> getWeaponList() {
        return WeaponList;
    }
}
