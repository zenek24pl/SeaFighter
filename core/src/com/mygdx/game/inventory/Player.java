package com.mygdx.game.inventory;

import java.util.ArrayList;

/**
 * Created by zenek on 15.04.2017.
 */

public class Player {
    private Board ownBoard;
    private int points;
    private boolean play;
    private boolean lost;
    private String name;
    private ArrayList<Item> ships;

    public Player() {
        this.ownBoard=new Board();
        this.points = 0;
        this.play = false;
        this.lost = false;
        this.ships = new ArrayList<Item>();
    }

    public ArrayList<Item> getShips() {
        return ships;
    }

    public void eraseShips(){
        ships.clear();

        System.out.print("N SHIPS: "+ships.size()+ " ");
    }

}
