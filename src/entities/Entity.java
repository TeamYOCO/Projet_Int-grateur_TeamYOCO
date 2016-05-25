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
 *
 * @author Seb
 */
public abstract class Entity {

    protected Box hitBox;
    protected float x,

    y,

    xOff, yOff;
    private static float saveX,saveY;

    protected boolean dead = false;

    /**
     * Permet la mise à jour des entitées
     * @param delta le temps d'une ittérations
     */
    public abstract void update(int delta);

    /**
     * Retourne la boîte de collision de l'entitée
     * @return la boîte de collision de l'entitée
     */
    public Box getHitBox() {
        return hitBox;
    }


    /**
     * Méthode qui permet de dessiner l'élément
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    public abstract void render(Graphics g) throws SlickException;

    // Les méthodes suivantes sont des getters/setters
    /**
     * Retourne la position en x de l'entitée
     * @return la position en x de l'entitée
     */
    public float getX() {
        return x;
    }

    /**
     *Retourne la position en y de l'entitée
     * @return la position en y de l'entitée
     */
    public float getY() {
        return y;
    }

    /**
     * Retourne la position en x de l'entitée pour la sauveguarde
     * @return la position en x de l'entitée
     */
    public static float getSaveX() {
        return saveX;
    }

    /**
     *Retourne la position en y de l'entitée pour la sauveguarde
     * @return la position en y de l'entitée
     */
    public static float getSaveY() {
        return saveY;
    }

    /**
     * Change la position de l'entitée à x
     * @param x la nouvelle position
     */
    public void setX(float x) {
        this.x = x;
        saveX = x;
    }

    /**
     * Change la position de l'entitée à y
     * @param y la nouvelle position
     */
    public void setY(float y) {
        this.y = y;
        saveY = y;
    }

    /**
     *Change la position de l'entitée à x pour la sauveguarde
     * @param x la nouvelle position
     */
    public static void setSaveX(float x) {
        saveX = x;
    }

    /**
     * Change la position de l'entitée à y pour la sauveguarde
     * @param y la nouvelle position
     */
    public static void setSaveY(float y) {
        saveY = y;
    }

    /**
     * retourne si l"entitée est morte
     * @return un booléen si l'entitée est morte
     */
    public boolean isDead() {
        return dead;
    }
    

    // Méthode qui permet de load une animation à partir d'un spritesheet
    /**
     * Méthode qui permet de load une animation à partir d'un spritesheet
     * @param spriteSheet la spritesheet de l'entitée
     * @param startX La position x où commence l'animation dans la spritesheet
     * @param endX La position x où finit l'animation dans la spritesheet
     * @param y La position y dans la spritesheet
     * @return l'animation de l'entitée
     */
    protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    protected Animation addAnimation(SpriteSheet spriteSheet, int startX, int endX, int y, Animation animation) {
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;

    }

}
