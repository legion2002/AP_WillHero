package com.example.ap_willhero;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Platform extends Solid implements Serializable {
    transient ImageView platformImage;

    public void setBasePlatform(Rectangle basePlatform) {
        this.basePlatform = basePlatform;
    }

    transient Rectangle basePlatform;
    private boolean isStaged;

    public Rectangle getBasePlatform() {
        return basePlatform;
    }

    @Override
    public boolean isStaged() {
        return isStaged;
    }

    @Override
    public void setStaged(boolean staged) {
        isStaged = staged;
    }

    Platform(Rectangle r) {

        basePlatform = r;
        this.isStaged = false;
        setPos(new Position(basePlatform.getLayoutX(), basePlatform.getLayoutY()));

        setWidth(basePlatform.getWidth());
        setHeight(basePlatform.getHeight());
    }

    public void setPlatformImage(ImageView platImage) {
        this.platformImage = platImage;
        platformImage.setLayoutX(this.getPos().getxPos());
        platformImage.setLayoutY(this.getPos().getyPos());
        platformImage.setFitHeight(100);
        platformImage.setFitWidth(this.getWidth());


    }

    @Override
    public void setPos(Position p) {
        super.setPos(p);
        if(basePlatform != null){
            basePlatform.setLayoutX(getPos().getxPos());
            basePlatform.setLayoutY(getPos().getyPos());
        }
        if (platformImage != null) {
            platformImage.setLayoutX(getPos().getxPos());
            platformImage.setLayoutY(getPos().getyPos());
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
