package com.example.ap_willhero;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Brick{
    final static int brickWidth = 100;
    final static int brickHeight = 20;

    public ImageView getBrickPhoto() {
        return brickPhoto;
    }

    private ImageView brickPhoto;
    private FallingPlatform basePlatform;

    public Brick(FallingPlatform platform){
        this.basePlatform = platform;
    }

    public void setBrickImage(ImageView img, int brickNumber){
        this.brickPhoto = img;
        img.setLayoutX(basePlatform.getPos().getxPos() + brickNumber * brickWidth);
        img.setLayoutY(basePlatform.getPos().getyPos());
        img.setFitWidth(brickWidth);
        img.setFitHeight(brickHeight);

    }
}
