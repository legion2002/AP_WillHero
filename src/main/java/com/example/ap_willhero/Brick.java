package com.example.ap_willhero;

import javafx.animation.PauseTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Brick implements Serializable {
    final static int brickWidth = 100;
    final static int brickHeight = 20;

    public PauseTransition getPause() {
        return pause;
    }

    public void setPause(PauseTransition pause) {
        this.pause = pause;
    }

    transient private PauseTransition pause;

    public ImageView getBrickPhoto() {
        return brickPhoto;
    }

    transient private ImageView brickPhoto;
    private FallingPlatform basePlatform;

    public Brick(FallingPlatform platform){
        this.basePlatform = platform;
        this.pause = new PauseTransition();
    }

    public void setBrickImage(ImageView img, int brickNumber){
        this.brickPhoto = img;
        img.setLayoutX(basePlatform.getPos().getxPos() + brickNumber * brickWidth);
        img.setLayoutY(basePlatform.getPos().getyPos());
        img.setFitWidth(brickWidth);
        img.setFitHeight(brickHeight);

    }
}
