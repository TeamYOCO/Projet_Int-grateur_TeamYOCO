/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Seb
 */
public class particles extends Entity{

    private  Animation  animation = new Animation();
    private int lifeSpam;
    private boolean dead;

    public particles(Animation animation) {
    }
    
    @Override
    public void update(int delta) {
        
    }

    @Override
    public void init() throws SlickException {
    }

    @Override
    public void render(Graphics g) throws SlickException {
        
    }
    
}
