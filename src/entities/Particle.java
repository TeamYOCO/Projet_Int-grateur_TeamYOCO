/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Seb
 */
public class Particle extends Entity {

    private Animation animation = new Animation();
    private int lifeSpam, speedX, speedY, direction;

    public Particle(Animation animation, int lifeSpam, int speedX, int speedY, int direction, float x, float y) {
        this.animation = animation;
        this.lifeSpam = lifeSpam;
        this.speedX = speedX;
        this.speedY = speedY;
        this.direction = direction;
        this.x = x;
        this.y = y;
    }


    @Override
    public void update(int delta) {
        lifeSpam -= delta;
        if (lifeSpam <= 0) {
            dead = true;
        }
    }

    @Override
    public void init() throws SlickException {
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animation, x, y);
    }

}
