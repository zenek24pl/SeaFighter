package com.mygdx.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by zenek on 15.03.2017.
 */

public class Player extends Image {

    private final static int WIDTH=88;
    private final static int HEIGHT=150;

    private final static int STARTING_X=200;
    private final static int STARTING_Y=300;

    public Player(){
        super(new Texture("badlogic.jpg"));
        this.setOrigin(WIDTH/2,HEIGHT/2);
        this.setSize(WIDTH,HEIGHT);
        this.setPosition(STARTING_X,STARTING_Y);
    }

    public void reactOnClick() {
        Action testAction= Actions.moveBy(10,10);
        this.addAction(testAction);
    }
}
