package com.example.ap_willhero;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Orc extends Solid implements Collidable, Serializable{

    transient private ImageView orcImage;
    private int coinsReleased;
    private int health;
    private boolean isAlive;
    private boolean isStaged;
    private boolean coinsGiven;


    public boolean isCoinsGiven() {
        return coinsGiven;
    }

    public void setCoinsGiven(boolean coinsGiven) {
        this.coinsGiven = coinsGiven;
    }

    public ImageView getOrcImage() {
        return orcImage;
    }

    public void setOrcImage(ImageView orcImage) {
        this.orcImage = orcImage;
        orcImage.setLayoutX(this.getPos().getxPos());
        orcImage.setLayoutY(this.getPos().getyPos());
        orcImage.setFitHeight(this.getHeight());
        orcImage.setFitWidth(this.getWidth());


    }

    public int getCoinsReleased() {
        return coinsReleased;
    }

    public void setCoinsReleased(int coinsReleased) {
        this.coinsReleased = coinsReleased;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    Orc(double x, double y){
        this.setPos( new Position(x, y));
        isStaged = false;
        coinsGiven = false;
        isAlive = true;

    }

    @Override
    public void setPos(Position p){
        super.setPos(p);
        if(orcImage != null){
            orcImage.setLayoutY(p.getyPos());
            orcImage.setLayoutX(p.getxPos());

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

    @Override
    public void collidesWith(Solid s, int collideVal) {

        if((s instanceof Platform || s instanceof FallingPlatform) && collideVal == 2){
            this.setyVelocity(-0.3);
            this.setxVelocity(this.getxVelocity()/2);
            if(this.getxVelocity() < 0.01){
                this.setxVelocity(0);
            }
        }
        if(s instanceof Orc){
            if(collideVal == 1){
                s.setxVelocity(0.1);

            }
        }

        if(s instanceof Shurikens){
            ((Shurikens) s).isLive =false;

            health -= 1;

            if(health <= 0){

                    killOrc();
            }


        }
        if(s instanceof Knives){


            health -= 1;

            if(health <= 0){

                    killOrc();
            }

        }

    }

    public void killOrc() {
        if(this instanceof BossOrc){

        }
        if(orcImage != null){
            orcImage.setImage(null);

        }
        isAlive = false;

    }

}
