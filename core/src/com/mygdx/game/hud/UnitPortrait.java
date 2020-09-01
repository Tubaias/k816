package com.mygdx.game.hud;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class UnitPortrait {
    public Vector2 pos;
    public Sprite sprite;
    public boolean visible;
    public boolean interactive;

    public UnitPortrait(Vector2 pos, Sprite sprite, boolean interactive) {
        this.sprite = sprite;
        this.pos = pos;
        this.interactive = interactive;
        this.visible = true;
    }

    public void setImage(Sprite sprite) {
        this.sprite = sprite;
    }
}
