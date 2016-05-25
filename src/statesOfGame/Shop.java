/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import static ca.qc.bdeb.info204.Game.OVERWORLD;
import gameEngine.DataManager;
import gameEngine.ResManager;
import items.Equipment;
import items.EquipmentList;
import items.IconList;
import java.util.ArrayList;
import java.util.TreeMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author Olivier
 */
public class Shop extends BasicGameState {

    private static int stateID;
    private Image background;
    private TreeMap<String, Equipment> displayItems = new TreeMap();
    private TreeMap<String, Equipment> reversedDisplayItems;
    private final int MAX_ITEM_SHOP = 10;
    private int minimum = 0, maximum, compteur = 0;
    private boolean clearMap = false, isOwned = false, isEntered = false;
    private static boolean confirmation = false;
    private Equipment equipmentSelected;
    private ArrayList<Equipment> listEquipmentAdded = new ArrayList();
    private UnicodeFont ufont15;

    /**
     *
     * @param stateID
     * @throws SlickException
     */
    public Shop(int stateID) throws SlickException {
        this.maximum = MAX_ITEM_SHOP;
        Shop.stateID = stateID;
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
        int compteur = 0;
        EquipmentList.getInstance().getListEquipment().get("Arc angelique").setShopSelected(true);
        ufont15 = ResManager.getInstance().getFont("Sherif", 15);
    }

    /**
     *
     * @param gc
     * @param sbg
     * @throws SlickException
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        isEntered = true;
    }

    /**
     *
     * @param gc
     * @param sbg
     * @throws SlickException
     */
    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        isEntered = false;
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
        if (isEntered) {
            background.draw(0, 0);
            g.setFont(ufont15);
            int itemCompteur = 0, displayCompteur = 0;
            g.setColor(Color.gray);
            g.fillRect(700, 50, 300, 600);
            g.setColor(Color.black);

            if (clearMap) {
                displayItems.clear();
                reversedDisplayItems.clear();
                clearMap = false;
            }

            for (String key : IconList.getInstance().getListIcon().keySet()) {
                if (displayCompteur >= minimum && displayCompteur < maximum) {
                    displayItems.put(key, EquipmentList.getInstance().getListEquipment().get(key));
                }
                displayCompteur += 1;
            }
            reversedDisplayItems = new TreeMap<>(displayItems.descendingMap());

            for (String key : displayItems.keySet()) {
                isOwned = false;
                if (EquipmentList.getInstance().getListEquipment().get(key).getShopSelected()) {
                    g.setColor(Color.orange);
                    equipmentSelected = EquipmentList.getInstance().getListEquipment().get(key);
                } else {
                    g.setColor(Color.lightGray);
                }
                g.fillRect(710, 55 + (60 * itemCompteur), 50, 50);
                IconList.getInstance().getListIcon().get(key).draw(719, 64 + (60 * itemCompteur));
                g.drawString(EquipmentList.getInstance().getListEquipment().get(key).getName(), 770, 60 + (60 * itemCompteur));

                for (Equipment equipment : CharacterStatsManager.getInstance().getInventory().getListItemFound()) {
                    if (equipment == EquipmentList.getInstance().getListEquipment().get(key)) {
                        isOwned = true;
                    }
                }
                for (Equipment equipment : CharacterStatsManager.getInstance().getInventory().getListItemPlayer()) {
                    if (equipment == EquipmentList.getInstance().getListEquipment().get(key)) {
                        isOwned = true;
                    }
                }
                if (!isOwned) {
                    g.drawString(EquipmentList.getInstance().getListEquipment().get(key).getPrice() + "$", 770, 80 + (60 * itemCompteur));
                } else {
                    g.drawString("Déjà obtenu", 770, 80 + (60 * itemCompteur));
                }
                itemCompteur += 1;
            }

            g.setColor(Color.white);
            g.fillRect(220, 13, 200, 50);
            g.setColor(Color.black);
            g.drawRect(220, 13, 200, 50);
            g.drawString("Money: " + CharacterStatsManager.getInstance().getMoney() + "$", 222, 43);
            g.drawString("Level: " + CharacterStatsManager.getInstance().getLevel(), 222, 15);
        }
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

        boolean next = false, present = true;
        try {
            if (isEntered) {
                if (input.isKeyPressed(Input.KEY_DOWN)) {
                    for (Equipment equipment : displayItems.values()) {
                        if (next) {
                            equipment.setShopSelected(true);
                            next = false;
                            present = false;
                        }
                        if (equipment.getShopSelected() && present) {
                            if (equipment != EquipmentList.getInstance().getEquipment("Livre rouge")) {
                                compteur++;
                                equipment.setShopSelected(false);
                                next = true;
                                if (compteur == maximum - 1) {
                                    minimum++;
                                    maximum++;
                                    clearMap = true;
                                }
                            }
                        }
                    }
                }
                present = true;

                if (input.isKeyPressed(Input.KEY_UP)) {
                    for (Equipment equipment : reversedDisplayItems.values()) {
                        if (next) {
                            equipment.setShopSelected(true);
                            next = false;
                            present = false;
                        }
                        if (equipment.getShopSelected() && present) {
                            if (equipment != EquipmentList.getInstance().getEquipment("Arc angelique")) {
                                compteur--;
                                equipment.setShopSelected(false);
                                next = true;
                                if (compteur == minimum) {
                                    minimum--;
                                    maximum--;
                                    clearMap = true;
                                }
                            }
                        }
                    }
                }

                if (input.isKeyPressed(Input.KEY_ENTER)) {
                    for (Equipment equipment : CharacterStatsManager.getInstance().getInventory().getListItemFound()) {
                        if (equipmentSelected == equipment) {
                            isOwned = true;
                        }
                    }

                    for (Equipment equipment : CharacterStatsManager.getInstance().getInventory().getListItemPlayer()) {
                        if (equipmentSelected == equipment) {
                            isOwned = true;
                        }
                    }
                    if (!isOwned && CharacterStatsManager.getInstance().getMoney() >= equipmentSelected.getPrice()) {
                        CharacterStatsManager.getInstance().buyItem(equipmentSelected.getPrice());
                        listEquipmentAdded.add(equipmentSelected);
                    }
                    for (Equipment equipment : listEquipmentAdded) {
                        CharacterStatsManager.getInstance().addItem(equipment);
                    }
                    listEquipmentAdded.clear();
                }
                
                if(input.isKeyPressed(Input.KEY_SPACE)){
                    sbg.enterState(Game.OVERWORLD);
                }
            }
        } catch (NullPointerException e) {
        }
    }

    public static void setConfirmation(boolean bool) {
        confirmation = bool;
    }
}
