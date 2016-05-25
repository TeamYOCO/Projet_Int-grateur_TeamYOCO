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

    /**
     * il est là au cas ou
     */
    public MiniMap() {
    }
    
    /**
     * Crée la mini carte
     * @param path le chemin pour la nouvelle carte
     */
    public MiniMap(String path) {
        MiniMap.fileName = path;
    }


    /**
     * Initialise la carte
     * @throws SlickException
     */
        public void init() throws SlickException {
        fileName = "res/maps/Maison_1.tmx";
        MiniMap.tiledMap = new TiledMap(fileName);
    }

    /**
     * Dessine tout ce qui est a l'arrière du personnage
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    public void renderBackground(Graphics g) throws SlickException {
        tiledMap.render(0, 0, 1);
        tiledMap.render(0, 0, 2);
        tiledMap.render(0, 0, 3);
        tiledMap.render(0, 0, 4);
    }


    /**
     * Dessine tout ce qui est en avant du personnage
     */
        public void renderForeground() {
        tiledMap.render(0, 0, 5);
        tiledMap.render(0, 0, 6);
    }

    /**
     * mise à jour de la map
     * @param gc la fenêtre
     * @param sbg le jeu
     * @param delta le temps d'une ittération
     * @throws SlickException
     */
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    /**
     * reguarde si il y a des collisions avec des murs
     * @param x position x à tester
     * @param y position y à tester
     * @return si il y a une collision
     */
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

    /**
     * retourne la carte quadrillée
     * @return la carte quadrillée
     */
    public TiledMap getTiledMap() {
        return tiledMap;
    }
    
    /**
     * retourne le nom de la carte
     * @return le nom de la carte
     */ 
    public static String getFileName(){
        return fileName;
    }

    /**
     * retourne le nombre d'objet dans la carte
     * @return le nombre d'objet dans la carte
     */
    public int getObjectCount() {
        return MiniMap.tiledMap.getObjectCount(0);
    }

    /**
     * retourne le type de la case choisit
     * @param objectID la case choisit
     * @return le type de la case
     */
    public String getObjectType(int objectID) {
        return MiniMap.tiledMap.getObjectType(0, objectID);
    }

    /**
     * Retourne la position en x de la case choisit
     * @param objectID la case choisit
     * @return la position en x
     */
    public float getObjectX(int objectID) {
        return MiniMap.tiledMap.getObjectX(0, objectID);
    }

     /**
     * Retourne la position en y de la case choisit
     * @param objectID la case choisit
     * @return la position en y
     */
    public float getObjectY(int objectID) {
        return MiniMap.tiledMap.getObjectY(0, objectID);
    }

    /**
     * retourne la largeur de la case
     * @param objectID la case choisit
     * @return la largeur de la case
     */
    public float getObjectWidth(int objectID) {
        return MiniMap.tiledMap.getObjectWidth(0, objectID);
    }

    /**
     * retourne la hauteur de la case
     * @param objectID la case choisit
     * @return la hauteur de la case
     */
    public float getObjectHeight(int objectID) {
        return MiniMap.tiledMap.getObjectHeight(0, objectID);
    }

    /**
     * retourne la valeur de la propriété de la case choisit
     * @param objectID la case choisit
     * @param propertyName le nom de la propriété
     * @param def valeur de défaut
     * @return la valeur de la propriété de la case choisit
     */
    public String getObjectProperty(int objectID, String propertyName, String def) {
        return MiniMap.tiledMap.getObjectProperty(0, objectID, propertyName, def);
    }
    
    /**
     * retourne la valeur de la propriété de la carte
     * @param name le nom de la paropriété
     * @return la valeur de celle-ci
     */
    public String getMapProperty(String name){
        return tiledMap.getMapProperty(name, "0");
    }

    /**
     * change la carte
     * @param file le chemin de la nouvelle carte
     * @throws SlickException
     */
    public static void changeMap(String file) throws SlickException {
        fileName = file;
        tiledMap = new TiledMap(file);  
        }
    
    }

