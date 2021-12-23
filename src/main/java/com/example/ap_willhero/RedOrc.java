package com.example.ap_willhero;

public class RedOrc extends Orc{
    static int redOrcHeight = 65;
    static int redOrcWidth = 65;

    RedOrc(double x, double y) {
        super(x, y);
        this.setHealth(2);
        this.setCoinsReleased(2);
        this.setHeight(redOrcHeight);
        this.setWidth(redOrcWidth);
    }
}
