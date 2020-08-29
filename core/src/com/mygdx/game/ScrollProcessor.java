package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;

// this library sux
public class ScrollProcessor implements InputProcessor {
    public int scrollAmount;

    public ScrollProcessor() {
        scrollAmount = 0;
    }

    @Override
	public boolean scrolled(int amount) {
        scrollAmount = amount;
		return false;
	}

    // UNWANTED STUFF
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
}