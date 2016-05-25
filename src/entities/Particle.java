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

/**
 * 
 * @author Seb
 */
public class Particle extends Entity {

    protected Animation animation = new Animation();
    protected int lifeSpam,
    direction;
    protected float speedX,
    speedY;

    /**
     * Crée une particule
     * @param animation L'animation de la particule
     * @param lifeSpam La durée de vie de la particule
     * @param speedX La vitesse en x de la particule
     * @param speedY La vitesse en y de la particule
     * @param direction La direction de la particule
     * @param x la position de départ en x de la particule
     * @param y La position de départ en y de la particule
     * @param hitBox La boîte de collision de la particule
     */
    public Particle(Animation animation, int lifeSpam, int speedX, int speedY, int direction, float x, float y, Box hitBox) {
        this.animation = animation;
        this.lifeSpam = lifeSpam;
        this.speedX = speedX;
        this.speedY = speedY;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.hitBox = hitBox;
    }

    /**
     * crée une particule vide au cas ou
     */
    public Particle() {
    }
    
    /**
     * permet à la particule de bouger
     * @param delta Le temps d'une ittérations
     */
    @Override
    public void update(int delta) {
        lifeSpam -= delta;
        if (lifeSpam <= 0) {
            dead = true;
        }
        this.x = this.x + speedX * delta;
        this.y = this.y + speedY * delta;
        hitBox.setPos(x+xOff, y+yOff);
    }

    /**
     * Dessine la particule pour la première fois
     * @param g  Le graphique qui permet de dessiner
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animation, x, y);
    }

}
