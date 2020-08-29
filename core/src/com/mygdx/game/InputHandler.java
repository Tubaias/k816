package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entity.unit.Unit;
import com.mygdx.game.entity.Target;

public class InputHandler {
    private Player player;
    private World world;
    private Viewport viewport;
    private OrthographicCamera camera;
    private ScrollProcessor scrollProc;

    private boolean fullscreen = false;

    public InputHandler(Player player, World world, Viewport viewport) {
        this.player = player;
        this.world = world;
        this.viewport = viewport;
        this.camera = (OrthographicCamera) viewport.getCamera();

        this.scrollProc = new ScrollProcessor();
        Gdx.input.setInputProcessor(scrollProc);
    }

    public void handleInputs() {
        handleSystemKeys();
        cameraZoom();
        cameraPan();
        handleLeftClick();
        handleRightClick();

        if (Gdx.input.isKeyPressed(Keys.G)) {
            world.generate();
        }

        if (Gdx.input.isKeyJustPressed(Keys.R)) {
            world.drawSorted = !world.drawSorted;
        }
    }

    private void handleSystemKeys() {
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyJustPressed(Keys.F11) || (Gdx.input.isKeyPressed(Keys.ALT_LEFT) && Gdx.input.isKeyJustPressed(Keys.ENTER))) {
            if (fullscreen) {
                Gdx.graphics.setWindowedMode(1280, 720);
                fullscreen = false;
            } else {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                fullscreen = true;
            }
        }

        if (Gdx.input.isKeyJustPressed(Keys.F10)) {
            player.toggleHud();
        }
    }

    private void cameraZoom() {
        if (scrollProc.scrollAmount > 0) {
            camera.zoom += 0.2f * camera.zoom;
        } else if (scrollProc.scrollAmount < 0) {
            camera.zoom -= 0.2f * camera.zoom;
        }

        if (camera.zoom < 0.05) {
            camera.zoom = 0.05f;
        }

        scrollProc.scrollAmount = 0;
    }

    private void cameraPan() {
        float cameraSpeed = 20;

        Vector3 cameraMovement = new Vector3();

        if (Gdx.input.isKeyPressed(Keys.W)) { cameraMovement.add(0, 1, 0); }
        if (Gdx.input.isKeyPressed(Keys.A)) { cameraMovement.add(-1, 0, 0); }
        if (Gdx.input.isKeyPressed(Keys.S)) { cameraMovement.add(0, -1, 0); }
        if (Gdx.input.isKeyPressed(Keys.D)) { cameraMovement.add(1, 0, 0); }

        cameraMovement.nor();
        cameraMovement.scl(Gdx.graphics.getDeltaTime() / (1f / 60f));
        cameraMovement.scl(cameraSpeed * camera.zoom);

        camera.translate(cameraMovement);
    }

    private void handleLeftClick() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector3 unprojectedPos = viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            Vector2 clickPos = new Vector2(unprojectedPos.x, unprojectedPos.y);

            boolean shiftIsPressed = Gdx.input.isKeyPressed(Keys.SHIFT_LEFT);
            if (!shiftIsPressed) {
                player.deselectAll();
            }

            for (Unit unit : world.getUnits()) {
                if (clickPos.x > unit.pos.x + unit.getOffsetX()
                    && clickPos.x < unit.pos.x + unit.getWidth() + unit.getOffsetX()
                    && clickPos.y > unit.pos.y + unit.getOffsetY()
                    && clickPos.y < unit.pos.y + unit.getHeight() + unit.getOffsetY()) {
                        player.selectUnit(unit, shiftIsPressed);
                        break;
                }
            }
        }
    }

    private void handleRightClick() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            Vector3 unprojectedPos = viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            Vector2 clickPos = new Vector2(unprojectedPos.x, unprojectedPos.y);

            boolean overWrite = true;
            if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
                overWrite = false;
            }

            for (Target t : player.getTargets()) {
                if (overWrite) {
                    t.getUnit().clearQueue();
                }

                t.getUnit().addTarget(clickPos);
            }
        }
    }
}