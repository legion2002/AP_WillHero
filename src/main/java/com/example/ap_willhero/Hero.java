package com.example.ap_willhero;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Hero extends Solid implements Collidable{

    private Helmet helmet;
    private Game game;
    private Weapon equippedWeapon;
    private float jumpHeight;
    private int currCoins;



    private int stepSize;
    private int health;
    private ImageView heroImage;

    private boolean touchingPlatform;


    Hero(Game game, ImageView img){
        this.currCoins = 0;
        this.game = game;
        this.heroImage = img;
        this.stepSize = 100;
        setWidth(heroImage.getFitWidth());
        setHeight(heroImage.getFitHeight());

        setPos(new Position(heroImage.getLayoutX(), heroImage.getLayoutY()));
        this.touchingPlatform = false;

    }

    public int getStepSize() {
        return stepSize;
    }

    public void moveForward(){
        //Had return type int
    }

    public void jump(){
        //Had return type int
    }

    public void useWeapon(){

    }

    public void collectCoins(){

    }

    public void upgradeWeapon(){

    }

    public void switchCurrentWeapon(){

    }

    public Helmet getHelmet(){
        return this.helmet;
    }

    public Weapon getEquippedWeapon(){
        return this.equippedWeapon;
    }

    public void setEquippedWeapon(Weapon weapon){
        this.equippedWeapon = weapon;
    }

    public int getCurrCoins(){
        return this.currCoins;
    }

    public void setCurrCoins(int coins){
        this.currCoins = coins;
    }

    public boolean getTouchingPlatform(){
        return touchingPlatform;
    }

    public Position getPosition(){
        return this.getPos();
    }
    @Override
    public void setPos(Position p){
        super.setPos(p);
        heroImage.setLayoutX(getPos().getxPos()) ;
        heroImage.setLayoutY(getPos().getyPos()) ;
    }

    @Override
    public void translateSolidX(double i) {
        setPos(new Position(getPos().getxPos() + i, getPos().getyPos()));
    }
    @Override
    public void translateSolidY(double i) {

        setPos(new Position(getPos().getxPos(), getPos().getyPos() + i));
    }




    public ImageView getHeroImage(){
        return this.heroImage;
    }

    public void setHeroImage(ImageView img){
        this.heroImage = img;

    }

    public void checkCollisionWithPlatform(){
        boolean flag = false;
        int offset = 5;
        for(Platform platform : game.getPlatformList()){
            //System.out.println("Hero Y : " + pos.getyPos());
            //System.out.println("Hero X : " + pos.getxPos());
            //System.out.println("Platform Y : " + platform.getLayoutY());
            //System.out.println("Platform X left boundary: " + platform.getLayoutX());
            //System.out.println("Platform X right boundary: " + (platform.getLayoutX() + platform.getWidth()));
            //if(pos.getyPos() + height >= (platform.getLayoutY() - offset) || pos.getyPos() + height <= (platform.getLayoutY() + offset) && pos.getxPos() >= (platform.getLayoutX() - 25) && (pos.getxPos() + width) <= (platform.getLayoutX() + 25 + platform.getWidth())){
            double left = getPos().getxPos();
            double right = left + getWidth();
            double top = getPos().getyPos();
            double bottom = top + getHeight();
            double platformLeft = platform.getPos().getxPos();
            double platformRight = platformLeft + platform.getWidth();
            double platformTop = platform.getPos().getyPos();
//            System.out.println("Top of hero: " + getPos().getyPos());
//            System.out.println("Bottom of hero : " + bottom);
//            System.out.println("Platform top : " + platformTop);
            if(left >= platformLeft &&  right <= platformRight) {
                //System.out.println("Platform in frame");
                //System.out.println("Top of hero: " + getPos().getyPos());
//                System.out.println("Bottom of hero : " + bottom);
//                System.out.println("Platform top : " + platformTop);
//                System.out.println("Platform left : " + platformLeft);
//                System.out.println("Platform right : " + platformRight);
//                System.out.println("hero left : " + left);
//                System.out.println("Hero right : " + right);

            }

            if(((right < platformRight && right > platformLeft) || (left > platformLeft && left < platformRight)) && (bottom > (platformTop - offset) && bottom < platformTop + offset)){
                flag = true;
                System.out.println("Touched platform");
                //game.getController().bounceBackHero();
                break;
            }
        }


        touchingPlatform = flag;
    }

    public boolean isDead(float abyssLevel){
        if(this.getPosition().getyPos() > abyssLevel){
            return true;
        }
        return false;
    }


    @Override
    public int hasCollided(Solid s){
        int offset = 5;
        double left = getPos().getxPos();
        double right = left + getWidth();
        double top = getPos().getyPos();
        double bottom = top + getHeight();
        double platformLeft = s.getPos().getxPos();
        double platformRight = platformLeft + s.getWidth();
        double platformTop = s.getPos().getyPos();

        if(left >= platformLeft &&  right <= platformRight) {


        }

        if(((right < platformRight && right > platformLeft) || (left > platformLeft && left < platformRight)) && (bottom > (platformTop - offset) && bottom < platformTop + offset)){

            System.out.println("Touched top of solid");

        }





        return 0;
    }

    @Override
    public void collidesWith(Solid s, int collideVal) {
        /*
        if(s instanceof Coin){
            Integer currentCoins = Integer.parseInt(game.getController().getCoinsCollectedLabel().getText());
            game.getController().getCoinsCollectedLabel().setText(Integer.toString(currentCoins + 1));
        }

         */

    }


}
