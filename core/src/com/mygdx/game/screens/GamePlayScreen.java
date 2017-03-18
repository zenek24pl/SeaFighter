package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.player.Player;

/**
 * Created by zenek on 15.03.2017.
 */

public class GamePlayScreen extends AbstractScreen {
    private Player player;
    private Button playerButton;
    private Label scoreLabel;

    public GamePlayScreen(MyGdxGame game) {
        super(game);
        init();
    }
    @Override
    protected void init(){
        initPlayer();
        initPlayerButton();
        initScoreLabel();
    }

    private void initScoreLabel() {
        Label.LabelStyle labelstyle=new Label.LabelStyle();
        labelstyle.font=new BitmapFont();
        scoreLabel=new Label("123",labelstyle);
        scoreLabel.setAlignment(Align.bottomLeft);
        stage.addActor(scoreLabel);
    }

    private void initPlayerButton() {
        playerButton=new Button(new Button.ButtonStyle());
        playerButton.setWidth(400);
        playerButton.setHeight(300);
        playerButton.setX(10);
        playerButton.setY(170);
        playerButton.setDebug(true);
        stage.addActor(playerButton);

        playerButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                player.reactOnClick();
                game.addPoint();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void initPlayer() {
        player=new Player();
        stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        System.out.println("Points " +game.getPoints());
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        stage.act();
    }
}
