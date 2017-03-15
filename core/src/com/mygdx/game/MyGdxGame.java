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
//	private final static int X=0;
//	private final static int Y=0;




	private boolean paused;
	
	@Override
	public void create () {
		this.setScreen(new SplashScreen(this));
	}


	//
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
