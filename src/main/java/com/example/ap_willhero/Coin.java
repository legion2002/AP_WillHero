package com.example.ap_willhero;

import javafx.scene.image.ImageView;

public class Coin extends Solid implements Collidable{
    private int coinVal;
    private ImageView coinImage;
    private Game game;

    public Coin(Game game, Position pos, ImageView image){
        this.game = game;
        this.coinImage = image;
        setWidth(coinImage.getFitWidth());
        setHeight(coinImage.getFitHeight());
        setPos(new Position(pos.getxPos(), pos.getyPos()));

    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public void collidesWith(Solid s) {

    }

    public Position getPosition(){
        return this.getPos();
    }

    @Override
    public void setPos(Position p){
        super.setPos(p);
        coinImage.setLayoutX(getPos().getxPos()) ;
        coinImage.setLayoutY(getPos().getyPos()) ;
    }
    @Override
    public void translateSolidX(double i) {
        setPos(new Position(getPos().getxPos() + i, getPos().getyPos()));
    }
    @Override
    public void translateSolidY(double i) {

        setPos(new Position(getPos().getxPos(), getPos().getyPos() + i));
    }
}
