package com.example.ap_willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;

public class Shurikens extends Solid implements Serializable {
    private int lifeTime;
    transient private ImageView shurikenImage;
    boolean isLive;
    static int shurikenHeight = 30;
    static int shurikenWidth = 30;
    static int shurikenOffset = 32;



    public ImageView getShurikenImage() {
        return shurikenImage;
    }

    @Override
    public void setPos(Position p){
        super.setPos(p);
        if(shurikenImage != null){
            shurikenImage.setLayoutY(p.getyPos());
            shurikenImage.setLayoutX(p.getxPos());

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
    public Shurikens(Position pos){
        this.setPos(pos);
        shurikenImage = new ImageView(new Image("shurikenBullet.png"));
        this.isLive = true;
        shurikenImage.setFitWidth(shurikenWidth);
        shurikenImage.setFitHeight(shurikenHeight);


    }

    public void setShurikenImage(ImageView img){
        shurikenImage = img;
        shurikenImage.setFitWidth(shurikenWidth);
        shurikenImage.setFitHeight(shurikenHeight);
        shurikenImage.setLayoutX(getPos().getxPos());
        shurikenImage.setLayoutY(getPos().getyPos());

    }

    public void shootShuriken(){
        Timeline shootingW1 = new Timeline();
        shootingW1.setCycleCount(15);
        KeyFrame shot = new KeyFrame(Duration.millis(40), e -> {
            this.translateSolidX(30);
            shurikenImage.setRotate(shurikenImage.getRotate() + 20);


        });
        shootingW1.getKeyFrames().add(shot);
        shootingW1.setOnFinished(event -> removeShuriken());
        shootingW1.play();

    }
    public void removeShuriken(){
        this.isLive = false;
        this.shurikenImage.setImage(null);

    }

    public int getLifeTime(){
        return this.lifeTime;
    }

    public void setLifeTime(int lifeTime){
        this.lifeTime = lifeTime;
    }



}
