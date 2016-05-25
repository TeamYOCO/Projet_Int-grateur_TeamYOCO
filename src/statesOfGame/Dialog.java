/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
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
public class Dialog extends BasicGameState {

    private static int stateID;
    private Image background;
    private static String text = "...";
    
    /**
     *
     * @param stateID
     * @throws SlickException
     */
    public Dialog(int stateID) throws SlickException {
        Dialog.stateID = stateID;
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
        background = Overworld.getScreenShot();
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
        g.fillRect(12, 550, 1000, 150);
        g.setColor(Color.black);
        g.drawString(text, 20, 570);
        g.drawString("Ok", 990, 680);
        
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

        if (((mouseX > 980 && mouseX < 1012) && (mouseY > 4 && mouseY < 20) && (input.isMouseButtonDown(0))) || (input.isKeyPressed(Input.KEY_SPACE))) {
            sbg.enterState(Game.OVERWORLD);
        }
    }
    
    public static void setText(String text){
        Dialog.text = text;
    }
}
