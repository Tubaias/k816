package com.mygdx.game.hud.button;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Player;

public interface Button {
    public Sprite getSprite();
    public void OnClick(Player player);
}
