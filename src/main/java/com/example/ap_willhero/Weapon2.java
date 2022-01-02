package com.example.ap_willhero;

import java.io.Serializable;

public class Weapon2 extends Weapon implements Serializable {
    public Weapon2(){

    }

    @Override
    public void useWeapon(Game game) {
        for(int i = 0; i < getLevel(); i++){
            Knives k = new Knives(game.getHero().getPos());
            game.getSolidList().add(k);
            game.getWeaponList().add(k);
            game.getController().getGameObjectsPane().getChildren().add(k.getKnifeImage());
            k.shootKnife();


        }

    }

}
