package com.example.ap_willhero;

import java.io.Serializable;

public class Shurikens implements Serializable {
    int lifeTime;

    public Shurikens(){

    }

    public int getLifeTime(){
        return this.lifeTime;
    }

    public void setLifeTime(int lifeTime){
        this.lifeTime = lifeTime;
    }


}
