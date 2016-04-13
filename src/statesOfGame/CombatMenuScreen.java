/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import playerEngine.PlayerGameManager;

/**
 *
 * @author 1455367
 */
public class CombatMenuScreen extends BasicGameState{
    
    private PlayerGameManager manager;
    private static int stateID;
    

    public CombatMenuScreen(int stateID, PlayerGameManager manager) {
        CombatMenuScreen.stateID = stateID;
        this.manager = manager;
    }

    
    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        Overworld.getScreenShot().draw(0,0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }
    
}
