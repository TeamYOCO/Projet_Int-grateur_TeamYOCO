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
 * Méthode qui représent tous les autres éléments graphiques à dessiner dans le overworld
 * @author Seb
 */
public class Particle extends Entity {

    protected Animation animation = new Animation();
    protected int lifeSpam, speedX, speedY, direction;

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

    public Particle() {
    }
    


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

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animation, x, y);
    }

}
