/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author The Ninetail
 */
public class Tornade extends Particle implements FriendlyEntity{
    
    private int damagePhysical;
    private int damageSpecial;
    private Animation transitionAnimation[];

    public Tornade(int x, int y, int direction) throws SlickException{
        SpriteSheet sheet = ResManager.getInstance().getSpriteSheet("tornado");
        this.animation = animation;
        transitionAnimation = new Animation[2];
        this.x = x;
        this.y = y;
        this.xOff = -64;
        this.yOff = -64;
        this.damagePhysical = CharacterStatsManager.getInstance().getStats()[1];
        this.damageSpecial = CharacterStatsManager.getInstance().getStats()[3];
        this.direction = direction;
        this.hitBox = new Box(x+xOff, y+yOff, 128, 128);
        animation = addAnimation(sheet, 0, 4, 1,animation);
        animation = addAnimation(sheet, 0, 4, 2,animation);
        transitionAnimation[0] = loadAnimation(sheet, 0, 4, 0);
        transitionAnimation[1] = loadAnimation(sheet, 0, 4, 3);
    }

    @Override
    public void update(int delta) {
        super.update(delta); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animation, x-64, y-64);
    }
       

    @Override
    public int getDamagePhysical() {
        return 0;
    }

    @Override
    public int getDamageSpecial() {
        return damageSpecial;
    }

    @Override
    public int getDirection() {
        return direction;
    }
    
}
