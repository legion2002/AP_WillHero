package com.example.ap_willhero;

import javafx.scene.image.ImageView;

public class Orc extends Solid{

    private ImageView orcImage;
    private int coinsReleased;
    private int health;
    private boolean isStaged;



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



    Orc(double x, double y){
        this.setPos( new Position(x, y));
        isStaged = false;

    }

    @Override
    public void setPos(Position p){
        super.setPos(p);
        if(orcImage != null){
            orcImage.setLayoutY(p.getyPos());
            orcImage.setLayoutX(p.getxPos());

        }


    }

    public void translateOrcX(double i) {
        setPos(new Position(getPos().getxPos() + i, getPos().getyPos()));
    }
    public void translateOrcY(double i) {
        setPos(new Position(getPos().getxPos(), getPos().getyPos() + i));
    }
}
