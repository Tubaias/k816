package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entity.unit.Unit;
import com.mygdx.game.entity.Target;
import com.mygdx.game.entity.targetIndicator;

public class Drawer {
    private final int TILEWIDTH = 128;
    private final int TILEHEIGHT = 64;
    private final int HALFTILEWIDTH = 64;
    private final int HALFTILEHEIGHT = 32;

    private SpriteBatch batch;
    private SpriteBatch uiBatch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Player player;
    private World world;
    private BitmapFont font;
    private int fps;

    public Drawer(Player player, World world) {
        this.player = player;
        this.world = world;

        batch = new SpriteBatch();
        uiBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        font = new BitmapFont();
        font.setColor(Color.GOLD);
    }

    public void drawFrame() {
        updateFPS();

        camera.update();
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        viewport.update(w, h);
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        batch.begin();

        drawGround();
        drawObjects();

        for (Unit unit : world.getUnits()) {
            for (targetIndicator target : unit.targetQueue) {
                batch.draw(target.getSprite(), 
                    target.pos.x + target.getOffsetX(), 
                    target.pos.y + target.getOffsetY());
            }
    
            batch.draw(unit.getSprite(), 
                unit.pos.x + unit.getOffsetX(), 
                unit.pos.y + unit.getOffsetY());

            font.draw(batch, "queue: " + unit.targetQueue.size(), unit.pos.x + unit.getOffsetX() + 5, unit.pos.y - 15);
        }
        
        for (Target t : player.getTargets()) {
            batch.draw(t.getSprite(), 
                t.getPos().x + t.getOffsetX(), 
                t.getPos().y + t.getOffsetY());
        }

        batch.end();

        drawUI();
    }

    private void drawGround() {
        TileMap map = world.getTerrainMap();

        for (GridPoint3 tile : map.sortedTiles) {
            int posX = (tile.x - tile.y) * HALFTILEWIDTH;
            int posY = (tile.x + tile.y) * HALFTILEHEIGHT;

            if (posX + TILEWIDTH > camera.frustum.planePoints[0].x 
                && posX < camera.frustum.planePoints[1].x
                && posY + TILEHEIGHT > camera.frustum.planePoints[0].y
                && posY < camera.frustum.planePoints[2].y) {
                    batch.draw(map.getTexture(tile.z), posX, posY);
            }
        }
    }

    private void drawObjects() {
        TileMap map = world.getObjectMap();

        for (int x = map.width - 1; x >= 0; x--) {
            for (int y = map.height - 1; y >= 0; y--) {
                int posX = (x - y) * HALFTILEWIDTH;
                int posY = (x + y) * HALFTILEHEIGHT;

                if (map.tiles[x][y] != 0) {
                    if (posX + TILEWIDTH > camera.frustum.planePoints[0].x 
                        && posX < camera.frustum.planePoints[1].x
                        && posY + TILEHEIGHT * 4 > camera.frustum.planePoints[0].y
                        && posY < camera.frustum.planePoints[2].y) {
                            batch.draw(map.getTexture(map.tiles[x][y] - 1), posX, posY);
                    }
                }
            }
        }
    }

    private void drawUI() {
        uiBatch.begin();

        font.draw(uiBatch, "" + fps, 10, 20);

        for (HudElement elem : player.getHud().elements) {
            if (elem.visible) {
                uiBatch.draw(elem.sprite, elem.pos.x, elem.pos.y);
            }
        }

        uiBatch.end();
    }

    private void updateFPS() {
        fps = (int) (1 / Gdx.graphics.getDeltaTime());
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public Viewport getViewport() {
        return viewport;
    }
}