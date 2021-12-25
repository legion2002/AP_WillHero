package com.example.ap_willhero;

import java.io.Serializable;

public class RedOrc extends Orc implements Serializable {
    transient final static int redOrcHeight = 60;
    transient final static int redOrcWidth = 60;

    RedOrc(double x, double y) {
        super(x, y);
        this.setHealth(2);
        this.setCoinsReleased(2);
        this.setHeight(redOrcHeight);
        this.setWidth(redOrcWidth);
    }
}
