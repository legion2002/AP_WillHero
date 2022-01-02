package com.example.ap_willhero;

import java.io.Serializable;

public abstract class Solid implements Serializable {
    private Position pos;
    private double height, width;
    private double xVelocity, yVelocity;
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
    public void setPos(Position p) {
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
    public int hasCollided(Solid s) {
        int offset = 12;
        int heroLeft = (int) getPos().getxPos();
        int heroRight = (int) (heroLeft + getWidth());
        int heroTop = (int) getPos().getyPos();
        int heroBottom = (int) (heroTop + getHeight());
        int solidLeft = (int) (s.getPos().getxPos());
        int solidRight = (int) (solidLeft + s.getWidth());
        int solidTop = (int) (s.getPos().getyPos());
        int solidBottom = (int) (s.getPos().getyPos() + s.getHeight());


        if (s instanceof Platform) {

            if (((heroRight < solidRight && heroRight > solidLeft) || (heroLeft > solidLeft && heroLeft < solidRight))) {


                if ((heroBottom > (solidTop - offset) && heroBottom < solidTop + offset)) {
//                    System.out.println("Collision with top of platform");
                    return 2; //Collision with top of platform
                }
            }

        }

        //Everything else if a rectangle/square -> no explicit checking
        offset = 12;
        //Collision with left of solid
        if (heroRight > (solidLeft - offset) && heroRight < (solidLeft + offset) &&
                (((heroTop > solidTop - offset) && (heroTop < solidBottom + offset)) || (((heroBottom > solidTop - offset) && (heroBottom < solidBottom + offset))))) {
            System.out.println("Collision with left of solid");
            return 1;
        }
        //Top of solid
        else if (heroBottom > (solidTop - offset) && heroBottom < (solidTop + offset) &&
                ((heroRight < solidRight && heroRight > solidLeft) || (heroLeft > solidLeft && heroLeft < solidRight))) {
            System.out.println("Collision with top of  solid");
            //System.out.println("Object is " + s.getClass().getName());
            return 2;
        }
        //Collision with bottom of solid
        else if (heroTop < (solidBottom + offset) && heroTop > (solidBottom - offset) &&
                ((heroRight < solidRight && heroRight > solidLeft) || (heroLeft > solidLeft && heroLeft < solidRight))) {
            System.out.println("Collision with bottom of  solid");
            return 4;
        }

        //Collision with right of solid
        else if ((heroLeft < (solidRight + offset) && heroLeft > (solidRight - offset)) &&
                (((heroTop > solidTop - offset) && (heroTop < solidBottom + offset)) || (((heroBottom > solidTop - offset) && (heroBottom < solidBottom + offset))))) {
            System.out.println("Collision with right of solid");
            return 3;
        }


        //No collision
        return 0;
    }


}
