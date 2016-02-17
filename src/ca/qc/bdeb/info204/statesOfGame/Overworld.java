/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.info204.statesOfGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
/**
 *
 * @author 1455367
 */
public class Overworld extends BasicGameState{
    
    private static int stateID;

    public Overworld(int stateID) {
        Overworld.stateID = stateID;
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
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }
}
