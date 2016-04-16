/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.awt.Rectangle;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1455367
 */
public class Equipment {

    protected int inventoryX, inventoryY;
    protected int hpMax;
    protected int mpMax;
    protected int attack;
    protected int speed;
    protected int specialAttack;
    protected int defence;
    protected int specialDefence;
    protected String name;
    protected Animation animation;
    protected float x, y;
    protected MiniMap map;
    protected Rectangle hitBox;
    protected Animation itemIcon;
    private boolean isAbove;

    public Equipment(int inventoryX, int inventoryY, Animation itemIcon,String name) {
        this.inventoryX = inventoryX;
        this.inventoryY = inventoryY;
        this.itemIcon = itemIcon;
        this.isAbove = false;
        this.name = name;
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

    public int getInventoryX() {
        return inventoryX;
    }

    public int getInventoryY() {
        return inventoryY;
    }

    public Animation getIcon() {
        return itemIcon;
    }
    
    public boolean getIsAbove(){
        return isAbove;
    }
    
    public String getName(){
        return name;
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
