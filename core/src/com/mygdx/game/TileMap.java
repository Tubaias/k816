package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint3;

public class TileMap {
    public int width;
    public int height;
    public int[][] tiles;
    public ArrayList<GridPoint3> sortedTiles;
    
    private Texture[] textures;
    private int tileTypes;

    public TileMap(int w, int h, int[][] tiles, Texture[] tex, int types) {
        this.width = w;
        this.height = h;
        this.tiles = tiles;
        this.textures = tex;
        this.tileTypes = types;

        sortTiles(width, height, tiles);
    }

    private void sortTiles(int width, int height, int[][] tiles) {
        sortedTiles = new ArrayList<>();

        for (int tileType = 0; tileType < tileTypes; tileType++) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (tiles[x][y] == tileType) {
                        sortedTiles.add(new GridPoint3(x, y, tileType));
                    }
                }
            }
        }
    }

    public Texture getTexture(int value) {
        if (value < tileTypes) {
            return textures[value];
        } else {
            return textures[0];
        }
    }
}