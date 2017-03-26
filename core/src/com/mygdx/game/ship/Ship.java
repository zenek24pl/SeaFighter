package com.mygdx.game.ship;

import com.mygdx.game.board.Board;

/**
 * Created by zenek on 26.03.2017.
 */

public class Ship {

    private int positionX,positionY;

    private int direction;

    private int size;

    private boolean isSink;

    public void cleanBoard(Board board){
        if(this.direction==0){
            for(int i=0;i<this.size;i++){
                board.getBoard()[this.getPositionX()+i][this.getPositionY()]=0;
            }
        }else{
            for (int i=0; i < this.size; i++)
                board.getBoard()[this.getPositionX()][this.getPositionY()+i]=-8;
        }
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isSink() {
        return isSink;
    }

    public void setSink(boolean sink) {
        isSink = sink;
    }
}
