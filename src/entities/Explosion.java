/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import maps.MiniMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author Tomas
 */
public class Explosion extends Particle implements FriendlyEntity {

    private int damagePhysical;
    private int damageSpecial;
    private Image img;
    private MiniMap map;

    /**
     *
     * @param x
     * @param y
     * @param direction
     * @param lifespam
     * @param map
     * @throws SlickException
     */
    public Explosion(float x, float y, int direction, int lifespam, MiniMap map) throws SlickException {
        SpriteSheet anim = ResManager.getInstance().getSpriteSheet("explosion");
        this.x = x;
        this.y = y;
        this.xOff = 0;
        this.yOff = 0;
        this.hitBox = new Box(x, y, 256, 128);
        this.direction = direction;
        for (int i = 0; i < 4; i++) {
            animation = loadAnimation(anim, 0, 3, i);
        }
        img = animation.getImage(direction);
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
        g.drawAnimation(animation, x-128, y-64);
        this.hitBox.render(g);
    }

    /**
     *
     * @param delta
     */
    @Override
    public void update(int delta) {
        super.update(delta); //To change body of generated methods, choose Tools | Templates.
        hitBox.setPos(x + xOff, y + yOff);
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
        dead = true;
        return damageSpecial;
    }

}
