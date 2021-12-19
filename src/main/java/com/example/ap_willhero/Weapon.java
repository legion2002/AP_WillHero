package com.example.ap_willhero;

public abstract class Weapon {
    private int level;
    private int damage;

    public Weapon(){

    }

    public abstract void useWeapon();

    public void increaseLevel(){
        this.level++;
    }

    public int getLevel(){
        return this.level;
    }

    public void setLevel(int newLevel){
        this.level = newLevel;
    }

    public int getDamage(){
        return this.damage;
    }
}
