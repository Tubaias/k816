package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class targetIndicator {
    public Vector2 pos;
    public boolean active;
    private Sprite sprite;

    public targetIndicator(Sprite img) {
        pos = new Vector2();
        this.sprite = img;

        this.active = false;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public float getOffsetX() {
        return this.sprite.getWidth() * -0.5f;
    }

    public float getOffsetY() {
        return this.sprite.getHeight() * -0.5f;
    }

    public void setPos(Vector2 newPos) {
        this.pos = newPos;
    }
}