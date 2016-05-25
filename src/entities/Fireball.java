/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author Tomas
 */
public class Fireball extends Particle implements FriendlyEntity{
    
    private int damagePhysical;
    private int damageSpecial;
    private MiniMap map;
    private ArrayList<Entity> list;

    /**
     *
     * @param x
     * @param y
     * @param direction
     * @param lifespam
     * @param map
     * @throws SlickException
     */
    public Fireball(float x, float y, int direction, int lifespam, MiniMap map, ArrayList<Entity> list) throws SlickException{
        SpriteSheet anim1 = ResManager.getInstance().getSpriteSheet("fireball");
        animation = new Animation();
        this.list = list;
        this.x = x;
        this.y = y;
        this.xOff = 0;
        this.yOff = 0;
        this.direction = direction;
        switch (direction){
            case 0: speedY = -0.3f; break;
            case 1: speedX = -0.3f; break;
            case 2: speedY = 0.3f; break;
            case 3: speedX = 0.3f; break;
        }
        xOff = 16;
        yOff = 16;
        this.hitBox = new Box(x+xOff, y+yOff, 32, 32);
        animation = loadAnimation(anim1, 0, 8, direction);
        this.damagePhysical = CharacterStatsManager.getInstance().getStats()[1];
        this.damageSpecial = CharacterStatsManager.getInstance().getStats()[3];
        this.lifeSpam = lifespam;
        this.map = map;
    }

    /**
     *
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        
        g.drawAnimation(animation, x, y);
        this.hitBox.render(g);
    }

    /**
     *
     * @param delta
     */
    @Override
    public void update(int delta) {
        super.update(delta); //To change body of generated methods, choose Tools | Templates.
        hitBox.setPos(x+xOff, y+yOff);
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getDamagePhysical() {
        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public int getDirection() {
        dead = true;
        return direction;
    }

    /**
     *
     * @return
     */
    @Override
    public int getDamageSpecial() {
        dead=true;
        return damageSpecial;
    }
    
}
