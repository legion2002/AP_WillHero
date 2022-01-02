package com.example.ap_willhero;

import java.io.Serializable;

public class Helmet implements Serializable {
    private static Helmet helmet = null;
    public static Helmet getInstance(){
        if(helmet == null){
            helmet = new Helmet();
        }
        return helmet;
    }
    private Helmet(){
        weapon1 = Weapon1.getInstance();
        weapon2 = Weapon2.getInstance();

    }
    private Weapon1 weapon1;
    private Weapon2 weapon2;

    public Weapon1 getWeapon1(){
        return weapon1;
    }

    public Weapon2 getWeapon2(){
        return weapon2;
    }

    public void setWeapon2(){

    }

    public void setWeapon1(){

    }
}
