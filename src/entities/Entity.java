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

    protected Rectangle hitBox;
    protected float x, y;
    protected boolean dead = false;

    public abstract void update(int delta);

    public Rectangle getHitBox() {
        return hitBox;
    }

    // Méthode init de la classe
    public abstract void init() throws SlickException;

    // Méthode qui permet de dessiner l'élément
    public abstract void render(Graphics g) throws SlickException;

    
    // Les méthodes suivantes sont des getters/setters
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isDead() {
        return dead;
    }

    public Rectangle getHitbox() {
        return this.hitBox;
    }
    
    // Méthode qui permet de load une animation à partir d'un spritesheet
    protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    
}
