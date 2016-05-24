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

    protected int inventoryX, inventoryY;
    public static final int MAX_STATS = 7;
    protected int stats[] = new int[MAX_STATS];
    protected int hpMax;
    protected int mpMax;
    protected int attack;
    protected int speed;
    protected int specialAttack;
    protected int defence;
    protected int specialDefence;
    protected String name, description;
    protected Animation animation;
    protected float x, y;
    protected MiniMap map;
    protected Rectangle hitBox;
    private boolean isAbove;
    private ArrayList<Particle> particle;
    private EquipmentType equipmentType;
    private int price;

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
    
    public void update(int delta) {

    }

    // Méthode init de la classe
    public void init() throws SlickException {

    }

    // Méthode qui permet de dessiner l'élément
    public void render(Graphics g) throws SlickException {

    }

    protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    public boolean isCollision(Rectangle rectObjet) {
        boolean collision = false;
        if (hitBox.getBounds().intersects(rectObjet)) {
            collision = true;
        }
        return collision;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public int[] getStats() {
        return stats;
    }

    public int getInventoryX() {
        return inventoryX;
    }

    public int getInventoryY() {
        return inventoryY;
    }


    public boolean getIsAbove() {
        return isAbove;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
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
    
    public int getPrice(){
        return price;
    }
    
    public EquipmentType getType(){
        return equipmentType;
    }

    public void setInventoryX(int x) {
        this.inventoryX = x;
    }

    public void setInventoryY(int y) {
        this.inventoryY = y;
    }

    public void setIsAbove(boolean isAbove) {
        this.isAbove = isAbove;
    }
}
