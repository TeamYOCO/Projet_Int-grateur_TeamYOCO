/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.awt.Rectangle;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1455367
 */
public class Weapon extends Equipment {

    private int hpMax;
    private int mpMax;
    private int attack;
    private int speed;
    private int specialAttack;
    private int defence;
    private int specialDefence;
    private String name;
    private Animation animation;

    public Weapon(MiniMap map) {
        this.animation = new Animation();
        this.map = map;
    }

    @Override
    public void update(int delta) {
    }

    @Override
    public void init() throws SlickException {
        this.x = 564;
        this.y = 154;
        SpriteSheet moveSpriteSheet = new SpriteSheet("res/sprites/items1_0.png", 32, 32);
        this.animation = loadAnimation(moveSpriteSheet, 0, 1, 0);

        this.hitBox = new Rectangle(100, 100);
    }

    @Override
    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animation, x, y);
    }
}
