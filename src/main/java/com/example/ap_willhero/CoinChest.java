package com.example.ap_willhero;

import java.io.Serializable;
import java.util.Random;

public class CoinChest extends TreasureChest implements Serializable {
    int treasureCoins;

    public int getTreasureCoins() {
        return treasureCoins;
    }

    CoinChest(double x, double y) {
        super(x, y);
        Random rand = new Random();
        treasureCoins = rand.nextInt(5) + 5;


    }
}
