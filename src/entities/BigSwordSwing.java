/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResMng;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.StatsMng;

/**
 *
 * @author 1455367
 */
public class BigSwordSwing extends Particle{
    private int damage;

    public BigSwordSwing(float x, float y, int direction, int lifeSpam) throws SlickException {
        SpriteSheet anim = ResMng.getInstance().getSpriteSheet("longsword_male");
        this.x = x;
        this.y = y;
        this.xOff = 0;
        this.yOff = 0;
        switch(direction){
            case 0: xOff = 48; yOff = 27; break;
            case 1: xOff = 11; yOff = 50; break;
            case 2: xOff = 48; yOff = 82; break;
            case 3: xOff = 85; yOff = 45; break;
        }
        this.hitBox = new Box(x+xOff, y+yOff, 0, 0);
        this.damage = StatsMng.getInstance().getStats()[1];
        this.animation = loadAnimation(anim, 1, 6, direction);
        this.lifeSpam = lifeSpam;
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animation, x, y);
        hitBox.render(g);
    }

    @Override
    public void update(int delta) {
        super.update(delta); 
        if (lifeSpam <= 300){
            this.hitBox = new Box(x+xOff, y+yOff, 32, 32);
        }
    }
}
