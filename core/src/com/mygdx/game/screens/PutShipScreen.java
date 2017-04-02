package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;

import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gesture.OnTouchHandler;
import com.mygdx.game.player.Player;
import com.mygdx.game.ship.Dron;
import com.mygdx.game.ship.Ship;

/**
 * Created by zenek on 01.04.2017.
 */

public class PutShipScreen extends AbstractScreen implements InputProcessor {
    Sprite sprite;
    private Texture board_texture, singleShip, twoPlacesShip, triPlacesShip, fourPlacesShip, vader, choose, logo, border,blank,background;
    private Image backgroundImg,board;
    private Image dron,twoPl,triPl,fourPl;
    int nShip=0;
    int size=0;
    int direction=0;
    Ship tempShip;
    int tempShipPos=-1;

    OnTouchHandler onTouchHandler;
    public final static int WIDTH = 10;
    public final static int HEIGHT = 16;
    private InputMultiplexer inputMultiplexer =new InputMultiplexer(this);
// DragAndDrop dnd;
    Skin skin;

    public PutShipScreen(MyGdxGame game) {
        super(game);
        init();
        onTouchHandler=new OnTouchHandler(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        onTouchHandler.setScreen(1);
        Gdx.input.setInputProcessor(stage);

    }
    @Override
    protected void init() {
        skin=new Skin();

        board_texture = new Texture("cell_small.jpg");
        singleShip = new Texture("1_ship.jpg");
        twoPlacesShip = new Texture("2_2_ship.jpg");
        triPlacesShip = new Texture("3_places_ship.jpg");
        fourPlacesShip = new Texture("4_places_ship.jpg");
        skin.add("dron",singleShip);
        skin.add("two",twoPlacesShip);
        skin.add("tri",triPlacesShip);
        skin.add("four",fourPlacesShip);

        vader = new Texture("vader.jpg");
        choose = new Texture("wybierz.png");
        logo = new Texture("Logo.png");
        border = new Texture("6.png");
        blank = new Texture("30.png");
        background=new Texture("background.jpg");
        backgroundImg=new Image(background);
        backgroundImg.setHeight(Gdx.graphics.getHeight());
        backgroundImg.setWidth(Gdx.graphics.getWidth());
       //
        dron=new Image(skin,"dron");
        dron.setBounds(1500,800,100,100);
        twoPl=new Image(skin,"two");
        twoPl.setBounds(1600,800,twoPl.getWidth(),twoPl.getHeight());
        triPl=new Image(skin,"tri");
        triPl.setBounds(1500,500,triPl.getWidth(),triPl.getHeight());
        fourPl=new Image(skin,"four");
        fourPl.setBounds(1750,500,fourPl.getWidth(),fourPl.getHeight());
        //dron.setPosition(1500,800);


        stage.addActor(backgroundImg);
        for (int i = 3; i < 13; i++) {
            for (int j = 2; j < 12; j++) {
                board=new Image(board_texture);
                board.setPosition(i*100,j*100);
                stage.addActor(board);
            }
        }
        stage.addActor(dron);
        stage.addActor(twoPl);
        stage.addActor(triPl);
        stage.addActor(fourPl);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        onTouchHandler.setWidth(Gdx.graphics.getWidth());
        onTouchHandler.setHeighy(Gdx.graphics.getHeight());


        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
        spriteBatch.begin();
    /*    for (int i = 3; i < 13; i++) {
            for (int j = 2; j < 12; j++) {
                spriteBatch.draw (board_texture, i * 100, j * 100);
            }
        }*/
        spriteBatch.end();
        spriteBatch.begin();

        spriteBatch.draw(choose, 1400, 1000);
        spriteBatch.draw(vader, 2000, 0);
        spriteBatch.draw(logo, 100, Gdx.graphics.getHeight() - 180);
        spriteBatch.end();

        dron.addListener(new DragListener(){
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                dron.moveBy(x-dron.getWidth(),y-dron.getHeight());
            }
        } );
        twoPl.addListener(new DragListener(){
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                twoPl.moveBy(x-twoPl.getWidth(),y-twoPl.getHeight());
            }
        } );
        triPl.addListener(new DragListener(){
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                triPl.moveBy(x-triPl.getWidth(),y-triPl.getHeight());
            }
        } );
        fourPl.addListener(new DragListener(){
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                fourPl.moveBy(x-fourPl.getWidth(),y-fourPl.getHeight());
            }
        } );
  //     placeShip();

    }

  /*  private void dragAndDrop() {
        Player player1=game.getPlayer1();
        if(onTouchHandler.isPan()){
            if(!onTouchHandler.isGrab() && !onTouchHandler.isBlank())
                if(onTouchHandler.getX()>=0 && onTouchHandler.getX()<=9 && onTouchHandler.getY()>=0 && onTouchHandler.getY()<=9)
                    if(player1.getMyBoard().getBoard()[onTouchHandler.getX()][onTouchHandler.getY()]>=0){
                        size=player1.getShips().get((int)player1.getMyBoard().getBoard()[onTouchHandler.getX()][onTouchHandler.getY()]).getSize();
                        direction=player1.getShips().get((int)player1.getMyBoard().getBoard()[onTouchHandler.getX()][onTouchHandler.getY()]).getDirection();onTouchHandler.setGrab(true);

                        tempShipPos=(int)player1.getMyBoard().getBoard()[onTouchHandler.getX()][onTouchHandler.getY()];
                        tempShip=player1.getShips().get(tempShipPos);

                        tempShip.cleanBoard(player1.getMyBoard());
                        onTouchHandler.setGrab(true);
                    }else {
                        onTouchHandler.setBlank(true);
                    }
                    if(onTouchHandler.isGrab())
                        if(direction==0)
                            for(int i=0;i<size;i++){

                            }

        }
        if(!onTouchHandler.isPan())
            if(onTouchHandler.getDeltaX()>=0 && onTouchHandler.getDeltaY()>=0){
                if(tempShipPos>=0 && !onTouchHandler.isBlank() && onTouchHandler.isGrab()){

                    int oldX=tempShip.getPositionX();
                    int oldY=tempShip.getPositionY();

                    tempShip.setPositionX(onTouchHandler.getDeltaX());
                    tempShip.setPositionY(onTouchHandler.getDeltaY());

                    player1.getShips().add(tempShipPos,tempShip);

                    onTouchHandler.setX(-1);
                    onTouchHandler.setY(-1);

                    if(!player1.validShip(tempShipPos) || !player1.fillBoardShip(tempShipPos)){

                        player1.getShips().remove(tempShipPos);

                        tempShip.setPositionX(oldX);
                        tempShip.setPositionY(oldY);

                        player1.getShips().add(tempShipPos,tempShip);

                        player1.validShip(tempShipPos);
                        player1.fillBoardShip(tempShipPos);
                    }
                    tempShipPos=-1;
                }
                onTouchHandler.setGrab(false);
                onTouchHandler.setBlank(false);
            }
    }*/

    public void placeShip(){
        Player player1=game.getPlayer1();
        if(!onTouchHandler.isPan() && !onTouchHandler.isGrab())
            if(nShip<2)
            if(onTouchHandler.getX()>=0 && onTouchHandler.getX()<=9 && onTouchHandler.getY()>=0 && onTouchHandler.getY()<=9)
                if(player1.getMyBoard().getBoard()[onTouchHandler.getX()][onTouchHandler.getY()]==-8)
                    if(addShip(onTouchHandler.getX(),onTouchHandler.getY(),0,nShip)){
                        nShip++;
                        onTouchHandler.setX(-1);
                        onTouchHandler.setX(-1);

                    }
    }

    public boolean addShip(int x,int y,int direction,int ship){
        Ship[] createShips={new Dron(x,y,direction),new Dron(x,y,direction)};
        Player player1=game.getPlayer1();
        player1.getShips().add(createShips[ship]);

        if(!player1.validShip(ship) || !player1.fillBoardShip(ship)){
            Ship ship1=player1.getShips().get(ship);
            player1.getShips().remove(ship1);

            if(direction==0)
                ship1.setDirection(1);
            else
                ship1.setDirection(0);

            player1.getShips().add(ship,ship1);

            if(!player1.validShip(ship) ||!player1.fillBoardShip(ship)){
                player1.getShips().remove(ship);
                player1.getMyBoard().printBoard();
                return false;
            }
        }
        return true;

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
