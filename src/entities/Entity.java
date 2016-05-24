/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Rectangle;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Classe mère de tous les éléments graphiques du overworld
 * @author Seb
 */
public abstract class Entity {

    /**
     *
     */
    protected Box hitBox;
    protected float x,

    /**
     *
     */
    y,

    /**
     *
     */
    xOff, yOff;
    private static float saveX,saveY;

    /**
     *
     */
    protected boolean dead = false;

    /**
     *
     * @param delta
     */
    public abstract void update(int delta);

    /**
     *
     * @return
     */
    public Box getHitBox() {
        return hitBox;
    }


    // Méthode qui permet de dessiner l'élément

    /**
     *
     * @param g
     * @throws SlickException
     */
        public abstract void render(Graphics g) throws SlickException;

    
    // Les méthodes suivantes sont des getters/setters

    /**
     *
     * @return
     */
        public float getX() {
        return x;
    }
    
    /**
     *
     * @return
     */
    public float getY() {
        return y;
    }

    /**
     *
     * @return
     */
    public static float getSaveX(){
        return saveX;
    }
    
    /**
     *
     * @return
     */
    public static float getSaveY(){
        return saveY;
    }
    
    /**
     *
     * @param x
     */
    public void setX(float x) {
        this.x = x;
        saveX = x;
    }

    /**
     *
     * @param y
     */
    public void setY(float y) {
        this.y = y;
        saveY = y;
    }
    
    /**
     *
     * @param x
     */
    public static void setSaveX(float x){
        saveX = x;
    }
    
    /**
     *
     * @param y
     */
    public static void setSaveY(float y){
        saveY = y;
    }

    /**
     *
     * @return
     */
    public boolean isDead() {
        return dead;
    }
    
    // Méthode qui permet de load une animation à partir d'un spritesheet

    /**
     *
     * @param spriteSheet
     * @param startX
     * @param endX
     * @param y
     * @return
     */
        protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    
}
