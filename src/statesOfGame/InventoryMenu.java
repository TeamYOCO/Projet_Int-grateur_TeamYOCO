/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Seb
 */
public class InventoryMenu extends BasicGameState {

    private static int stateID;
    private Image inventoryPic;
    private SpriteSheet ssFriend1,ssFriend2,ssSoldier;
    Animation anim1 = new Animation();
    Animation anim2 = new Animation();
    Animation animSoldier = new Animation();
    
    public InventoryMenu(int stateID) throws SlickException {
        InventoryMenu.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        inventoryPic = new Image("res/pictures/inventory1.png");
        ssFriend1 = new SpriteSheet("res/sprites/hairfemale.png",32,32);
        ssFriend2 = new SpriteSheet("res/sprites/hairmale.png",32,32);
        ssSoldier = new SpriteSheet("res/sprites/soldier.png",64,63);
        anim1.addFrame(ssFriend1.getSprite(0,0), 100);
        anim2.addFrame(ssFriend2.getSprite(0,0), 100);
        animSoldier.addFrame(ssSoldier.getSprite(0,2), 100);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Overworld.getScreenShot().draw(0,0);
        inventoryPic.draw(0,0);
        g.drawAnimation(animSoldier, 195, 128);
        g.drawAnimation(anim1, 206, 208);
        g.drawAnimation(anim2, 206, 258);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(gc.getInput().isKeyPressed(23)){
            sbg.enterState(Game.OVERWORLD);
        }
    }

}
