package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

/**
 * Created by zenek on 25.03.2017.
 */

public class MenuScreen extends AbstractScreen implements InputProcessor {

    private Texture menuImg=new Texture("menu_screen.jpg");
    private Texture gameName=new Texture("Logo.png");
    private Texture newGameText=new Texture("play_bt.png");
    private Image newGameBt;
    private Image background;
    private Image title;

    public MenuScreen(final MyGdxGame game) {
        super(game);

       background=new Image(menuImg);
        title=new Image(gameName);
        newGameBt=new Image(newGameText);
        newGameBt.setPosition(Gdx.graphics.getWidth()/2- newGameBt.getWidth()/2,Gdx.graphics.getHeight()/2-newGameBt.getHeight()/2);
     //   newGameBt.setWidth(400);
    //    newGameBt.setHeight(400);
        newGameBt.addListener(new ClickListener(){
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
        {
            game.setScreen(new GamePlayScreen(game));
            return true;
        }
        });
        title.setPosition(Gdx.graphics.getWidth()/2- title.getWidth()/2,Gdx.graphics.getHeight()-200);
        background.setPosition(0,0);
        background.setWidth(Gdx.graphics.getWidth());
        background.setHeight(Gdx.graphics.getHeight());
     //   stage.addActor(titleSprite);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(background);
        stage.addActor(newGameBt);
        stage.addActor(title);

        stage.act();
        init();
       // gestureHandler=new OnTouchHandler(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
      //  gestureHandler.setScreen(1);
    }

    @Override
    protected void init() {

    }

    @Override
    public void render(float delta){
        super.render(delta);
        spriteBatch.begin();
        stage.draw();

        spriteBatch.end();
     //   spriteBatch.begin();
      //   spriteBatch.draw(menuImg,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

     //   spriteBatch.draw(titleSprite,titleSprite.getX(),titleSprite.getY());
   //     spriteBatch.end();
    //    spriteBatch.begin();
    //    spriteBatch.draw(menuImg,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
       // spriteBatch.draw(newGameBt,)
       // stage.draw();
    //     spriteBatch.draw(titleSprite,titleSprite.getX(),titleSprite.getY());
    //    spriteBatch.end();
        //   spriteBatch.draw(newGameSprite,newGameSprite.getX(),newGameSprite.getY());


    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
