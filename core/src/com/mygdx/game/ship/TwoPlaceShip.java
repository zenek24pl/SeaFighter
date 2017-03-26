package com.mygdx.game.ship;

/**
 * Created by zenek on 26.03.2017.
 */

public class TwoPlaceShip extends Ship {
    public TwoPlaceShip(int positionX,int positionY,int direction) {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.setSize(2);
        this.setDirection(direction);
        this.setSink(false);
    }
}
