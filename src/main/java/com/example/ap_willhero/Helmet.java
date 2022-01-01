package com.example.ap_willhero;

import java.io.Serializable;

public class Helmet implements Serializable {
    public Helmet(){
        weapon1 = new Weapon1();
        weapon2 = new Weapon2();

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
