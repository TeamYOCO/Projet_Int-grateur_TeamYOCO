/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResMng;
import maps.MiniMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.StatsMng;

/**
 *
 * @author The Ninetail
 */
public class Arrow extends Particle implements FriendlyEntity{
    
    private int damagePhysical;
    private int damageSpecial;
    private Image img;
    private MiniMap map;

    public Arrow(float x, float y, int direction, int lifespam, MiniMap map) throws SlickException{
        SpriteSheet anim = ResMng.getInstance().getSpriteSheet("arrow");
        this.x = x;
        this.y = y;
        this.xOff = 0;
        this.yOff = 0;
        this.direction = direction;
        animation = loadAnimation(anim, 0, 4, 0);
        img = animation.getImage(direction);
        switch(direction){
            case 0: speedY = -0.5f; xOff = -2; this.hitBox = new Box(x, y, 4, 30); break;
            case 1: speedX = -0.5f; yOff = -2; this.hitBox = new Box(x, y, 30, 4); break;
            case 2: speedY = 0.5f; xOff = -2; yOff = -32; this.hitBox = new Box(x, y, 4, 30); break;
            case 3: speedX = 0.5f; yOff = -2; xOff = -32; this.hitBox = new Box(x, y, 30, 4); break;
        }
        this.damagePhysical = StatsMng.getInstance().getStats()[1];
        this.damageSpecial = StatsMng.getInstance().getStats()[3];
        this.lifeSpam = lifespam;
        this.map = map;
    }

    @Override
    public void render(Graphics g) throws SlickException {
        switch(direction){
            case 0: g.drawImage(img, x - 14, y,Color.white); break;
            case 1: g.drawImage(img, x, y - 16,Color.white); break;
            case 2: g.drawImage(img, x - 16, y - 32,Color.white); break;
            case 3: g.drawImage(img, x - 32, y - 16,Color.white); break;
        }
    }

    @Override
    public void update(int delta) {
        super.update(delta); //To change body of generated methods, choose Tools | Templates.
        hitBox.setPos(x+xOff, y+yOff);
    }
    
    @Override
    public int getDamage() {
        dead = true;
        return damagePhysical;
    }

    @Override
    public int getDirection() {
        dead = true;
        return direction;
    }
    
}
