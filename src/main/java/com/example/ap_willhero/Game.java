package com.example.ap_willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Game {
    private Hero hero;
    private int totalCoins;
    private int gravity;

    public float getAbyssLevel() {
        return abyssLevel;
    }

    private float abyssLevel;
    private int totalLocations;
    private ArrayList<Platform> PlatformList;
    private ArrayList<Solid> SolidList;
    private ArrayList<Orc> OrcList;
    private GameController gameController;

    public ArrayList<Orc> getOrcList() {
        return OrcList;
    }

    public Game(GameController gc, ImageView heroImage) {

        PlatformList = new ArrayList<>();
        SolidList = new ArrayList<>();
        OrcList  = new ArrayList<>();
        this.gameController = gc;
        this.abyssLevel = 500;
        this.totalLocations = 122;
        System.out.println("Image Position is: " + heroImage.getLayoutX() + " " + heroImage.getLayoutY());

        hero = new Hero(this, heroImage);

    }

    public void addPlatform(Platform platform) {
        PlatformList.add(platform);
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

    public void generateCoins() {
        double coinOffsetY = -50;
        double coinOffsetX = 20;
        double distanceBetweenCoins = 50;
        double coinHeight = 30;
        double coinWidth = 30;
        int platformCounter = 0;
        for (Platform platform : PlatformList) {
            //Generating coins on every second platform : starting from 2nd
            if (platformCounter % 2 == 1) {
                double platformWidth = platform.getWidth();
                double platformX = platform.getPos().getxPos();
                double platformY = platform.getPos().getyPos();

                ImageView img1 = new ImageView();
                Image image = new Image("coin.png");
                img1.setImage(image);
                img1.setFitHeight(coinHeight);
                img1.setFitWidth(coinWidth);
                Coin coin1 = new Coin(this, new Position(platformX + coinOffsetX, platformY + coinOffsetY), img1);

                ImageView img2 = new ImageView();
                img2.setImage(image);
                img2.setFitHeight(coinHeight);
                img2.setFitWidth(coinWidth);
                Coin coin2 = new Coin(this, new Position(platformX + coinOffsetX + distanceBetweenCoins, platformY + coinOffsetY), img2);

                SolidList.add(coin1);
                SolidList.add(coin2);
                gameController.getGameRoot().getChildren().add(img1);
                gameController.getGameRoot().getChildren().add(img2);

                if (platformWidth > 200) {
                    // We generate a batch of three coins
                    ImageView img3 = new ImageView();
                    img3.setImage(image);
                    img3.setFitHeight(coinHeight);
                    img3.setFitWidth(coinWidth);

                    Coin coin3 = new Coin(this, new Position(platformX + coinOffsetX + 2 * distanceBetweenCoins, platformY + coinOffsetY), img3);
                    SolidList.add(coin3);
                    gameController.getGameRoot().getChildren().add(img3);

                }

            }

            platformCounter++;
        }
    }

    public void generateOrcs() {
        int maxRandNum = 7;
        int minRandNum = 0;

        for (Platform currPlatform : PlatformList) {
            double maxRandPos = 100;
            double minRandPos = 5;
            int randNum = (int) (Math.random() * (maxRandNum - minRandNum + 1) + minRandNum);
            if(randNum >= 4){
                continue;
            }
            double randPos = Math.random() * (maxRandPos - minRandPos + 1) + maxRandPos;
            randPos = 5;
            for (int i = 0; i < randNum; i++) {

                int orcType = (int) (Math.random() + 0.25);
                if(orcType == 1){
                    RedOrc r = new RedOrc(randPos + currPlatform.getPos().getxPos(), currPlatform.getPos().getyPos() - RedOrc.redOrcHeight - 5);
                    randPos += RedOrc.redOrcWidth + 10;
                    this.SolidList.add(r);
                    this.OrcList.add(r);
                }
                else{
                    GreenOrc g = new GreenOrc(randPos + currPlatform.getPos().getxPos(), currPlatform.getPos().getyPos() - GreenOrc.greenOrcHeight - 5);
                    randPos += GreenOrc.greenOrcWidth + 10;

                    this.SolidList.add(g);
                    this.OrcList.add(g);

                }
            }

        }
        for(Orc orc : OrcList){
            if(orc instanceof RedOrc){
                System.out.println("Red Orc position is : " + orc.getPos().getxPos() + " " + orc.getPos().getyPos());

            }
            else{
                System.out.println("Green Orc position is : " + orc.getPos().getxPos() + " " + orc.getPos().getyPos());

            }
        }
    }
}
