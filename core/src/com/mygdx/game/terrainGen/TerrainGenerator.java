package com.mygdx.game.terrainGen;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.TileMap;
import com.mygdx.game.terrainGen.algo.ArrayFiller;
import com.mygdx.game.terrainGen.algo.PerlinGenerator;
import com.mygdx.game.terrainGen.algo.WorleyGenerator;

public class TerrainGenerator {
    private Random rng;

    public TerrainGenerator() {
        rng = new Random();
    }

    public TileMap generateTileMap(int width, int height, Texture[] tileTextures, int tileTypes) {
        int[][] tiles = new int[width][height];

        int seed = rng.nextInt(100_000);
        PerlinGenerator perlinGen = new PerlinGenerator(seed);
        WorleyGenerator worleyGen = new WorleyGenerator(seed);

        int lower = 0;
        int upper = 2;
        worleyGen.setFeaturePointBounds(lower, upper);

        double scale1 = 1d / (rng.nextInt(1000) + 1);
        double scale2 = 1d / (rng.nextInt(100) + 1);
        double scale3 = 1d / (rng.nextInt(20) + 1);

        double[][] noiseArray1 = ArrayFiller.fill2DArray(width, height, scale1, worleyGen);
        double[][] noiseArray2 = ArrayFiller.fill2DArray(width, height, scale2, perlinGen);
        double[][] noiseArray3 = ArrayFiller.fill2DArray(width, height, scale3, perlinGen);
        double[][] noise2D = new double[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                noise2D[x][y] = (noiseArray1[x][y] + noiseArray2[x][y] + noiseArray3[x][y]) / 3;
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double noiseVal = noise2D[x][y] * tileTypes;
                
                if (noiseVal < 0.4 * tileTypes) {
                    tiles[x][y] = 0;
                } else if (noiseVal < 0.45 * tileTypes) {
                    tiles[x][y] = 1;
                } else if (noiseVal < 0.6 * tileTypes) {
                    tiles[x][y] = 2;
                } else {
                    tiles[x][y] = 3;
                }
            }
        }
        
        return new TileMap(width, height, tiles, tileTextures, tileTypes);
    }

    public TileMap generateObjects(TileMap map, Texture[] objectTextures, int objectTypes) {
        int[][] obj = new int[map.width][map.height];

        obj = generateTreesAndBushes(map, obj);
        //obj = generateRocks(map, obj);

        return new TileMap(map.width, map.height, obj, objectTextures, objectTypes);
    }

    private int[][] generateTreesAndBushes(TileMap map, int[][] obj) {
        PerlinGenerator treeGen = new PerlinGenerator(rng.nextInt(100_000));
        double scale = 1d / (rng.nextInt(100) + 1);
        double[][] noise2D = ArrayFiller.fill2DArray(map.width, map.height, scale, treeGen);

        double treeShift = 0.4;
        double bushShift = 0.5;

        for (int x = 0; x < map.width; x++) {
            for (int y = 0; y < map.height; y++) {
                if (map.tiles[x][y] == 2) {
                    double noiseVal = noise2D[x][y];
                    double treeChance = rng.nextDouble();

                    if (noiseVal > treeChance + treeShift) {
                        obj[x][y] = 1;
                    } else if (noiseVal < 0.6 && noiseVal > rng.nextDouble() + bushShift) {
                        obj[x][y] = 2;
                    }
                }
            }
        }

        return obj;
    }

    /*
    private int[][] generateRocks(TileMap map, int[][] obj) {
        PerlinGenerator rockGen = new PerlinGenerator(rng.nextInt(100_000));
        double scale = 1d / (rng.nextInt(50) + 1);
        double[][] noise2D = ArrayFiller.fill2DArray(map.width, map.height, scale, rockGen);

        for (int x = 0; x < map.width; x++) {
            for (int y = 0; y < map.height; y++) {
                int tileType = map.tiles[x][y];

                if (tileType == 2 || tileType == 3) {
                    double noiseVal = noise2D[x][y];
                    double rockChance = rng.nextDouble();

                    double shift;
                    if (tileType == 2) {
                        shift = 0.7;
                    } else {
                        shift = 0.5;
                    }

                    if (noiseVal > rockChance + shift) {
                        obj[x][y] = 2;
                    }
                }
            }
        }

        return obj;
    }
    */
}