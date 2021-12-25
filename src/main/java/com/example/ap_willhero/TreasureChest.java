package com.example.ap_willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;

public class TreasureChest extends Solid implements Serializable {
    transient private ImageView chestImage;
    private boolean isStaged;
    transient final static int chestHeight = 55;
    transient final static int chestWidth = 74;
    transient int chestNumber;

    @Override
    public boolean isStaged() {
        return isStaged;
    }

    @Override
    public void setStaged(boolean staged) {
        isStaged = staged;
    }




    TreasureChest(double x, double y){
        this.setPos( new Position(x, y));
        isStaged = false;
        this.setHeight(chestHeight);
        this.setWidth(chestWidth);

    }

    @Override
    public void setPos(Position p){
        super.setPos(p);
        if(chestImage != null){
            chestImage.setLayoutY(p.getyPos());
            chestImage.setLayoutX(p.getxPos());

        }


    }
    @Override
    public void translateSolidX(double i) {
        setPos(new Position(getPos().getxPos() + i, getPos().getyPos()));
    }
    @Override
    public void translateSolidY(double i) {

        setPos(new Position(getPos().getxPos(), getPos().getyPos() + i));
    }

    public void doChestAnimation(){
        /*
        Timeline chestAnimation = new Timeline();
        chestAnimation.setCycleCount(4);
        chestAnimation.setAutoReverse(false);
        String imageName = "chestStage";
        chestNumber = 2;
        KeyFrame changeImage = new KeyFrame(Duration.millis(500), e ->{
            String chestImageName = imageName + String.valueOf(chestNumber) + ".png";
            System.out.println(chestImageName);
            setChestImage(new ImageView(new Image(chestImageName)));
            chestNumber++;
        });
        chestAnimation.getKeyFrames().add(changeImage);
        chestAnimation.play();

         */
        String imageName = "chestStage";
        chestNumber = 2;
        for(int i = 0; i < 5; i++){
            String chestImageName = imageName + String.valueOf(chestNumber) + ".png";
            System.out.println(chestImageName);
            setChestImage(new ImageView(new Image(chestImageName)));
            chestNumber++;
        }

    }

    public ImageView getChestImage() {
        return chestImage;
    }

    public void setChestImage(ImageView chestImage) {
        this.chestImage = chestImage;
        chestImage.setLayoutX(this.getPos().getxPos());
        chestImage.setLayoutY(this.getPos().getyPos());
        chestImage.setFitHeight(this.getHeight());
        chestImage.setFitWidth(this.getWidth());


    }


}
