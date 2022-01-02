package com.example.ap_willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;

public class Knives extends Solid implements Serializable {
    private int lifeTime;
    transient private ImageView knifeImage;
    boolean isLive;
    static int knifeWidth = 30;
    static int knifeHeight = 30;

    public Knives(Position p){
        this.setPos(p);
        knifeImage = new ImageView(new Image("knifeBullet.png"));
        this.isLive = true;
        knifeImage.setFitWidth(knifeWidth);
        knifeImage.setFitHeight(knifeHeight);

    }

    public void shootKnife(){
        Timeline shootingW1 = new Timeline();
        shootingW1.setCycleCount(20);
        KeyFrame shot = new KeyFrame(Duration.millis(40), e -> {
            this.translateSolidX(40);
        });
        shootingW1.getKeyFrames().add(shot);
        shootingW1.setOnFinished(event -> removeKnife());
        shootingW1.play();

    }
    public void removeKnife(){
        this.isLive = false;
        this.knifeImage.setImage(null);

    }
    @Override
    public void setPos(Position p){
        super.setPos(p);
        if(knifeImage != null){
            knifeImage.setLayoutY(p.getyPos());
            knifeImage.setLayoutX(p.getxPos());

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

    public int getLifeTime(){
        return this.lifeTime;
    }

    public void setLifeTime(int lifeTime){
        this.lifeTime = lifeTime;
    }

    public ImageView getKnifeImage() {
        return knifeImage;
    }

    public void setKnifeImage(ImageView img) {
        knifeImage = img;
        knifeImage.setFitWidth(knifeWidth);
        knifeImage.setFitHeight(knifeHeight);
        knifeImage.setLayoutX(getPos().getxPos());
        knifeImage.setLayoutY(getPos().getyPos());
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
