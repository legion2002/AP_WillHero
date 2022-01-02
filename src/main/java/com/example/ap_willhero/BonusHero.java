package com.example.ap_willhero;

import javafx.scene.image.ImageView;

public class BonusHero extends Solid implements Collidable {
    private int health;
    private ImageView heroImage;
    private BonusController game;


    BonusHero(BonusController g, ImageView img) {
        this.game = g;
        this.heroImage = img;
        setWidth(heroImage.getFitWidth());
        setHeight(heroImage.getFitHeight());
        setPos(new Position(heroImage.getLayoutX(), heroImage.getLayoutY()));
    }

    @Override
    public void collidesWith(Solid s, int collideVal) {
        if(s instanceof Platform){
            if(collideVal == 2){
                this.setyVelocity(-0.5);
                this.setxVelocity(0);
            }
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

    public void checkHealth() {
    }
}
