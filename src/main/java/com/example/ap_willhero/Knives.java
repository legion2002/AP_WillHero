package com.example.ap_willhero;

public class Knives implements Collidable{
    int lifeTime;

    public Knives(){

    }

    public int getLifeTime(){
        return this.lifeTime;
    }

    public void setLifeTime(int lifeTime){
        this.lifeTime = lifeTime;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public void collidesWith(Solid s) {

    }
}
