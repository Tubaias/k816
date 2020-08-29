package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GameHud {
    public boolean isEnabled;
    public ArrayList<HudElement> elements;
    private HudElement unitPortraitBorder;

    public GameHud() {
        this.isEnabled = true;
        this.elements = new ArrayList<>();

        createHud();
    }

    private void createHud() {
        elements.add(new HudElement(new Vector2(0, 0), new Texture("mapBorder.png"), true));
        elements.add(new HudElement(new Vector2(0, 0), new Texture("actionMenu.png"), true));

        unitPortraitBorder = new HudElement(new Vector2(0, 0), new Texture("unitPortraitBorder.png"), true);
        unitPortraitBorder.visible = false;
        elements.add(unitPortraitBorder);
    }

    public void enablePortraitBorder() {
        if (!isEnabled) { return; }

        unitPortraitBorder.visible = true;
        unitPortraitBorder.interactive = true;
    }

    public void disablePortraitBorder() {
        unitPortraitBorder.visible = false;
        unitPortraitBorder.interactive = false;
    }

    public void toggle(boolean portraitBorderStatus) {
        isEnabled = !isEnabled;

        for (HudElement e : elements) {
            e.visible = isEnabled;
        }

        if (isEnabled) {
            unitPortraitBorder.visible = portraitBorderStatus;
            unitPortraitBorder.interactive = portraitBorderStatus;
        }
    }
}