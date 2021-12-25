package com.example.ap_willhero;

import java.io.Serializable;

public class GreenOrc extends Orc implements Serializable {
    transient final static int greenOrcHeight = 60;
    transient final static int greenOrcWidth = 60;
    GreenOrc(double x, double y) {
        super(x, y);
        this.setHealth(1);
        this.setCoinsReleased(1);
        this.setWidth(greenOrcHeight);
        this.setHeight(greenOrcWidth);
    }
}
