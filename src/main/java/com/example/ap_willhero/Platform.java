package com.example.ap_willhero;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Platform extends Solid {
    ImageView platformImage;
    Rectangle basePlatform;

    Platform(Rectangle r){

        basePlatform = r;
        setPos(new Position(basePlatform.getLayoutX(),basePlatform.getLayoutY()));

        setWidth(basePlatform.getWidth());
        setHeight(basePlatform.getHeight());
    }

    @Override
    public void setPos(Position p){
        super.setPos(p);
        basePlatform.setLayoutX(getPos().getxPos());
        basePlatform.setLayoutY(getPos().getyPos());
        //CHANGE THIS WHEN YOU ADD IMAGEVIEW
//        platformImage.setLayoutX(getPos().getxPos());
//        platformImage.setLayoutY(getPos().getyPos());



    }

    public void translatePlatformX(double translation){
        setPos(new Position(getPos().getxPos() + translation, getPos().getyPos()));
    }
    public void translatePlatformY(double translation){
        setPos(new Position(getPos().getxPos(), getPos().getyPos() + translation));

    }
}
