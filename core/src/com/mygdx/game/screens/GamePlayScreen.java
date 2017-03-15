package com.mygdx.game.screens;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.player.Player;

/**
 * Created by zenek on 15.03.2017.
 */

public class GamePlayScreen extends AbstractScreen {
    private Player player;
    public GamePlayScreen(MyGdxGame game) {
        super(game);
        init();
    }
    @Override
    protected void init(){
        initPlayer();
    }

    private void initPlayer() {
        player=new Player();
        stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        stage.act();
    }
}
