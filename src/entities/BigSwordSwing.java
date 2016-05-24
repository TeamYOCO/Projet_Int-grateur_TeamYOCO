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
import playerEngine.CharacterStatsManager;

/**
 *
 * @author 1455367
 */
public class BigSwordSwing extends SwordSwing{

    public BigSwordSwing(float x, float y, int direction, int lifeSpam) throws SlickException {
        super(x, y, direction, lifeSpam);
        SpriteSheet sheet = ResManager.getInstance().getSpriteSheet("longsword");
        this.animation = loadAnimation(sheet, 1, 6, direction);
        switch(direction){
            case 0: xOff = 48; yOff = 27; break;
            case 1: xOff = 11; yOff = 50; break;
            case 2: xOff = 48; yOff = 82; break;
            case 3: xOff = 85; yOff = 45; break;
        }
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
