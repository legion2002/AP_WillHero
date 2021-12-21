package com.example.ap_willhero;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.controlsfx.tools.Platform;

public class Hero implements Collidable{
    private Position pos;
    private Helmet helmet;
    private Game game;
    private Weapon equippedWeapon;
    private float jumpHeight;
    private int currCoins;
    private int stepSize;
    private int health;
    private ImageView image;
    private int height;
    private int width;
    private boolean touchingPlatform;


    Hero(Game game){
        currCoins = 0;
        this.game = game;
        this.width = 50;
        this.height = 50;
        this.touchingPlatform = true;
    }

    public void setImage(ImageView image){
        this.image = image;
        this.pos = new Position((float)image.getLayoutX(), (float)image.getLayoutY());
    }

    public void moveForward(){
        //Had return type int
    }

    public void jump(){
        //Had return type int
    }

    public void useWeapon(){

    }

    public void collectCoins(){

    }

    public void upgradeWeapon(){

    }

    public void switchCurrentWeapon(){

    }

    public Helmet getHelmet(){
        return this.helmet;
    }

    public Weapon getEquippedWeapon(){
        return this.equippedWeapon;
    }

    public void setEquippedWeapon(Weapon weapon){
        this.equippedWeapon = weapon;
    }

    public int getCurrCoins(){
        return this.currCoins;
    }

    public void setCurrCoins(int coins){
        this.currCoins = coins;
    }

    public boolean getTouchingPlatform(){
        return touchingPlatform;
    }

    public Position getPosition(){
        return this.pos;
    }

    public ImageView getImage(){
        return this.image;
    }

    public void checkCollisionWithPlatform(){
        boolean flag = false;
        int offset = 2;
        for(Rectangle platform : game.getPlatformList()){
            //System.out.println("Hero Y : " + pos.getyPos());
            //System.out.println("Hero X : " + pos.getxPos());
            //System.out.println("Platform Y : " + platform.getLayoutY());
            //System.out.println("Platform X left boundary: " + platform.getLayoutX());
            //System.out.println("Platform X right boundary: " + (platform.getLayoutX() + platform.getWidth()));
            //if(pos.getyPos() + height >= (platform.getLayoutY() - offset) || pos.getyPos() + height <= (platform.getLayoutY() + offset) && pos.getxPos() >= (platform.getLayoutX() - 25) && (pos.getxPos() + width) <= (platform.getLayoutX() + 25 + platform.getWidth())){
            float left = pos.getxPos();
            float right = left + width;
            float top = pos.getyPos();
            float bottom = top + height;
            float platformLeft = (float)platform.getLayoutX();
            float platformRight = (float)(platformLeft + platform.getWidth());
            float platformTop = (float)platform.getLayoutY();
            //System.out.println("Bottom of hero : " + bottom);
            //System.out.println("Platform top : " + platformTop);
            if(((right < platformRight && right > platformLeft) || (left > platformLeft && left < platformRight)) && (bottom > (platformTop - offset) && bottom < platformTop + offset)){
                flag = true;
                System.out.println("Touched platform");
                game.getController().bounceBackHero();
                break;
            }
        }


        touchingPlatform = flag;
    }


    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public void collidesWith(Solid s) {


    }
}
