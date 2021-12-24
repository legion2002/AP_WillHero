package com.example.ap_willhero;

import javafx.scene.image.ImageView;

public class TreasureChest extends Solid implements Collidable{
    private ImageView chestImage;
    private boolean isStaged;
    final static int chestHeight = 55;
    final static int chestWidth = 74;


    TreasureChest(double x, double y){
        this.setPos( new Position(x, y));
        isStaged = false;

    }

    @Override
    public void setPos(Position p){
        super.setPos(p);
        if(chestImage != null){
            chestImage.setLayoutY(p.getyPos());
            chestImage.setLayoutX(p.getxPos());

        }


    }

    public void translateChestX(double i) {
        setPos(new Position(getPos().getxPos() + i, getPos().getyPos()));
    }
    public void translateChestY(double i) {
        setPos(new Position(getPos().getxPos(), getPos().getyPos() + i));
    }

    public void openChest(){
        
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

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public void collidesWith(Solid s) {

    }
}
