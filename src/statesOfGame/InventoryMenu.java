/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import playerEngine.PlayerGameManager;

/**
 *
 * @author Hassaoui
 */
public class InventoryMenu extends BasicGameState {

    private PlayerGameManager manager;
    private static int stateID;
    private Image inventoryPic;
    private Animation itemIcons[];
    private ArrayList<Animation> listItemFound;
    
    public InventoryMenu(int stateID, PlayerGameManager manager) throws SlickException {
        this.itemIcons = new Animation[144];
        InventoryMenu.stateID = stateID;
        this.manager = manager;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        listItemFound = new ArrayList();
        int compt = 0;
        inventoryPic = new Image("res/pictures/inventory.png");
        SpriteSheet moveSpriteSheet = new SpriteSheet("res/sprites/items1_0.png", 32, 32);
        for (int j = 0; j < 16; j++) {
            for (int i = 0; i < 9; i++) {
                itemIcons[compt] = loadAnimation(moveSpriteSheet, i, (i+1), j);
                compt += 1;
            }
        }
        listItemFound.add(itemIcons[15]);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Overworld.getScreenShot().draw(0,0);
        inventoryPic.draw(0,0);
        for (Animation anim : listItemFound) {
            anim.draw(200, 350);
        }
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(gc.getInput().isKeyPressed(23)){
            sbg.enterState(Game.OVERWORLD);
        }
    }
    
    protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

}
