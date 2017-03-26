package com.mygdx.game.gesture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by zenek on 25.03.2017.
 */

public class OnTouchHandler implements GestureListener {

    private int width,heighy;
    private boolean pan=false;
    private boolean grab=false;
    private boolean blank=false;
    private int X=-1;
    private int Y=-1;
    private int deltaX=-1;
    private int deltaY=-1;
    private boolean playBt=false;
    private boolean autoButton=false;
    private int screen=1;

    public OnTouchHandler(int width,int height) {
        GestureDetector gestureDetector=new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetector);
        this.width=width;
        this.heighy=height;

    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        int start=6;
        int limit=16;

        this.pan=false;

        if(this.screen==1){
            start=4;
            limit=14;
        }
        else if(this.screen==2){
            start=6;
            limit=16;
        }
        float worldX=(x*10)/width;
        float worldY=(y*16)/heighy;
        if( worldX <= 10 && worldX >=0 && worldY >= start && worldY <= limit){
            this.X=(int) worldX;
            this.Y=(int) worldY-start;

            //  System.out.println("Coord: "+X+" "+Y);
            return true;
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        int start=6;
        int limit=16;

        if (this.screen == 1){
            start=4;
            limit=14;
        }else if (this.screen == 2){
            start=6;
            limit=16;
        }

        this.pan=true;

        float worldX=(x*10)/width;
        float worldY=(y*16)/heighy;

        System.out.println("Coord X: "+worldX+" Y:"+worldY+" "+heighy+" "+width);

        if( worldX <= 10 && worldX >=0 && worldY >= start && worldY <= limit){
            this.X=(int) worldX;
            this.Y=(int) worldY-start;

            System.out.println("Coord: "+X+" "+Y);
            return true;
        }

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        int start=6;
        int limit=16;


        if (this.screen == 1){
            start=4;
            limit=14;
        }else if (this.screen == 2){
            start=6;
            limit=16;
        }

        this.pan=false;

        float worldX=(x*10)/width;
        float worldY=(y*16)/heighy;

        System.out.println("Coord X: "+worldX+" Y:"+worldY+" "+heighy+" "+width);

        if( worldX <= 10 && worldX >=0 && worldY >= start && worldY <= limit){
            this.deltaX=(int) worldX;
            this.deltaY=(int) worldY-start;

            System.out.println("Coord: "+X+" "+Y);
            return true;
        }

        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
    public int tapButton(float x,float y){

        if	(x >= 0.2 && x <= 4.8 && y >= 14.2 && y <= 15.8){
            this.setAutoButton(true);
            return 1;
        }else if (x >= 5.2 && x <= 9.8 && y >= 14.2 && y <= 15.8){
            this.setPlayBt(true);
            return 2;
        }
        else return -1;

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeighy() {
        return heighy;
    }

    public void setHeighy(int heighy) {
        this.heighy = heighy;
    }

    public boolean isPan() {
        return pan;
    }

    public void setPan(boolean pan) {
        this.pan = pan;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public boolean isGrab() {
        return grab;
    }

    public void setGrab(boolean grab) {
        this.grab = grab;
    }

    public boolean isBlank() {
        return blank;
    }

    public void setBlank(boolean blank) {
        this.blank = blank;
    }

    public boolean isPlayBt() {
        return playBt;
    }

    public void setPlayBt(boolean playBt) {
        this.playBt = playBt;
    }

    public boolean isAutoButton() {
        return autoButton;
    }

    public void setAutoButton(boolean autoButton) {
        this.autoButton = autoButton;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }
}
