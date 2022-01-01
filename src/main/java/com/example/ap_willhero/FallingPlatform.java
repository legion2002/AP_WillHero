package com.example.ap_willhero;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.ArrayList;

public class FallingPlatform extends Solid implements Serializable {
    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    @Override
    public boolean isStaged() {
        return isStaged;
    }

    private ArrayList<Brick> bricks = new ArrayList<>();

    public Rectangle getRectangleForPlatform() {
        return rectangleForPlatform;
    }

    private Rectangle rectangleForPlatform;
    private boolean isStaged;

    public boolean isBrickPhotosSet() {
        return brickPhotosSet;
    }

    public void setBrickPhotosSet(boolean brickPhotosSet) {
        this.brickPhotosSet = brickPhotosSet;
    }

    private boolean brickPhotosSet;

    public FallingPlatform(Rectangle r){
        this.rectangleForPlatform = r;
        this.isStaged = false;
        this.brickPhotosSet = false;
        setPos(new Position(rectangleForPlatform.getLayoutX(), rectangleForPlatform.getLayoutY()));
        setWidth(rectangleForPlatform.getWidth());
        setHeight(rectangleForPlatform.getHeight());
        generateBricks();

    }

    public void generateBricks(){
        int numberOfBricks = (int) (getWidth() / Brick.brickWidth);
        System.out.println("Numbe rof bricks" + numberOfBricks);
        for(int i = 0; i < numberOfBricks; i++){
            bricks.add(new Brick(this));
        }
    }


    @Override
    public void setPos(Position p){
        super.setPos(p);
        rectangleForPlatform.setLayoutX(getPos().getxPos());
        rectangleForPlatform.setLayoutY(getPos().getyPos());
        if(brickPhotosSet){
            int brickNumber = 0;
            for(Brick brick : bricks){
                brick.getBrickPhoto().setLayoutX(getPos().getxPos() + brickNumber * Brick.brickWidth);
                brick.getBrickPhoto().setLayoutY(getPos().getyPos());
            }
        }

    }


    @Override
    public void translateSolidX(double translation) {
        setPos(new Position(getPos().getxPos() + translation, getPos().getyPos()));
    }

    @Override
    public void translateSolidY(double translation) {
        setPos(new Position(getPos().getxPos(), getPos().getyPos() + translation));

    }
}
