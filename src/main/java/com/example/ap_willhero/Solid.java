package com.example.ap_willhero;

public abstract class Solid {
    private Position pos;
    private double height;
    private double width;
    private int weight;
    private double xVelocity;
    private double yVelocity;



    private boolean isStaged;


    public boolean isStaged() {
        return isStaged;
    }

    public void setStaged(boolean staged) {
        isStaged = staged;
    }

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

    public abstract void translateSolidX(double translation);
    public abstract void translateSolidY(double translation);

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }






}
