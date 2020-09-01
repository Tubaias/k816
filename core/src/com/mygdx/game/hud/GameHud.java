package com.mygdx.game.hud;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class GameHud {
    public boolean isEnabled;
    public ArrayList<HudElement> elements;
    private HudElement unitPortraitBorder;
    public UnitPortrait unitPortrait;

    public GameHud() {
        this.isEnabled = true;
        this.elements = new ArrayList<>();

        createHud();

        toggle(false);
    }

    private void createHud() {
        elements.add(new HudElement(new Vector2(0, 0), new Texture("mapBorder.png"), true));
        elements.add(new HudElement(new Vector2(0, 0), new Texture("actionMenu.png"), true));

        unitPortraitBorder = new HudElement(new Vector2(0, 0), new Texture("unitPortraitBorder.png"), true);
        unitPortraitBorder.visible = false;
        elements.add(unitPortraitBorder);

        unitPortrait = new UnitPortrait(new Vector2(406, 4), new Sprite(new Texture("unitPortraitBase.png")), true);
        unitPortrait.visible = false;
    }

    public void setPortraitStatus(boolean status) {
        if (!isEnabled) { return; }

        unitPortraitBorder.visible = status;
        unitPortraitBorder.interactive = status;
        unitPortrait.visible = status;
        unitPortrait.interactive = status;
    }

    public void setUnitPortraitImage(Sprite portrait) {
        unitPortrait.setImage(portrait);
    }

    public void toggle(boolean portraitBorderStatus) {
        isEnabled = !isEnabled;

        for (HudElement e : elements) {
            e.visible = isEnabled;
        }

        if (isEnabled) {
            setPortraitStatus(portraitBorderStatus);
        }
    }
}