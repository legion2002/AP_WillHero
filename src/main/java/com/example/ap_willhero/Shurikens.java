package com.example.ap_willhero;

public class Shurikens implements Collidable{
    int lifeTime;

    public Shurikens(){

    }

    public int getLifeTime(){
        return this.lifeTime;
    }

    public void setLifeTime(int lifeTime){
        this.lifeTime = lifeTime;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public void collidesWith(Solid s) {

    }
}
