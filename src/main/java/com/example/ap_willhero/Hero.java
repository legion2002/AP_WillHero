package com.example.ap_willhero;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Hero extends Solid implements Collidable, Serializable {

    private Helmet helmet;

    public void setGame(Game game) {
        this.game = game;
    }

    transient private Game game;
    private Weapon equippedWeapon;
    private float jumpHeight;
    private int currCoins;

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    private int currentLocation;


    private int stepSize;
    private int health;
    transient private ImageView heroImage;

    private boolean touchingPlatform;



    Hero(Game game, ImageView img){
        this.currCoins = 0;
        this.game = game;
        this.heroImage = img;
        this.stepSize = 100;
        this.helmet = new Helmet();
//        this.equippedWeapon = helmet.getWeapon1();
        setWidth(heroImage.getFitWidth());
        setHeight(heroImage.getFitHeight());

        setPos(new Position(heroImage.getLayoutX(), heroImage.getLayoutY()));
        this.touchingPlatform = false;
        this.currentLocation = 0;

    }



    public int getStepSize() {
        return stepSize;
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
        this.heroImage.setLayoutX(getPos().getxPos());
        this.heroImage.setLayoutY(getPos().getyPos());
        setWidth(heroImage.getFitWidth());
        setHeight(heroImage.getFitHeight());

    }


    public boolean isDead(float abyssLevel){
        if(this.getPosition().getyPos() > abyssLevel){
            return true;
        }
        return false;
    }

    public void increaseCurrCoin(int value){
        currCoins += value;
        game.getController().getCoinsCollectedLabel().setText(Integer.toString(currCoins));
    }




    @Override
    public void collidesWith(Solid s, int collideVal) {

        if(s instanceof Coin){
            if(! ((Coin)s).isHasBeenCollected()){

                ((Coin) s).setHasBeenCollected(true);
                increaseCurrCoin(((Coin) s).getCoinVal());

            }
        }

        if(s instanceof Platform ){
            if(collideVal == 2){
                this.setyVelocity(-0.5);
                this.setxVelocity(0);
            }
            else if (collideVal == 1){
                this.setyVelocity(-0.2);
                this.setxVelocity(0);
            }
            else if(collideVal == 4){
                System.out.println("HERO CAME UNDER PLATFORM");
                game.killHero();
            }

        }

        if(s instanceof FallingPlatform ){
            if(collideVal == 2){
                this.setyVelocity(-0.5);
                this.setxVelocity(0);
                if(!((FallingPlatform) s).isAnimationDone()){
                    ((FallingPlatform) s).startFallingPlatformAnimation();
                    //((FallingPlatform) s).setAnimationDone(true);
                }

            }
            else if (collideVal == 1){
                this.setyVelocity(-0.2);
                this.setxVelocity(0);
            }
            else if(collideVal == 4){
                System.out.println("HERO CAME UNDER PLATFORM");
                System.exit(0);
            }
        }

        if(s instanceof Orc){
            if(collideVal == 1){
                //make colliding with left here
                if(s instanceof BossOrc){
                    System.out.println("COLLIDED WITH LEFT OF BOSS ORC");
                    this.setyVelocity(0);
                    this.setxVelocity(-0.5);
                }
                else{
                    System.out.println("COLLIDED WITH LEFT OF ORCCCCC");
                    System.out.println("Hero X velocity is " + this.getxVelocity());
                    this.setxVelocity(-this.getxVelocity()/2);
                    this.setyVelocity(0);
                    s.setxVelocity(0.25);
                }



            }
            else if(collideVal == 2){
                this.setyVelocity((-0.2));
            }
            else if(collideVal == 4){
                System.out.println("HERO CAME UNDER ORC");
                game.killHero();
            }
        }

        if(s instanceof TreasureChest){
            System.out.println("do chest animation here");
//            ((TreasureChest)s).doChestAnimation();
            ((TreasureChest) s).setOpened(true);
                if(s instanceof WeaponChest){
                    System.out.println("This is a weapon chest");
                    int weaponType = ((WeaponChest) s).getWeaponType();

                    if(weaponType == 1){

                        helmet.getWeapon1().increaseLevel();
                        game.getController().getShurikenLabel().setText(Integer.toString(helmet.getWeapon1().getLevel()));
                        if(equippedWeapon == null){
                            equippedWeapon = helmet.getWeapon1();
                        }

                    }
                    else if(weaponType == 2){

                        helmet.getWeapon2().increaseLevel();
                        game.getController().getKnifeLabel().setText(Integer.toString(helmet.getWeapon2().getLevel()));
                        if(equippedWeapon == null){
                            equippedWeapon = helmet.getWeapon2();
                        }

                    }
                }
                else if(s instanceof CoinChest){
                    System.out.println("This is a coin chest");
                    increaseCurrCoin(((CoinChest) s).getTreasureCoins());
                }
        }






    }


}
