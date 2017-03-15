package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGdxGame;

/**
 * Created by zenek on 15.03.2017.
 */

public class SplashScreen extends AbstractScreen {

    private Texture splashImage;

    public SplashScreen(final MyGdxGame game){
        super(game);

        init();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                //TODO
              System.out.print("Po sekundzie");
                game.setScreen(new GamePlayScreen(game));
            }
        },5);

    }

    private void init() {

        //TODO implement better assets loading when game grows
        splashImage=new Texture("splash.jpg");

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(splashImage,0,0);
        spriteBatch.draw(splashImage,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteBatch.end();
    }
}
