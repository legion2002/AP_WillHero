package com.example.ap_willhero;

import java.io.Serializable;

public class Weapon2 extends Weapon implements Serializable {
    private static Weapon2 weapon2 = new Weapon2();
    public static Weapon2 getInstance(){
        if(weapon2 == null){
            weapon2 = new Weapon2();
        }
        return weapon2;
    }
    private Weapon2(){

    }

    @Override
    public void useWeapon(Game game) {
        for(int i = 0; i < getLevel(); i++){
            Knives k = new Knives(new Position(game.getHero().getPos().getxPos() + (i + 1) * Knives.knifeOffset, game.getHero().getPos().getyPos()));
            game.getSolidList().add(k);
            game.getWeaponList().add(k);
            game.getController().getGameObjectsPane().getChildren().add(k.getKnifeImage());
            k.shootKnife();


        }

    }

}
