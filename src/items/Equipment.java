/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import entities.Particle;
import java.awt.Frame;
import java.awt.Rectangle;
import java.util.ArrayList;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1455367
 */
public class Equipment {

    protected int inventoryX, inventoryY;
    protected int stats[] = new int[6];
    protected int hpMax;
    protected int mpMax;
    protected int attack;
    protected int speed;
    protected int specialAttack;
    protected int defence;
    protected int specialDefence;
    protected String name,description;
    protected Animation animation;
    protected float x, y;
    protected MiniMap map;
    protected Rectangle hitBox;
    protected Image itemIcon;
    private boolean isAbove;
    private ArrayList<Particle> particle;

    public Equipment(int inventoryX, int inventoryY, Image itemIcon, String name, String description, int[] stats) {
        this.inventoryX = inventoryX;
        this.inventoryY = inventoryY;
        this.itemIcon = itemIcon;
        this.isAbove = false;
        this.name = name;
        this.description = description;
        this.hpMax = stats[0];
        this.attack = stats[1];
        this.defence = stats[2];
        this.specialAttack = stats[3];
        this.specialDefence = stats[4];
        this.speed = stats[5];
        this.stats[0] = stats[0];
        this.stats[1] = stats[1];
        this.stats[2] = stats[2];
        this.stats[3] = stats[3];
        this.stats[4] = stats[4];
        this.stats[5] = stats[5];
    }
    
    public Equipment(int inventoryX, int inventoryY, Image itemIcon, String name, String description, int[] stats) {
        this.inventoryX = inventoryX;
        this.inventoryY = inventoryY;
        this.itemIcon = itemIcon;
        this.isAbove = false;
        this.name = name;
        this.description = description;
        this.hpMax = stats[0];
        this.attack = stats[1];
        this.defence = stats[2];
        this.specialAttack = stats[3];
        this.specialDefence = stats[4];
        this.speed = stats[5];
        this.stats[0] = stats[0];
        this.stats[1] = stats[1];
        this.stats[2] = stats[2];
        this.stats[3] = stats[3];
        this.stats[4] = stats[4];
        this.stats[5] = stats[5];
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
    
    public int[] getStats(){
        return stats;
    }

    public int getInventoryX() {
        return inventoryX;
    }

    public int getInventoryY() {
        return inventoryY;
    }

    public Image getIcon() {
        return itemIcon;
    }
    
    public boolean getIsAbove(){
        return isAbove;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getHpMax() {
        return hpMax;
    }

    public int getAttack() {
        return attack;
    }
    
    public int getDefence() {
        return defence;
    }
    
    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefence() {
        return specialDefence;
    }

    public int getSpeed() {
        return speed;
    }

    public void setInventoryX(int x) {
        this.inventoryX = x;
    }

    public void setInventoryY(int y) {
        this.inventoryY = y;
    }
    
    public void setIsAbove(boolean isAbove){
        this.isAbove = isAbove;
    }
}
