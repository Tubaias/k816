package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.unit.Lammas;
import com.mygdx.game.entity.unit.Unit;
import com.mygdx.game.terrainGen.TerrainGenerator;

public class World {
    public int width;
    public int height;

    private ArrayList<Unit> units;
    private TerrainGenerator gen;
    private TileMap terrainMap;
    private TileMap objectMap;
    private Texture[] tileTextures;
    private Texture[] objectTextures;
    private int tileTypes;
    private int objectTypes;
    private Texture sheepWorkerTex;
    private Texture sheepWorkerPortrait;
    
    public World() {
        this.width = 512;
        this.height = 512;
        this.units = new ArrayList<>();

        sheepWorkerTex = new Texture("sheepWorker.png");
        sheepWorkerPortrait = new Texture("sheepWorkerIcon.png");

        tileTypes = 4;
        tileTextures = new Texture[tileTypes];
        tileTextures[0] = new Texture("waterTile.png");
        tileTextures[1] = new Texture("sandTile.png");
        tileTextures[2] = new Texture("grassTile.png");
        tileTextures[3] = new Texture("stoneTile.png");

        objectTypes = 2;
        objectTextures = new Texture[objectTypes];
        objectTextures[0] = new Texture("kuusi.png");
        objectTextures[1] = new Texture("marjaPuskaSlim.png");

        gen = new TerrainGenerator();
        terrainMap = gen.generateTileMap(width, height, tileTextures, tileTypes);
        objectMap = gen.generateObjects(terrainMap, objectTextures, objectTypes);
    }

    public void tick() {
        for (Unit unit : units) {
            unit.move();
        }
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void createLammas(float x, float y, int number) {
        units.add(new Lammas(sheepWorkerTex, sheepWorkerPortrait, x, y, number));
    }

    public TileMap getTerrainMap() {
        return terrainMap;
    }

    public TileMap getObjectMap() {
        return objectMap;
    }

    public void generate() {
        terrainMap = gen.generateTileMap(width, height, tileTextures, tileTypes);
        objectMap = gen.generateObjects(terrainMap, objectTextures, objectTypes);
    }
}