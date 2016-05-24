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
 * @author Hassaoui
 */
public class Credits extends BasicGameState {

    private static int stateID;
    private Image background;
    
    /**
     *
     * @param stateID
     * @throws SlickException
     */
    public Credits(int stateID) throws SlickException {
        Credits.stateID = stateID;
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
        background = ResManager.getInstance().getImage("treesun");
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
        background.draw(0,0);
        g.setColor(Color.white);
        g.drawString("Guenette, Hassaoui, Nantel\nMention spéciale : ta mère", WIDTH/2, HEIGHT/2);
        g.drawString("< Retour", 15, HEIGHT-25);
        
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

        if ((mouseX > 0 && mouseX < 100) && (mouseY > 0 && mouseY < 25)) {
            if (input.isMouseButtonDown(0)) {
                sbg.enterState(MAINMENU);
            }
        }
    }
}
