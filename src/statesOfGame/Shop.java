/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import items.EquipmentList;
import items.IconList;
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
public class Shop extends BasicGameState {

    private static int stateID;
    private Image background;

    public Shop(int stateID) throws SlickException {
        Shop.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
//        background = Overworld.getScreenShot();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
//        background.draw(0, 0);
        
        g.setColor(Color.gray);
        g.fillRect(800, 50, 200, 600);
        g.setColor(Color.lightGray);
        for (int i = 0; i < 10; i++) {
            g.fillRect(810, 55+(60*i), 50, 50);
        }
        IconList.getInstance().getListIcon().get("Casque Antique").draw(819,64+(0));
        IconList.getInstance().getListIcon().get("Casque Metallique").draw(819,64+(60));
        IconList.getInstance().getListIcon().get("Casque d'Argent").draw(819,64+(120));
        IconList.getInstance().getListIcon().get("Casque Emeraude").draw(819,64+(180));
        IconList.getInstance().getListIcon().get("Casque Royal").draw(819,64+(240));
        IconList.getInstance().getListIcon().get("Armure Antique").draw(819,64+(300));
        IconList.getInstance().getListIcon().get("Armure Metallique").draw(819,64+(360));
        IconList.getInstance().getListIcon().get("Armure d'Argent").draw(819,64+(420));
        IconList.getInstance().getListIcon().get("Armure Emeraude").draw(819,64+(480));
        IconList.getInstance().getListIcon().get("Armure Royale").draw(819,64+(540));

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();

        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();
        

    }
}
