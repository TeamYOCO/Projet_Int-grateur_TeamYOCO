/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import java.util.Random;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author The Ninetail
 */
public class Tornade extends Particle implements FriendlyEntity {

    private int damagePhysical;
    private int damageSpecial;
    private Animation transitionAnimation[];

    public Tornade(float x, float y, int direction) throws SlickException {
        Image temp = ResManager.getInstance().getSpriteSheet("tornado").getScaledCopy(1.5f);
        SpriteSheet sheet = new SpriteSheet(temp, 192, 192);
        this.animation = animation;
        transitionAnimation = new Animation[2];
        this.x = x;
        this.y = y;
        this.xOff = -70;
        this.yOff = -32;
        this.damagePhysical = CharacterStatsManager.getInstance().getStats()[1];
        this.damageSpecial = CharacterStatsManager.getInstance().getStats()[3]*2;
        this.direction = direction;
        this.lifeSpam = 800;
        switch (direction) {
            case 0:
                speedY = -0.5f;
                break;
            case 1:
                speedX = -0.5f;
                break;
            case 2:
                speedY = 0.5f;
                break;
            case 3:
                speedX = 0.5f;
                break;
        }
        this.hitBox = new Box(x + xOff, y + yOff, 128, 128);
        animation = addAnimation(sheet, 0, 4, 0, animation);
        animation = addAnimation(sheet, 0, 4, 1, animation);
        animation = addAnimation(sheet, 0, 4, 2, animation);
        animation = addAnimation(sheet, 0, 4, 1, animation);
        animation = addAnimation(sheet, 0, 4, 2, animation);
        animation = addAnimation(sheet, 0, 4, 3, animation);
        animation.setSpeed(3);
    }

    @Override
    public void update(int delta) {
        super.update(delta);
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animation, x - 96, y - 96);
//        hitBox.render(g);
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
        int redirection = 0;
        Random rnd = new Random();
        switch (direction) {
            case 0:
                if (rnd.nextInt() % 2 == 1) {
                    redirection = 1;
                } else {
                    redirection = 3;
                }
            case 1:
                if (rnd.nextInt() % 2 == 1) {
                    redirection = 0;
                } else {
                    redirection = 2;
                }
            case 2:
                if (rnd.nextInt() % 2 == 1) {
                    redirection = 1;
                } else {
                    redirection = 3;
                }
            case 3:
                if (rnd.nextInt() % 2 == 1) {
                    redirection = 0;
                } else {
                    redirection = 2;
                }
        }
        return redirection;
    }

}
