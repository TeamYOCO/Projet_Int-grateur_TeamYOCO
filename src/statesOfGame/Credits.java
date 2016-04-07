/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import static ca.qc.bdeb.info204.Game.HEIGHT;
import static ca.qc.bdeb.info204.Game.MAINMENU;
import static ca.qc.bdeb.info204.Game.WIDTH;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import playerEngine.PlayerGameManager;

/**
 *
 * @author Hassaoui
 */
public class Credits extends BasicGameState {

    private PlayerGameManager manager;
    private static int stateID;
    private Image background;
    
    public Credits(int stateID, PlayerGameManager manager) throws SlickException {
        Credits.stateID = stateID;
        this.manager = manager;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("res/pictures/tree_sun.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw(0,0);
        g.setColor(Color.white);
        g.drawString("Guenette, Hassaoui, Nantel", WIDTH/2, HEIGHT/2);
        g.drawString("< Retour", 15, HEIGHT-25);
        
    }

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
