package com.example.ap_willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

import java.io.Serializable;

public class Weapon1 extends Weapon implements Serializable {
    private static Weapon1 weapon1 = new Weapon1();
    public static Weapon1 getInstance(){
        if(weapon1 == null){
            weapon1 = new Weapon1();
        }
        return weapon1;
    }
    private Weapon1(){

    }

    @Override
    public void useWeapon(Game game) {


        for(int i = 0; i < getLevel(); i++){
            Shurikens s = new Shurikens(new Position(game.getHero().getPos().getxPos() + (i + 1) * Shurikens.shurikenOffset, game.getHero().getPos().getyPos()));
            game.getSolidList().add(s);
            game.getWeaponList().add(s);
            game.getController().getGameObjectsPane().getChildren().add(s.getShurikenImage());
            s.shootShuriken();

        }





    }


}
