package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
    private Texture board_texture,singleShip,twoPlacesShip,triPlacesShip,fourPlacesShip,vader,choose,logo;
    private static boolean[][] cpuBoard = new boolean[10][10];
    private static boolean[][] playerBoard = new boolean[10][10];

    public GamePlayScreen(MyGdxGame game) {
        super(game);
        init();
    }
    @Override
    protected void init(){
        board_texture =new Texture("cell_small.jpg");
        singleShip=new Texture("1_ship.jpg");
        twoPlacesShip=new Texture("2_2_ship.jpg");
        triPlacesShip=new Texture("3_places_ship.jpg");
        fourPlacesShip=new Texture("4_places_ship.jpg");
        vader=new Texture("vader.jpg");
       choose=new Texture("wybierz.png");
        logo=new Texture("Logo.png");

        initPlayer();
     //   initPlayerButton();
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

    public static boolean setStates(int x,int y){
        return false;
    }



    private void initScoreLabel() {
        Label.LabelStyle labelstyle=new Label.LabelStyle();
        labelstyle.font=new BitmapFont();
        scoreLabel=new Label("",labelstyle);
        scoreLabel.setFontScale(4);
        scoreLabel.setWrap(true);
   //     scoreLabel.setHeight(100);
       // scoreLabel.setWidth(100);
        scoreLabel.setPosition(Gdx.graphics.getWidth()-300,Gdx.graphics.getHeight()-100,Align.left);
       // scoreLabel.setAlignment(Align.topRight);
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
        spriteBatch.begin();
        for (int i=3;i<13;i++){
            for(int j=2;j<12;j++) {
                spriteBatch.draw(board_texture, i * 100, j * 100);
            }
        }
        spriteBatch.draw(singleShip,1500,800);
        spriteBatch.draw(twoPlacesShip,1600,800);
        spriteBatch.draw(triPlacesShip,1500,500);
        spriteBatch.draw(fourPlacesShip,1750,500);
        spriteBatch.draw(choose,1400,1000);
        spriteBatch.draw(vader,2000,0);
        spriteBatch.draw(logo,100,Gdx.graphics.getHeight()-180);
        spriteBatch.end();
    }

    private void update() {
        scoreLabel.setText("Score "+game.getPoints());
        stage.act();
    }

}
