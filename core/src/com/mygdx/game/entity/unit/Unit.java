package com.mygdx.game.entity.unit;

import java.util.ArrayDeque;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.targetIndicator;

public abstract class Unit {
    public Vector2 pos;
    public ArrayDeque<targetIndicator> targetQueue;

    protected Sprite sprite;
    protected float speed;
    protected Texture targetTex;

    public Unit(Texture tex, float x, float y) {
        this.pos = new Vector2(x, y);
        this.sprite = new Sprite(tex);
        this.targetTex = new Texture("target.png");
        this.targetQueue = new ArrayDeque<>();
    }

    public abstract void move();
    public abstract void clearQueue();
    public abstract void addTarget(Vector2 targetPos);
    
    public Sprite getSprite() {
        return this.sprite;
    }

    public float getWidth() {
        return this.sprite.getWidth();
    }

    public float getHeight() {
        return this.sprite.getHeight();
    }

    public float getOffsetX() {
        return this.sprite.getWidth() * -0.5f;
    }

    public float getOffsetY() {
        return this.sprite.getHeight() * -0.1f;
    }
}