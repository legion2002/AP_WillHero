package com.example.ap_willhero;

public class Hero implements Collidable{
    Position pos;
    Helmet helmet;
    Weapon equippedWeapon;
    float jumpHeight;
    int currCoins;
    int stepSize;
    int health;

    Hero(){
        pos = new Position(55,55);
        currCoins = 0;
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


    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public void collidesWith() {

    }
}
