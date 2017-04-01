package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    private Image backgroundImg;
    int nShip=0;
    int size=0;
    int direction=0;
    Ship tempShip;
    int tempShipPos=-1;

    OnTouchHandler onTouchHandler;
    public final static int WIDTH = 10;
    public final static int HEIGHT = 16;

    public PutShipScreen(MyGdxGame game) {
        super(game);
        init();
        onTouchHandler=new OnTouchHandler(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        onTouchHandler.setScreen(1);
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
        blank = new Texture("30.png");
        background=new Texture("background.jpg");
        backgroundImg=new Image(background);
        backgroundImg.setHeight(Gdx.graphics.getHeight());
        backgroundImg.setWidth(Gdx.graphics.getWidth());
        stage.addActor(backgroundImg);
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
        for (int i = 3; i < 13; i++) {
            for (int j = 2; j < 12; j++) {
                spriteBatch.draw (board_texture, i * 100, j * 100);
            }
        }
        spriteBatch.end();
        spriteBatch.begin();
  //      spriteBatch.draw(background,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteBatch.draw(singleShip, 1500, 800);
        dragAndDrop();
        spriteBatch.draw(twoPlacesShip, 1600, 800);
        spriteBatch.draw(triPlacesShip, 1500, 500);
        spriteBatch.draw(fourPlacesShip, 1750, 500);
        spriteBatch.draw(choose, 1400, 1000);
        spriteBatch.draw(vader, 2000, 0);
        spriteBatch.draw(logo, 100, Gdx.graphics.getHeight() - 180);
        spriteBatch.end();
        placeShip();

    }

    private void dragAndDrop() {
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
    }

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
