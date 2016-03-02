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
    private final int playX = 400,playY = 200;
    private final int newGameX = 400,newGameY = 50;
    private final int settingsX = 200, settingsY = 50;
    private final int creditsX = 200, creditsY = 50;

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
        g.drawString("Play",WIDTH/2-15, HEIGHT/3-10);//10 : depends on the string lenght
        g.drawRect(WIDTH/2-newGameX/2,  HEIGHT/3+playY/2+10, newGameX, newGameY);
        g.drawString("New Game", WIDTH/2-30, HEIGHT/3+playY/2+10+15);//15 & 5 : depends on the string lenght
        g.drawRect(WIDTH/2-settingsX, HEIGHT/3+playY/2+10+newGameY+10, settingsX, settingsY);
        g.drawString("Paramètres", WIDTH/2-settingsX/2-40, HEIGHT/3+playY/2+10+newGameY+10+15);
        g.drawRect(WIDTH/2, HEIGHT/3+playY/2+10+newGameY+10, creditsX, creditsY);
        g.drawString("Credits", WIDTH/2+creditsX/2-40, HEIGHT/3+playY/2+10+newGameY+10+15);
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
