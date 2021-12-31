package com.example.ap_willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

import java.io.Serializable;

public class Weapon1 extends Weapon implements Serializable {
    public Weapon1(){

    }

    @Override
    public void useWeapon(Game game) {

        Shurikens s = new Shurikens(game.getHero().getPos());
        game.getSolidList().add(s);
        game.getWeaponList().add(s);
        game.getController().getGameObjectsPane().getChildren().add(s.getShurikenImage());
        s.shootShuriken();





    }


}
