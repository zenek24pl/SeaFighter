package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.SplashScreen;

public class MyGdxGame extends Game {

	private final static String GAME_NAME="SeaFighter";
	public final static int WIDTH=700;
	public final static int HEIGHT=700;

	private  int points;



	private boolean paused;

	public void addPoint(){
		points++;
	}
	@Override
	public void create () {
		this.setScreen(new SplashScreen(this));
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
