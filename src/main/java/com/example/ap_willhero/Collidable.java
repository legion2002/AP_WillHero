package com.example.ap_willhero;

public interface Collidable {
    public int hasCollided(Solid s); // if 0 - no collision, 1 - collision with left, 2 - collision with top , 3 - collision with right, 4 collision with bottom
    public void collidesWith(Solid s, int collideVal);
}
