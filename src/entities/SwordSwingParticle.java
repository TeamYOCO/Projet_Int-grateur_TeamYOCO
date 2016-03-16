/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Rectangle;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Seb
 */
public class SwordSwingParticle extends Particle{
    
    private Image image;

    public SwordSwingParticle(Animation animation, int lifeSpam, int speedX, int speedY, int direction, float x, float y, Rectangle hitBox) {
        super(animation, lifeSpam, speedX, speedY, direction, x, y, hitBox);
    }

    public SwordSwingParticle(int direction, float x, float y, int lifeSpam) throws SlickException {
        SpriteSheet itemSheet = new SpriteSheet("res/sprites/items1_0.png", 32, 32);
        image = itemSheet.getSprite(0, 0).getScaledCopy(1.2f);
        image.rotate(90 + direction*-90);
        this.y = y;
        this.x = x;
        this.lifeSpam = lifeSpam;
    }
    
    @Override
    public void update(int delta) {
        lifeSpam -= delta;
        if (lifeSpam <= 0) {
            dead = true;
        }
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawImage(image, x, y);
    }
    
}
