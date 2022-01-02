package com.example.ap_willhero;

import java.io.Serializable;
import java.util.Random;

public class WeaponChest extends TreasureChest implements Serializable {
    int weaponType;
    WeaponChest(double x, double y) {
        super(x, y);
        Random rand = new Random();
//        weaponType = 2;
        weaponType = rand.nextInt(2) + 1;
    }

    public int getWeaponType() {
        return weaponType;
    }
}
