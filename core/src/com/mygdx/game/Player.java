package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.unit.Unit;
import com.mygdx.game.entity.Target;
import com.mygdx.game.hud.GameHud;

public class Player {
    private GameHud hud;
    private ArrayList<Target> targets;
    private Texture unitSelTexture;
    
    public Player() {
        this.targets = new ArrayList<>();
        this.unitSelTexture = new Texture("unitSelection.png");
        this.hud = new GameHud();
    }

    public void selectUnit(Unit unit, boolean toggle) {
        if (unitIsTargeted(unit)) {
            if (toggle) {
                deselectUnit(unit);
            }

            if (targets.isEmpty()) {
                hud.setPortraitStatus(false);
            }

            return;
        }

        targets.add(new Target(unitSelTexture, unit));
        hud.setPortraitStatus(true);
        hud.setUnitPortraitImage(unit.getPortraitSprite());
    }

    public void deselectUnit(Unit unit) {
        for (Target t : targets) {
            if (t.getUnit().equals(unit)) {
                targets.remove(t);
                return;
            }
        }
    }

    public void deselectAll() {
        targets.clear();
        hud.setPortraitStatus(false);
    }

    public void updateTargets() {
        for (Target t : targets) {
            t.updatePos();
        }
    }

    public ArrayList<Target> getTargets() {
        return targets;
    }

    public boolean unitIsTargeted(Unit unit) {
        for (Target t : targets) {
            if (t.getUnit().equals(unit)) {
                return true;
            }
        }

        return false;
    }

    public GameHud getHud() {
        return this.hud;
    }

    public void toggleHud() {
        hud.toggle(!targets.isEmpty());
    }
}