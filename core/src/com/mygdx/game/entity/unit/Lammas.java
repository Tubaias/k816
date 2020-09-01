package com.mygdx.game.entity.unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.targetIndicator;

public class Lammas extends Unit {
    int number;

    public Lammas(Texture tex, Texture portraitTex, float x, float y, int number) {
        super(tex, portraitTex, x, y);
        this.speed = 3;

        this.number = number;
    }

    @Override
    public void move() {
        Vector2 movement = new Vector2();

        if (!targetQueue.isEmpty()) {
            targetIndicator target = targetQueue.peek();;

            if (Math.abs(pos.x - target.pos.x) > 5 || Math.abs(pos.y - target.pos.y) > 5) {
                movement = target.pos.cpy().sub(pos).nor();
                movement.scl(Gdx.graphics.getDeltaTime() / (1f / 60f));
                movement.scl(speed);
            } else {
                targetQueue.poll();
            }
        }

        pos.add(movement);
    }

    @Override
    public void clearQueue() {
        targetQueue.clear();
    }

    @Override
    public void addTarget(Vector2 targetPos) {
        targetIndicator t = new targetIndicator(new Sprite(targetTex));
        t.setPos(targetPos);
        targetQueue.add(t);
    }
}