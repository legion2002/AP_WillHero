package com.example.ap_willhero;

import java.io.Serializable;

public abstract class Weapon implements Serializable {
    private int level;
    private int damage;

    public Weapon(){
        level = 0;
        damage = 1;

    }

    public abstract void useWeapon(Game game);

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
