package com.example.ap_willhero;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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

    public boolean isAnimationDone() {
        return animationDone;
    }

    public void setAnimationDone(boolean animationDone) {
        this.animationDone = animationDone;
    }

    private boolean animationDone;

    public Rectangle getRectangleForPlatform() {
        return rectangleForPlatform;
    }

    transient private Rectangle rectangleForPlatform;
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
        this.animationDone = false;
        setPos(new Position(rectangleForPlatform.getLayoutX(), rectangleForPlatform.getLayoutY()));
        setWidth(rectangleForPlatform.getWidth());
        setHeight(rectangleForPlatform.getHeight());
        generateBricks();

    }

    public void generateBricks(){
        int numberOfBricks = (int) (getWidth() / Brick.brickWidth);
        for(int i = 0; i < numberOfBricks; i++){
            bricks.add(new Brick(this));
        }
    }


    @Override
    public void setPos(Position p){
        super.setPos(p);
        rectangleForPlatform.setLayoutX(getPos().getxPos());
        rectangleForPlatform.setLayoutY(getPos().getyPos());
        if(brickPhotosSet && !animationDone){
            int brickNumber = 0;
            for(Brick brick : bricks){
                brick.getBrickPhoto().setLayoutX(getPos().getxPos() + brickNumber * Brick.brickWidth);
                brick.getBrickPhoto().setLayoutY(getPos().getyPos());
                brickNumber++;
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

    public void removeFallingPlatformBrick(ImageView brick) {
        brick.setImage(null);
    }

    public void brickFalling(ImageView node) {
        TranslateTransition fallBrick = new TranslateTransition();

        fallBrick.setByY(node.getLayoutY() + 500);
        fallBrick.setDuration(Duration.millis(2500));
        fallBrick.setNode(node);
        fallBrick.setOnFinished(e -> removeFallingPlatformBrick(node));

        fallBrick.play();
        rectangleForPlatform.setWidth(rectangleForPlatform.getWidth() - 100);
        this.setPos(new Position(this.getPos().getxPos() + 100, this.getPos().getyPos()));

    }

    public void startFallingPlatformAnimation() {
        this.setAnimationDone(true);
        int brickNumber = 1;
        int timeGapForFalling = 400;
        for(Brick brick : bricks){
            brick.getPause().setDuration(Duration.millis(brickNumber * timeGapForFalling));
            brickNumber++;
        }


        for(Brick brick : bricks){
            brick.getPause().play();

            brick.getPause().setOnFinished(e -> brickFalling(brick.getBrickPhoto()));
        }
    }
}
