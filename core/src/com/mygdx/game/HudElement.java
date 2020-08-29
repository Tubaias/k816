package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class HudElement {
    public Vector2 pos;
    public Sprite sprite;
    public boolean interactive;
    public boolean visible;
    
    public HudElement(Vector2 position, Texture tex, boolean interactive) {
        this.pos = position;
        this.sprite = new Sprite(tex);
        this.interactive = interactive;
        this.visible = true;
    }

    public void onClick() {
        if (interactive) {
            System.out.println("asdfasd");
        }
    }
}