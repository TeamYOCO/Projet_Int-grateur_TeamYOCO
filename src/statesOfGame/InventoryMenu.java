/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import gameEngine.ResManager;
import gameEngine.EquipmentList;
import items.Equipment;
import java.awt.Font;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import playerEngine.CharacterStatsManager;
import playerEngine.PlayerGameManager;

/**
 *
 * @author Hassaoui
 */
public class InventoryMenu extends BasicGameState {

    private final PlayerGameManager manager;
    private final CharacterStatsManager statsManager;
    private static int stateID;
    private Image inventoryPic;
    private final ArrayList<int[]> listStats;
    private int lastPosition = -1;
    private final int MAX_LIST_JOUEUR = 10;
    private UnicodeFont ufont;

    public InventoryMenu(int stateID, PlayerGameManager manager) throws SlickException {
        InventoryMenu.stateID = stateID;
        this.manager = manager;
        listStats = new ArrayList();
        statsManager = CharacterStatsManager.getInstance();
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        inventoryPic = ResManager.getInstance().getImage("inventory1player");

//  creer la police de caractère
//        Font font = new Font("Serif", Font.BOLD, 15);
//        ufont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
//        ufont.addAsciiGlyphs();
//        ufont.addGlyphs(400, 600);
//        ufont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
//        ufont.loadGlyphs();
        manager.getInventory().getListItemFound().add(EquipmentList.getInstance().getListEquipment().get("Casque Antique"));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Overworld.getScreenShot().draw(0, 0);
        inventoryPic.draw(0, 0);
        g.setColor(Color.black);
//        g.setFont(ufont);
        int y = 350, x = 200, i = 0;

        for (int j = 0; j < 6; j++) {
            g.drawString(statsManager.getStatsName(j) + statsManager.getStats()[j], 200, 155 + (j * 28));
        }
        for (Equipment itemFound : manager.getInventory().getListItemFound()) {
            if (i % 16 == 0) {
                x = 200;
            } else {
                x = 200 + (40 * ((i - (i / 16) * 16)));
            }

            if (i % 16 == 0 && i != 0) {
                y = 350 + ((i / 16) * 44);
            }
            itemFound.setInventoryX(x);
            itemFound.setInventoryY(y);
            itemFound.getIcon().draw(itemFound.getInventoryX(), itemFound.getInventoryY());

            if (manager.getInventory().getListItemFound().get(i).getIsAbove()) {
                g.drawString(itemFound.getName(), 415, 200);
                g.drawString(itemFound.getDescription(), 435, 220);
                g.drawString("Vie-" + itemFound.getHpMax(), 405, 310);
                g.drawString("Att-" + itemFound.getAttack(), 480, 310);
                g.drawString("Def-" + itemFound.getDefence(), 555, 310);
                g.drawString("SpA-" + itemFound.getSpecialAttack(), 630, 310);
                g.drawString("SpD-" + itemFound.getSpecialDefence(), 705, 310);
                g.drawString("Vit-" + itemFound.getSpeed(), 780, 310);
            }
            i++;
        }

        for (Equipment playerItem : manager.getInventory().getListItemPlayer()) {
            playerItem.getIcon().draw(playerItem.getInventoryX(), playerItem.getInventoryY());
            if (playerItem.getIsAbove()) {
                g.drawString(playerItem.getName(), 415, 200);
                g.drawString(playerItem.getDescription(), 435, 220);
                g.drawString("Vie-" + playerItem.getHpMax(), 405, 310);
                g.drawString("Att-" + playerItem.getAttack(), 480, 310);
                g.drawString("Def-" + playerItem.getDefence(), 555, 310);
                g.drawString("SpA-" + playerItem.getSpecialAttack(), 630, 310);
                g.drawString("SpD-" + playerItem.getSpecialDefence(), 705, 310);
                g.drawString("Vit-" + playerItem.getSpeed(), 780, 310);
            }
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();
        int x, y;

        if (gc.getInput().isKeyPressed(23) || ((mouseX < 163 || mouseX > 863 || mouseY > 580 || mouseY < 119) && gc.getInput().isMousePressed(0)) || gc.getInput().isMousePressed(1)) { //sortir du menu en pesant 'i', le bouton droit de la souris ou en clickant hors de la fenetre inventaire
            sbg.enterState(Game.OVERWORLD);
        }

        for (int j = 0; j < manager.getInventory().getListItemFound().size(); j++) {
            if ((mouseX > manager.getInventory().getListItemFound().get(j).getInventoryX() && mouseX < manager.getInventory().getListItemFound().get(j).getInventoryX() + 40)
                    && (mouseY < (700 - manager.getInventory().getListItemFound().get(j).getInventoryY()) && mouseY > (700 - manager.getInventory().getListItemFound().get(j).getInventoryY() - 50))) { //verifie si la souris est au dessus de l'item
                try {
                    manager.getInventory().getListItemFound().get(lastPosition).setIsAbove(false); //set le dernier item selectionne a false
                } catch (ArrayIndexOutOfBoundsException e) {
                } catch (IndexOutOfBoundsException e) {
                }
                lastPosition = j;
                if (gc.getInput().isMousePressed(0)) {  //si l'item est clique -> false
                    if (manager.getInventory().getListItemPlayer().size() < MAX_LIST_JOUEUR) {
                        manager.getInventory().getListItemFound().get(j).setIsAbove(false);
                        manager.getInventory().getListItemPlayer().add(manager.getInventory().getListItemFound().get(j));
                        for (int k = 0; k < 6; k++) {
                            statsManager.getStats()[k] += manager.getInventory().getListItemFound().get(j).getStats()[k];
                        }
                        manager.getInventory().getListItemFound().remove(j);
                    }
                } else {
                    manager.getInventory().getListItemFound().get(j).setIsAbove(true); //si l'item n'est pas clique -> true
                }
            } else {
                manager.getInventory().getListItemFound().get(j).setIsAbove(false); //met tous les items non-selectionne a false
            }
        }
        for (int j = 0; j < manager.getInventory().getListItemPlayer().size(); j++) { //changer la position des objets qui viennent d'etre deplace
            x = 430 + (40 * j);
            y = 155;
            try {
                manager.getInventory().getListItemPlayer().get(j).setInventoryX(x);
                manager.getInventory().getListItemPlayer().get(j).setInventoryY(y);
            } catch (IndexOutOfBoundsException e) {
            }
        }

        for (int j = 0; j < manager.getInventory().getListItemPlayer().size(); j++) {
            if ((mouseX > manager.getInventory().getListItemPlayer().get(j).getInventoryX() && mouseX < manager.getInventory().getListItemPlayer().get(j).getInventoryX() + 40)
                    && (mouseY < (700 - manager.getInventory().getListItemPlayer().get(j).getInventoryY()) && mouseY > (700 - manager.getInventory().getListItemPlayer().get(j).getInventoryY() - 50))) { //verifie si la souris est au dessus de l'item
                try {
                    manager.getInventory().getListItemPlayer().get(lastPosition).setIsAbove(false); //set le dernier item selectionne a false
                } catch (ArrayIndexOutOfBoundsException e) {
                } catch (IndexOutOfBoundsException e) {
                }
                lastPosition = j;
                if (gc.getInput().isMousePressed(0)) { //si l'item est clique -> false
                    manager.getInventory().getListItemPlayer().get(j).setIsAbove(false);
                    manager.getInventory().getListItemFound().add(manager.getInventory().getListItemPlayer().get(j));
                    for (int k = 0; k < 6; k++) {
                        statsManager.getStats()[k] -= manager.getInventory().getListItemPlayer().get(j).getStats()[k];
                    }
                    manager.getInventory().getListItemPlayer().remove(j);
                    for (int k = j; k < manager.getInventory().getListItemPlayer().size(); k++) {
                        manager.getInventory().getListItemPlayer().get(k).setInventoryX(manager.getInventory().getListItemPlayer().get(k).getInventoryX() - 40);
                    }
                } else {
                    manager.getInventory().getListItemPlayer().get(j).setIsAbove(true);  //si l'item n'est pas clique -> true
                }
            } else {
                manager.getInventory().getListItemPlayer().get(j).setIsAbove(false); //met tous les items non-selectionne a false
            }
        }
    }

    protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

}
