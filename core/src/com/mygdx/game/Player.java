package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.unit.Unit;
import com.mygdx.game.entity.Target;

public class Player {
    private ArrayList<Target> targets;
    private Texture unitSelTexture;
    
    public Player() {
        this.targets = new ArrayList<>();
        this.unitSelTexture = new Texture("unitSelection.png");
    }

    public void selectUnit(Unit unit) {
        if (unitIsTargeted(unit)) { return; }
        targets.add(new Target(unitSelTexture, unit));
    }

    public void unselect() {
        targets.clear();
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
}