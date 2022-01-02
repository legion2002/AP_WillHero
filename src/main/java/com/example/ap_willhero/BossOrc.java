package com.example.ap_willhero;

import java.io.Serializable;

public class BossOrc extends Orc implements Serializable {
    transient final static int bossOrcHeight = 200;
    transient final static int bossOrcWidth = 200;
    BossOrc(double x, double y) {
        super(x, y);
        this.setHealth(50);
        this.setCoinsReleased(50);
        this.setWidth(bossOrcWidth);
        this.setHeight(bossOrcHeight);

    }
}
