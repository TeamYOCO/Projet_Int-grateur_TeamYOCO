/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maps;

import items.Equipment;
import items.Weapon;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Contient
 *
 * @author Tomas
 */
public class MiniMap {

    private TiledMap tiledMap;
    private ArrayList<TiledMap> listMap = new ArrayList();
    private ArrayList<Weapon> listEquipment = new ArrayList();

    public MiniMap() {
    }

    // Initialise la map dans la boucle init() du jeu
    public void init() throws SlickException {
        this.changeMap("res/maps/map_1-1.tmx");

    }

    public void renderBackground(Graphics g) throws SlickException {
        tiledMap.render(0, 0, 1);
        tiledMap.render(0, 0, 2);
        tiledMap.render(0, 0, 3);
        tiledMap.render(0, 0, 4);

        try {
            if (tiledMap == listMap.get(1)) {
                listEquipment.add(new Weapon(this));

                for (Equipment equipment : listEquipment) {
                    equipment.init();
                    equipment.render(g);
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }

    }

// Affiche le foreground de la map
    public void renderForeground() {
        tiledMap.render(0, 0, 5);
        tiledMap.render(0, 0, 6);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

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

    public void changeMap(String file) throws SlickException {
        this.tiledMap = new TiledMap(file);
        TiledMap tm = this.tiledMap;
    }
}
