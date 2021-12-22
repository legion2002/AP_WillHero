package com.example.ap_willhero;

public abstract class Solid {
    private Position pos;
    private double height;
    private double width;
    private int weight;
    private float xVelocity;
    private float yVelocity;
    private float gravity;


    public Position getPos() {
        return pos;
    }
    public void setPos(Position p){
        pos = p;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }




}
