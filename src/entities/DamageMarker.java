/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author The Ninetail
 */
public class DamageMarker extends Particle{
    
    private int display;

    public DamageMarker(float x, float y, int display) {
        this.x = x;
        this.y = y;
        this.hitBox = new Box(x, x, 0, 0);
        this.lifeSpam = 500;
        this.display = display;
        this.speedY = -0.1f;
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawString(""+display, x, y);
    }
    
    
    
}
