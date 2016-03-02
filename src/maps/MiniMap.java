/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maps;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author 1455367
 */
public class MiniMap{

    private TiledMap tiledMap;
    private Music backgroundSong;

    public MiniMap() {
    }



    // Initialise la map dans la boucle init() du jeu
    public void init() throws SlickException {
        this.tiledMap = new TiledMap("res/maps/map_1-1.tmx");
    }

    // Affiche le background de la map
    public void renderBackground() {
        tiledMap.render(0, 0);
    }

    // Affiche le foreground de la map
    public void renderForeground() {
    }

    // Détecte si le joueur entre en collision avec les éléments collision de la map
    public boolean isCollision(float x, float y) {
        int tileW = this.tiledMap.getTileWidth();
        int tileH = this.tiledMap.getTileHeight();
        int logicLayer = this.tiledMap.getLayerIndex("collision");
        Image tile = this.tiledMap.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
        boolean collision = tile != null;
        if (collision) {
            Color color = tile.getColor((int) x % tileW, (int) y % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;
        
    }

    // Les méthodes suivantes sont des getters
    public TiledMap getTiledMap() {
        return tiledMap;
    }
    
    public int getObjectCount() {
        return this.tiledMap.getObjectCount(0);
    }

    public String getObjectType(int objectID) {
        return this.tiledMap.getObjectType(0, objectID);
    }

    public float getObjectX(int objectID) {
        return this.tiledMap.getObjectX(0, objectID);
    }

    public float getObjectY(int objectID) {
        return this.tiledMap.getObjectY(0, objectID);
    }

    public float getObjectWidth(int objectID) {
        return this.tiledMap.getObjectWidth(0, objectID);
    }

    public float getObjectHeight(int objectID) {
        return this.tiledMap.getObjectHeight(0, objectID);
    }

    public String getObjectProperty(int objectID, String propertyName, String def) {
        return this.tiledMap.getObjectProperty(0, objectID, propertyName, def);
    }

    // Change la map
    public void changeMap(String file) throws SlickException {
        this.tiledMap = new TiledMap(file);
    }
}
