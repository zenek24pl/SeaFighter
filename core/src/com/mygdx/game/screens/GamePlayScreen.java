package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gesture.OnTouchHandler;
import com.mygdx.game.player.Player;

/**
 * Created by zenek on 15.03.2017.
 */

public class GamePlayScreen extends AbstractScreen {

    private Label scoreLabel;
    String nShips;
    OnTouchHandler onTouchHandler;
    MyGdxGame gdxGame;
    Texture ship;
    Texture hit;
    Texture miss;
    Texture blank;

    Viewport viewport;
    Sprite sprite;

    private Texture board_texture, singleShip, twoPlacesShip, triPlacesShip, fourPlacesShip, vader, choose, logo, border;

    public GamePlayScreen(MyGdxGame game) {
        super(game);
        init();
    }

    @Override
    protected void init() {
        board_texture = new Texture("cell_small.jpg");
        singleShip = new Texture("1_ship.jpg");
        twoPlacesShip = new Texture("2_2_ship.jpg");
        triPlacesShip = new Texture("3_places_ship.jpg");
        fourPlacesShip = new Texture("4_places_ship.jpg");
        vader = new Texture("vader.jpg");
        choose = new Texture("wybierz.png");
        logo = new Texture("Logo.png");
        border = new Texture("6.png");
        ship = new Texture("32.png");
        hit = new Texture("36tes3t.jpg");
        miss = new Texture("36test6.jpg");
        blank = new Texture("30.png");
        this.game.getPlayer1().generateRandomBoard();
        onTouchHandler=new OnTouchHandler(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        onTouchHandler.setScreen(2);
        initScoreLabel();
        initBoard();
    }

    private static int[][] initBoard() {
        int[][] board = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = 0;
            }
        }

        return board;
    }

    public static boolean setStates(int x, int y) {
        return false;
    }


    private void initScoreLabel() {
        Label.LabelStyle labelstyle = new Label.LabelStyle();
        labelstyle.font = new BitmapFont();
        scoreLabel = new Label("", labelstyle);
        scoreLabel.setFontScale(4);
        scoreLabel.setWrap(true);
        //     scoreLabel.setHeight(100);
        // scoreLabel.setWidth(100);
        scoreLabel.setPosition(Gdx.graphics.getWidth() - 300, Gdx.graphics.getHeight() - 100, Align.left);
        // scoreLabel.setAlignment(Align.topRight);
        stage.addActor(scoreLabel);
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        System.out.println("Points " + game.getPoints());
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
       ;/*
        for (int i = 3; i < 13; i++) {
            for (int j = 2; j < 12; j++) {
                spriteBatch.draw (board_texture, i * 100, j * 100);
            }
        }
        */
    //    spriteBatch.begin();
    //    drawTable(game.getPlayer2(),1);
   //     spriteBatch.end();

        spriteBatch.begin();
        drawTable(game.getPlayer1(),0.5f);
        spriteBatch.end();
        spriteBatch.begin();
        spriteBatch.draw(singleShip, 1500, 800);
        spriteBatch.draw(twoPlacesShip, 1600, 800);
        spriteBatch.draw(triPlacesShip, 1500, 500);
        spriteBatch.draw(fourPlacesShip, 1750, 500);
        spriteBatch.draw(choose, 1400, 1000);
        spriteBatch.draw(vader, 2000, 0);
        spriteBatch.draw(logo, 100, Gdx.graphics.getHeight() - 180);
        spriteBatch.draw(border, 250, 150);
        spriteBatch.end();
        onTouchHandler.setWidth(Gdx.graphics.getWidth());
        onTouchHandler.setHeighy(Gdx.graphics.getHeight());

        game.gesture(onTouchHandler);
        System.out.println("x:"+onTouchHandler.getX()+" y: "+onTouchHandler.getY());


    }

    private void update() {
        scoreLabel.setText("Score " + game.getPoints());
        stage.act();
    }
    void drawTable(Player player, float scale)
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (player.getMyBoard().getBoard()[j][i] == 0
                        || player.getMyBoard().getBoard()[j][i] == 1
                        || player.getMyBoard().getBoard()[j][i] == 2
                        || player.getMyBoard().getBoard()[j][i] == 3
                        || player.getMyBoard().getBoard()[j][i] == 4) {

                    if (scale == 1)
                        spriteBatch.draw(blank, j * scale, (9 - i) * scale, scale, scale);
                    else
                        spriteBatch.draw(ship, j * scale, (9 - i) * scale, scale, scale);
                    continue;
                }
                if (player.getMyBoard().getBoard()[j][i] == 0.5
                        || player.getMyBoard().getBoard()[j][i] == 1.5
                        || player.getMyBoard().getBoard()[j][i] == 2.5
                        || player.getMyBoard().getBoard()[j][i] == 3.5
                        || player.getMyBoard().getBoard()[j][i] == 4.5) {

                    spriteBatch.draw(hit, j * scale, +(9 - i) * scale, scale, scale);
                    continue;
                }
                if (player.getMyBoard().getBoard()[j][i] == -9) {
                    spriteBatch.draw(miss, j * scale, (9 - i) * scale, scale, scale);
                    continue;
                }

                if (player.getMyBoard().getBoard()[j][i] == -8) {
                    spriteBatch.draw(blank, j * scale, (9 - i) * scale, scale, scale);
                    continue;
                }
            }
        }
    }

}
