/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import static ca.qc.bdeb.info204.Game.CREDITS;
import static ca.qc.bdeb.info204.Game.HEIGHT;
import static ca.qc.bdeb.info204.Game.HELP;
import static ca.qc.bdeb.info204.Game.OVERWORLD;
import static ca.qc.bdeb.info204.Game.WIDTH;
import gameEngine.DataManager;
import gameEngine.ResMng;
import items.EquipmentList;
import maps.MiniMap;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import playerEngine.StatsMng;

/**
 *
 * @author 1455367
 */
public class MainMenu extends BasicGameState {

    private static int stateID;
    private final int playX = 400, playY = 200;
    private final int newGameX = 400, newGameY = 50;
    private final int settingsX = 200, settingsY = 50;
    private final int creditsX = 200, creditsY = 50;
    private final String menuTheme = "res/musics/102-menu-selection.WAV";
    private Image background;
    private Music menuMusic;
    private boolean stopMusic = true, saveFileIsEmpty;

    public MainMenu(int stateID) throws SlickException {
        MainMenu.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = ResMng.getInstance().getImage("treesun");
        menuMusic = new Music(menuTheme);
        ResMng resManager = ResMng.getInstance();
        EquipmentList strManager = EquipmentList.getInstance();
        if(DataManager.getInstance().isEmpty()){
            saveFileIsEmpty = true;
        } else {
            saveFileIsEmpty = false;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        background.draw(0, 0);
        if(saveFileIsEmpty){
            g.setColor(Color.darkGray);
        } else {
            g.setColor(Color.white);
        }
        g.drawRect(WIDTH / 2 - playX / 2, HEIGHT / 3 - playY / 2, playX, playY);
        g.drawString("Continuer", WIDTH / 2 - 35, HEIGHT / 3 - 10);//10 : depends on the string lenght
        g.setColor(Color.white);
        g.drawRect(WIDTH / 2 - newGameX / 2, HEIGHT / 3 + playY / 2 + 10, newGameX, newGameY);
        g.drawString("Nouvelle partie", WIDTH / 2 - 70, HEIGHT / 3 + playY / 2 + 10 + 15);//15 & 5 : depends on the string lenght
        g.drawRect(WIDTH / 2 - settingsX, HEIGHT / 3 + playY / 2 + 10 + newGameY + 10, settingsX, settingsY);
        g.drawString("Commandes", WIDTH / 2 - settingsX / 2 - 40, HEIGHT / 3 + playY / 2 + 10 + newGameY + 10 + 15);//40 & 15 : depends on the string lenght
        g.drawRect(WIDTH / 2, HEIGHT / 3 + playY / 2 + 10 + newGameY + 10, creditsX, creditsY);
        g.drawString("Credits", WIDTH / 2 + creditsX / 2 - 40, HEIGHT / 3 + playY / 2 + 10 + newGameY + 10 + 15);//40 & 15 : depends on the string lenght
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();

        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();

        //clisque sur le bouton "nouvelle partie"
        if ((mouseX > (WIDTH / 2 - newGameX / 2) && mouseX < WIDTH / 2 + newGameX / 2) 
                && (mouseY > 2 * HEIGHT / 3 - playY / 2 - 10 - newGameY && mouseY < 2 * HEIGHT / 3 - playY / 2 - 10)) {
            if (input.isMouseButtonDown(0)) {
                stopMusic = true;
                StatsMng.getInstance().addItem("Epee de bois");
                Overworld.setNewGame(true);
                sbg.enterState(OVERWORLD, new FadeOutTransition(), new EmptyTransition());
            }
        }
        
        //clique sur le bouton "credits"
        if ((mouseX > WIDTH / 2 && mouseX < WIDTH / 2 + creditsX) 
                && (mouseY > 2 * HEIGHT / 3 - playY / 2 - 10 - newGameY - 10 - creditsY 
                && mouseY < 2 * HEIGHT / 3 - playY / 2 - 10 - newGameY - 10)) {
            if (input.isMouseButtonDown(0)) {
                stopMusic = false;
                sbg.enterState(CREDITS);
            }
        }
        
        //clique sur le bouton "continuer"
        if((mouseX> (WIDTH / 2 - playX / 2) && mouseX < (WIDTH / 2 + playX / 2)) 
                && (mouseY < (2*HEIGHT/3 + playY/2) && mouseY > 2*HEIGHT/3 - playY/2)
                && !saveFileIsEmpty){
            if(input.isMousePressed(0)){
                Overworld.setNewGame(false);
                sbg.enterState(OVERWORLD);
            }
        }
        
        //clique sur le bouton "commandes"
        if((mouseX> (WIDTH / 2 - settingsX) && mouseX < (WIDTH / 2)) 
                && (mouseY > 2 * HEIGHT / 3 - playY / 2 - 10 - newGameY - 10 - settingsY 
                && mouseY < 2 * HEIGHT / 3 - playY / 2 - 10 - newGameY - 10)){
            if(input.isMousePressed(0)){
                stopMusic = false;
                sbg.enterState(HELP);
            }
        }
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
        if (stopMusic) {
            menuMusic.play();
            menuMusic.loop();
        }
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) {
        if (stopMusic) {
            menuMusic.stop();
        }
    }

}
