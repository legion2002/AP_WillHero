package com.example.ap_willhero;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Coin extends Solid implements Serializable {
    private int coinVal;
    transient private ImageView coinImage;
    final static int maximumCoinWidth = 30;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    transient private Game game;


    private boolean hasBeenCollected;

    public Coin(Game game, Position pos){
        this.game = game;

        setPos(new Position(pos.getxPos(), pos.getyPos()));
        coinVal = 1;
        hasBeenCollected = false;

    }

    public void setCoinImage(ImageView img){
        this.coinImage = img;
        img.setLayoutX(getPos().getxPos());
        img.setLayoutY(getPos().getyPos());
        setWidth(coinImage.getFitWidth());
        setHeight(coinImage.getFitHeight());
    }

    public int getCoinVal(){
        return this.coinVal;
    }

    public void setHasBeenCollected(boolean hasBeenCollected) {
        this.hasBeenCollected = hasBeenCollected;
    }

    public boolean isHasBeenCollected() {
        return hasBeenCollected;
    }

    public ImageView getCoinImage(){
        return this.coinImage;
    }


    public Position getPosition(){
        return this.getPos();
    }

    @Override
    public void setPos(Position p){
        super.setPos(p);
        if(coinImage != null){
            coinImage.setLayoutX(getPos().getxPos()) ;
            coinImage.setLayoutY(getPos().getyPos()) ;
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
}
