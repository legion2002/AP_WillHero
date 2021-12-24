package com.example.ap_willhero;

public class GreenOrc extends Orc{
    final static int greenOrcHeight = 60;
    final static int greenOrcWidth = 60;
    GreenOrc(double x, double y) {
        super(x, y);
        this.setHealth(1);
        this.setCoinsReleased(1);
        this.setWidth(greenOrcHeight);
        this.setHeight(greenOrcWidth);
    }
}