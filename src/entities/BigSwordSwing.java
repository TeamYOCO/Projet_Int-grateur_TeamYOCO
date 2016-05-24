/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1455367
 */
public class BigSwordSwing extends SwordSwing implements FriendlyEntity {

    /**
     *
     * @param x
     * @param y
     * @param direction
     * @param lifeSpam
     * @throws SlickException
     */
    public BigSwordSwing(float x, float y, int direction, int lifeSpam) throws SlickException {
        super(x, y, direction, lifeSpam);
        SpriteSheet sheet = ResManager.getInstance().getSpriteSheet("longsword");
        this.animation = loadAnimation(sheet, 1, 6, direction);
        switch (direction) {
            case 0:
                xOff = -24;
                yOff = -72;
                break;
            case 1:
                xOff = -64;
                yOff = -40;
                break;
            case 2:
                xOff = -24;
                yOff = -16;
                break;
            case 3:
                xOff = 16;
                yOff = -40;
                break;
        }
        this.hitBox = new Box(x, y, 0, 0);
    }

    /**
     *
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animation, x - 98, y - 124);
//        hitBox.render(g);
    }

    /**
     *
     * @param delta
     */
    @Override
    public void update(int delta) {
        super.update(delta);
        if (lifeSpam <= 400) {
            this.hitBox = new Box(x + xOff, y + yOff, 48, 48);
        }
    }
}
