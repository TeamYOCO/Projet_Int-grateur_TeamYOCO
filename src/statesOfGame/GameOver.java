/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import static ca.qc.bdeb.info204.Game.HEIGHT;
import static ca.qc.bdeb.info204.Game.MAINMENU;
import static ca.qc.bdeb.info204.Game.WIDTH;
import gameEngine.ResManager;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Olivier
 */
public class GameOver extends BasicGameState {

    private static int stateID;
    
    /**
     *
     * @param stateID
     * @throws SlickException
     */
    public GameOver(int stateID) throws SlickException {
        GameOver.stateID = stateID;
    }

    /**
     *
     * @return
     */
    @Override
    public int getID() {
        return stateID;
    }

    /**
     *
     * @param gc
     * @param sbg
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    /**
     *
     * @param gc
     * @param sbg
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.drawString("Vous avez perdu", WIDTH/2-70, HEIGHT/2+10);
        g.drawString("K.", WIDTH/2-5, HEIGHT/2+300);
    }

    /**
     *
     * @param gc
     * @param sbg
     * @param i
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();

        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();

        if ((mouseX > WIDTH/2-5 && mouseX < WIDTH/2+5) && (mouseY > HEIGHT/2-320 && mouseY < HEIGHT/2-300)) {
            if (input.isMouseButtonDown(0)) {
                sbg.enterState(MAINMENU);
            }
        }
    }
}
