/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import static ca.qc.bdeb.info204.Game.HEIGHT;
import static ca.qc.bdeb.info204.Game.OVERWORLD;
import static ca.qc.bdeb.info204.Game.WIDTH;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author 1455367
 */
public class MainMenu extends BasicGameState {

    private static int stateID;
    private int playX = 400;
    private int playY = 200;
    private int newGameX = 400;
    private int newGameY = 50;

    public MainMenu(int stateID) {
        MainMenu.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        
        g.drawRect(WIDTH/2-playX/2, HEIGHT/3-playY/2, playX, playY);
        g.drawString("Play",WIDTH/2-playX/2, HEIGHT/3-10);//10 : depends on the string lenght
        g.drawRect(WIDTH/2-newGameX/2,  HEIGHT/3+playY/2+10, newGameX, newGameY);
        g.drawString("New Game", WIDTH/2-15, HEIGHT/3+playY/2+10+5);//15 & 5 : depends on the string lenght
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();
        
        if((mouseX>WIDTH/2-playX/2 && mouseX<WIDTH/2+playX/2) && (mouseY<HEIGHT-HEIGHT/3+playY/2+10 && mouseY>HEIGHT-HEIGHT/3+playY/2+10+newGameY)){
            if(input.isMouseButtonDown(0)){
                sbg.enterState(OVERWORLD);
            }
        }
        
    }

}
