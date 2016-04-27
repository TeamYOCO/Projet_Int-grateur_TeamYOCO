/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author 1455367
 */
public class SwordSwing extends Particle {
    
    private int damage;

    public SwordSwing(float x, float y, int direction, int lifeSpam) throws SlickException {
        SpriteSheet anim = ResManager.getInstance().getSpriteSheet("sword_particles");
        this.x = x;
        this.y = y;
        this.damage = CharacterStatsManager.getInstance().getStats()[1];
        this.animation = loadAnimation(anim, 1, 6, direction);
        this.lifeSpam = lifeSpam;
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animation, x, y);
    }
    
    

}
