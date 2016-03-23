/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import static ca.qc.bdeb.info204.Game.HEIGHT;
import static ca.qc.bdeb.info204.Game.OVERWORLD;
import static ca.qc.bdeb.info204.Game.WIDTH;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final String menuTheme = "102-menu-selection.WAV";
    private final String overworldTheme = "006-link-s-house.WAV";
    Music menuMusic,overworldMusic;

    public MainMenu(int stateID) throws SlickException {
        MainMenu.stateID = stateID;
        menuMusic = new Music(menuTheme);
        overworldMusic = new Music(overworldTheme);
        menuMusic.play();
        
        
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
        g.drawString("Continuer",WIDTH/2-35, HEIGHT/3-10);//10 : depends on the string lenght
        g.drawRect(WIDTH/2-newGameX/2,  HEIGHT/3+playY/2+10, newGameX, newGameY);
        g.drawString("Nouvelle partie", WIDTH/2-70, HEIGHT/3+playY/2+10+15);//15 & 5 : depends on the string lenght
        g.drawRect(WIDTH/2-settingsX, HEIGHT/3+playY/2+10+newGameY+10, settingsX, settingsY);
        g.drawString("Paramètres", WIDTH/2-settingsX/2-40, HEIGHT/3+playY/2+10+newGameY+10+15);//40 & 15 : depends on the string lenght
        g.drawRect(WIDTH/2, HEIGHT/3+playY/2+10+newGameY+10, creditsX, creditsY);
        g.drawString("Credits", WIDTH/2+creditsX/2-40, HEIGHT/3+playY/2+10+newGameY+10+15);//40 & 15 : depends on the string lenght
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();
        
        if((mouseX>(WIDTH/2-playX/2) && mouseX<WIDTH/2+playX/2) && (mouseY>2*HEIGHT/3-playY/2 && mouseY<2*HEIGHT/3+playY/2)){
            if(input.isMouseButtonDown(0)){
                menuMusic.stop();
                overworldMusic.play();
                sbg.enterState(OVERWORLD);
            }
        }
        
        
    }

}
