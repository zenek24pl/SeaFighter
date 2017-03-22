package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by zenek on 19.03.2017.
 */

public class Inputs implements InputProcessor{
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            Gdx.app.exit();
            return true;
        }
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
        if (button == Input.Buttons.LEFT && BattleShips.getGamestate() < 10) {
            switch (BattleShips.getGamestate()) {
                case 0:
                    if (BattleShips.attemptPlace(getTwentyFourth(screenX), getTwentyFourth(screenY) - 12,
                            BattleShips.getRotated(), 5)) {
                        BattleShips.setGamestate(1);
                    }
                    return true;
                case 1:
                    if (BattleShips.attemptPlace(getTwentyFourth(screenX), getTwentyFourth(screenY) - 12,
                            BattleShips.getRotated(), 4)) {
                        BattleShips.setGamestate(2);
                    }
                    return true;
                case 2:
                    if (BattleShips.attemptPlace(getTwentyFourth(screenX), getTwentyFourth(screenY) - 12,
                            BattleShips.getRotated(), 3)) {
                        BattleShips.setGamestate(3);
                    }
                    return true;
                case 3:
                    if (BattleShips.attemptPlace(getTwentyFourth(screenX), getTwentyFourth(screenY) - 12,
                            BattleShips.getRotated(), 3)) {
                        BattleShips.setGamestate(4);
                    }
                    return true;
                case 4:
                    if (BattleShips.attemptPlace(getTwentyFourth(screenX), getTwentyFourth(screenY) - 12,
                            BattleShips.getRotated(), 2)) {
                        BattleShips.setGamestate(5);
                    }
                    return true;
            }

            int x;
            int y;
            if (screenX > 24 && screenY > 24) {
                x = getTwentyFourth(screenX);
                y = getTwentyFourth(screenY);

                if (x < 10 && y < 10) {
                    if (BattleShips.setStates(x, y)) {
                        BattleShips.cpu1.cpuMove();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private int getTwentyFourth(int screenP) {
        return screenP / 24 - 1;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        BattleShips.setMouseX(screenX);
        BattleShips.setMouseY(548 - screenY);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        BattleShips.setRotated();
        return true;
    }
}
