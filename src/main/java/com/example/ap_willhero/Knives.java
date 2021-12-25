package com.example.ap_willhero;

import java.io.Serializable;

public class Knives implements Serializable {
    int lifeTime;

    public Knives(){

    }

    public int getLifeTime(){
        return this.lifeTime;
    }

    public void setLifeTime(int lifeTime){
        this.lifeTime = lifeTime;
    }


}
