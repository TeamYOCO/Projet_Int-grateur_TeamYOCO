/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import items.Equipment;
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
        EquipmentList.getInstance().getListEquipment().get("Jambieres Emeraudes").setShopSelected(true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
//        background.draw(0, 0);
        int itemCompteur = 0;
        g.setColor(Color.gray);
        g.fillRect(700, 50, 300, 600);
        g.setColor(Color.black);

        
        for (String key : IconList.getInstance().getListIcon().keySet()) {
            if (EquipmentList.getInstance().getListEquipment().get(key).getShopSelected()) {
                g.setColor(Color.orange);
            } else {
                g.setColor(Color.lightGray);
            }
            g.fillRect(710, 55 + (60 * itemCompteur), 50, 50);
            IconList.getInstance().getListIcon().get(key).draw(719, 64 + (60 * itemCompteur));
            g.drawString(EquipmentList.getInstance().getListEquipment().get(key).getName(), 770, 60 + (60 * itemCompteur));
            g.drawString(EquipmentList.getInstance().getListEquipment().get(key).getPrice() + "$", 770, 80 + (60 * itemCompteur));
            itemCompteur += 1;
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();

        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();
        
        boolean next = false;
        
        if(input.isKeyPressed(Input.KEY_DOWN)){
            for (Equipment equipment : EquipmentList.getInstance().getListEquipment().values()) {
                if(next){
                    equipment.setShopSelected(true);
                    next = false;
                }
                if(equipment.getShopSelected()){
                    next = true;
                }
            }
        }
        
        if(input.isKeyPressed(Input.KEY_UP)){
            for (Equipment equipment : EquipmentList.getInstance().getListEquipment().values()) {
                if(next){
                    equipment.setShopSelected(true);
                    next = false;
                }
                if(equipment.getShopSelected()){
                    next = true;
                }
            }
        }

    }
}
