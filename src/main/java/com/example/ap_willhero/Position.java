package com.example.ap_willhero;

import java.io.Serializable;

public class Position implements Serializable {
    private double xPos;
    private double yPos;

    Position(double x, double y){
        this.xPos = x;
        this.yPos = y;

    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }




}
