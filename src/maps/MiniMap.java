/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maps;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Tomas
 */
public class MiniMap {
    
    private static String fileName;
    private static TiledMap tiledMap;

    public MiniMap() {
    }
    
    public MiniMap(String path) {
        MiniMap.fileName = path;
    }

    // Initialise la map dans la boucle init() du jeu
    public void init() throws SlickException {
        fileName = "res/maps/map_1-1.tmx";
        MiniMap.tiledMap = new TiledMap(fileName);
    }

    
    public void renderBackground(Graphics g) throws SlickException {
        tiledMap.render(0, 0, 1);
        tiledMap.render(0, 0, 2);
        tiledMap.render(0, 0, 3);
        tiledMap.render(0, 0, 4);
    }

    // Affiche le foreground de la map
    public void renderForeground() {
        tiledMap.render(0, 0, 5);
        tiledMap.render(0, 0, 6);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    public boolean isCollision(float x, float y) {
        int tileW = MiniMap.tiledMap.getTileWidth();
        int tileH = MiniMap.tiledMap.getTileHeight();
        int logicLayer = MiniMap.tiledMap.getLayerIndex("collision");
        Image tile = MiniMap.tiledMap.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
        boolean collision = tile != null;
        if (collision) {
            Color color = tile.getColor((int) x % tileW, (int) y % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;

    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }
    
    public static String getFileName(){
        return fileName;
    }

    public int getObjectCount() {
        return MiniMap.tiledMap.getObjectCount(0);
    }

    public String getObjectType(int objectID) {
        return MiniMap.tiledMap.getObjectType(0, objectID);
    }

    public float getObjectX(int objectID) {
        return MiniMap.tiledMap.getObjectX(0, objectID);
    }

    public float getObjectY(int objectID) {
        return MiniMap.tiledMap.getObjectY(0, objectID);
    }

    public float getObjectWidth(int objectID) {
        return MiniMap.tiledMap.getObjectWidth(0, objectID);
    }

    public float getObjectHeight(int objectID) {
        return MiniMap.tiledMap.getObjectHeight(0, objectID);
    }

    public String getObjectProperty(int objectID, String propertyName, String def) {
        return MiniMap.tiledMap.getObjectProperty(0, objectID, propertyName, def);
    }
    
    public String getMapProperty(String name){
        return tiledMap.getMapProperty(name, "0");
    }

    public static void changeMap(String file) throws SlickException {
        fileName = file;
        tiledMap = new TiledMap(file);  
        }
    
    }

