/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import entities.Particle;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1455367
 */
public class Equipment implements Serializable{

    protected int inventoryX,

    inventoryY;
    public static final int MAX_STATS = 7;
    protected int stats[] = new int[MAX_STATS];
    protected int hpMax;
    protected int mpMax;
    protected int attack;
    protected int speed;
    protected int specialAttack;
    protected int defence;
    protected int specialDefence;
    protected String name,
    description;
    protected Animation animation;
    protected float x,y;
    protected MiniMap map;
    protected Rectangle hitBox;
    private boolean isAbove;
    private ArrayList<Particle> particle;
    private EquipmentType equipmentType;
    private int price;
    private boolean shopSelected = false;

    /**
     * Crée les equipements
     * @param inventoryX La position x de l'équipement dans l'inventaire
     * @param inventoryY La position x de l'équipement dans l'inventaire
     * @param name le nom de l'équipement
     * @param description la description de l'équipement
     * @param stats les statistiques de l'équipement
     * @param equipmentType le type d'équipement
     * @param price Le prix de l'équipement
     */
    public Equipment(int inventoryX, int inventoryY, String name, String description, int[] stats, EquipmentType equipmentType, int price) {
        this.inventoryX = inventoryX;
        this.inventoryY = inventoryY;
        this.isAbove = false;
        this.name = name;
        this.description = description;
        this.stats[0] = stats[0];
        this.stats[1] = stats[1];
        this.stats[2] = stats[2];
        this.stats[3] = stats[3];
        this.stats[4] = stats[4];
        this.stats[5] = stats[5];
        this.stats[6] = stats[6];
        this.equipmentType = equipmentType;
        this.price = price;
    }
    
    /**
     * Mise à jour de l'équipement
     * @param delta le temps d'une ittération
     */
    public void update(int delta) {

    }

    /**
     * initialise la classe
     * @throws SlickException
     */
        public void init() throws SlickException {

    }

    /**
     * Permet de desssiner l'équipement
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
        public void render(Graphics g) throws SlickException {

    }

    /**
     * Charger l'image de l'équipement
     * @param spriteSheet L'image de l'équipement
     * @param startX Le début en x dans l'image
     * @param endX La fin en x dans l'image
     * @param y La position en y dans l'image
     * @return L'image de l'équipement
     */
    protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    /**
     * Si elle est en collision avec un rectangle
     * @param rectObjet un rectangle qui représente un autre objet
     * @return Si il y a eu collision
     */
    public boolean isCollision(Rectangle rectObjet) {
        boolean collision = false;
        if (hitBox.getBounds().intersects(rectObjet)) {
            collision = true;
        }
        return collision;
    }

    /**
     * Retourne la boîte de collision
     * @return la boîte de collision
     */
    public Rectangle getHitBox() {
        return hitBox;
    }

    /**
     * retourne les statistiques
     * @return les statistiques
     */
    public int[] getStats() {
        return stats;
    }

    /**
     * retourne la valeur de x dans l'inventaire
     * @return la valeur de x dans l'inventaire
     */
    public int getInventoryX() {
        return inventoryX;
    }

    /**
     *retourne la valeur de y dans l'inventaire
     * @return la valeur de y dans l'inventaire
     */
    public int getInventoryY() {
        return inventoryY;
    }

    /**
     * retourne si l'équipement est dessus
     * @return si l'équipement est dessus
     */
    public boolean getIsAbove() {
        return isAbove;
    }

    /**
     * retourne le nom de l'équipement
     * @return le nom de l'équipement
     */
    public String getName() {
        return name;
    }

    /**
     * retourne la description de l'équipement
     * @return la description de l'équipement
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Si l'équipement est selectionner dans le magasin
     * @return Si l'équipement est selectionner dans le magasin
     */
    public boolean getShopSelected(){
        return shopSelected;
    }
    
    /**
     * retourne le nom de la statistique
     * @param stat le numéro de la statistique
     * @return le nom de la statistique
     */
    public String getStatName(int stat){
        switch(stat){
            case 0 : return "Vie-";
            case 1 : return "Att-";
            case 2 : return "Def-";
            case 3 : return "SpA-";
            case 4 : return "SpD-";
            case 5 : return "Vit-";
            case 6 : return "Eng-";
            default : return "wut";
        }
    }
    
    /**
     * retourne le prix de l'équipement
     * @return le prix de l'équipement
     */
    public int getPrice(){
        return price;
    }
    
    /**
     * retourne le type de l'équipement
     * @return le type de l'équipement
     */
    public EquipmentType getType(){
        return equipmentType;
    }

    /**
     * change la position en x dans l'inventaire
     * @param x la nouvelle position en x
     */
    public void setInventoryX(int x) {
        this.inventoryX = x;
    }

    /**
     * change la position en y dans l'inventaire
     * @param y la nouvelle position en y
     */
    public void setInventoryY(int y) {
        this.inventoryY = y;
    }

    /**
     * change si l'équipement est au dessus
     * @param isAbove le nouveau booléen
     */
    public void setIsAbove(boolean isAbove) {
        this.isAbove = isAbove;
    }
    
    /**
     * change si l'équipement est selectionné dans le magasin
     * @param shopSelected le nouveau booléen
     */
    public void setShopSelected(boolean shopSelected){
        this.shopSelected = shopSelected;
    }
}
