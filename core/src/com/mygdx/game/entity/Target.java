package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.unit.Unit;

public class Target {
    private Vector2 pos;
    private Sprite sprite;
    private Unit unit;

    public Target(Texture tex, Unit unit) {
        this.sprite = new Sprite(tex);
        this.unit = unit;
    }

    public Vector2 getPos() {
        return this.pos;
    }

    public void updatePos() {
        pos = unit.pos;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public float getOffsetX() {
        return unit.getOffsetX();
    }

    public float getOffsetY() {
        return unit.getOffsetY();
    }

    public Unit getUnit() {
        return unit;
    }
}