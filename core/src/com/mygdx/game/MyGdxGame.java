package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.StringBuilder;
import com.mygdx.game.gesture.OnTouchHandler;
import com.mygdx.game.player.Player;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.SplashScreen;

public class MyGdxGame extends Game {

	private final static String Game_Prefs= "game.gdx.prefs";
	private final static String Game_Score= "game.gdx.score";
	private final static String GAME_NAME="SeaFighter";
	public final static int WIDTH=700;
	public final static int HEIGHT=700;

	private  int points;
	private Player player1;
	private Player player2;
	private Preferences preferences;
	Screen menu;

	private boolean paused;
	public void gesture(OnTouchHandler gestureHandler){

		if (gestureHandler.getX() >= 0 && gestureHandler.getY() >= 0){
			this.getPlayer1().play(gestureHandler.getX(),gestureHandler.getY(),this.getPlayer2());
		}
	}
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	public void addPoint(){
		points++;
		preferences.putInteger(Game_Score,points);
		preferences.flush();
	}
	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this));
	}

	private void init() {
		setPlayer1(new Player());
		setPlayer2(new Player());
		preferences=Gdx.app.getPreferences(Game_Prefs);
	}

	public int getPoints() {
		return points;
	}

	//
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
