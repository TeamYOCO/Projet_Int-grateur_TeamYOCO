/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import gameEngine.ResManager;
import items.EquipmentList;
import items.Equipment;
import items.EquipmentType;
import items.IconList;
import java.awt.Font;
import java.io.Serializable;
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

/**
 *
 * @author Hassaoui
 */
public class InventoryMenu extends BasicGameState implements Serializable{

    private final CharacterStatsManager statsManager;
    private static int stateID;
    private Image inventoryPic;
    private final ArrayList<int[]> listStats;
    private int lastPosition = -1;
    private final int MAX_LIST_JOUEUR = 10;
    private UnicodeFont ufont;
    private boolean isSameType;
    private ArrayList<Equipment> listItemDeleted;

    public InventoryMenu(int stateID) throws SlickException {
        InventoryMenu.stateID = stateID;
        listStats = new ArrayList();
        statsManager = CharacterStatsManager.getInstance();
        listItemDeleted = new ArrayList();
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        inventoryPic = ResManager.getInstance().getImage("inventory1player");

////  creer la police de caractère
        ufont = ResManager.getInstance().getFont("Sherif", 15);
//        manager.addItem("Bottes Royales");
//        manager.addItem("Casque Metallique");
//        manager.addItem("Armure d'Argent");
//        manager.addItem("Jambieres Royales");
//        manager.addItem("Bouclier legendaire");
//        manager.addItem("Jesus");
//        manager.addItem("Banane");
//        manager.addItem("Epee legendaire");
//        manager.addItem("Livre bleu");
//        manager.addItem("Livre mauve");
//        manager.addItem("Arc de feu");
//        manager.addItem("Arc artisanal");

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Overworld.getScreenShot().draw(0, 0);
        inventoryPic.draw(0, 0);
        g.setColor(Color.black);
        g.setFont(ufont);
        int i = 0;
        
        for (int j = 0; j < statsManager.getStats().length; j++) {
            g.drawString(statsManager.getStatsName(j) + statsManager.getStats()[j], 200, 155 + (j * 23));
        }
        for (Equipment itemFound : CharacterStatsManager.getInstance().getInventory().getListItemFound()) {

            placeItem(itemFound, i);
            IconList.getInstance().getListIcon().get(itemFound.getName()).draw(itemFound.getInventoryX(), itemFound.getInventoryY());

            if (CharacterStatsManager.getInstance().getInventory().getListItemFound().get(i).getIsAbove()) {
                g.drawString(itemFound.getName(), 415, 200);
                g.drawString(itemFound.getDescription(), 435, 220);
                for (int j = 0; j < itemFound.getStats().length; j++) {
                    g.drawString(itemFound.getStatName(j) + itemFound.getStats()[j], 405 + (65 * j), 310);
                }
            }
            i++;
        }

        for (Equipment playerItem : CharacterStatsManager.getInstance().getInventory().getListItemPlayer()) {
            IconList.getInstance().getListIcon().get(playerItem.getName()).draw(playerItem.getInventoryX(), playerItem.getInventoryY());
            if (playerItem.getIsAbove()) {
                g.drawString(playerItem.getName(), 415, 200);
                g.drawString(playerItem.getDescription(), 435, 220);
                for (int j = 0; j < playerItem.getStats().length; j++) {
                    g.drawString(playerItem.getStatName(j) + playerItem.getStats()[j], 405 + (65 * j), 310);
                }
            }
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();

        //sortir du menu en pesant 'e', le bouton droit de la souris ou en clickant hors de la fenetre inventaire
        if (gc.getInput().isKeyPressed(18) || ((mouseX < 163 || mouseX > 863 || mouseY > 580 || mouseY < 119) && gc.getInput().isMousePressed(0)) || gc.getInput().isMousePressed(1)) { 
            sbg.enterState(Game.OVERWORLD);
        }

        //passe au travers de la boucle d'item trouve
        for (Equipment itemFound : CharacterStatsManager.getInstance().getInventory().getListItemFound()) {
            isSameType = false;
            //verifie si la souris est au dessus de l'item
            if ((mouseX > itemFound.getInventoryX() && mouseX < itemFound.getInventoryX() + 40)
                    && (mouseY < (700 - itemFound.getInventoryY()) && mouseY > (700 - itemFound.getInventoryY() - 50))) {

                if (gc.getInput().isMousePressed(0)) {  //si l'item est clique
                    itemFoundClicked(itemFound);
                } else {
                    //si l'item n'est pas clique, mais que le curseur est par dessus
                    itemFound.setIsAbove(true);
                }
            } else {
                try {
                    itemFound.setIsAbove(false); //met tous les items non-selectionnes a false
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }

        CharacterStatsManager.getInstance().getInventory().getListItemFound().removeAll(listItemDeleted);
        listItemDeleted.clear();
        
        for (Equipment itemPlayer : CharacterStatsManager.getInstance().getInventory().getListItemPlayer()) {

            //verifie si la souris est au dessus de l'item
            if ((mouseX > itemPlayer.getInventoryX() && mouseX < itemPlayer.getInventoryX() + 40)
                    && (mouseY < (700 - itemPlayer.getInventoryY()) && mouseY > (700 - itemPlayer.getInventoryY() - 50))) {

                if (gc.getInput().isMousePressed(0)) { //si l'item est clique 
                    itemPlayerClicked(itemPlayer);
                } else {
                    itemPlayer.setIsAbove(true);  //si l'item n'est pas clique -> true
                }

            } else {
                try {
                    itemPlayer.setIsAbove(false); //met tous les items non-selectionne a false
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }

        CharacterStatsManager.getInstance().getInventory().getListItemPlayer().removeAll(listItemDeleted);
        listItemDeleted.clear();
    }

    
    protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    private void placeItem(Equipment itemFound, int i) {
        int x, y;

        if (i % 16 == 0) {
            x = 200;
        } else {
            x = 200 + (40 * ((i - (i / 16) * 16)));
        }

        if (i % 16 == 0 && i != 0) {
            y = 350 + ((i / 16) * 44);
        } else {
            y=350;
        }

        itemFound.setInventoryX(x);
        itemFound.setInventoryY(y);
    }

    private void itemFoundClicked(Equipment itemFound) throws SlickException{
        //s'il y a deja un objet du meme type
        for (Equipment itemPlayer : CharacterStatsManager.getInstance().getInventory().getListItemPlayer()) {
            if (itemFound.getType() == itemPlayer.getType()) {
                isSameType = true;
            }
        }

        //impossible de rajouter des objet si la limite d'equipement est atteinte ou s'il y a deja un objet du meme type
        if ((CharacterStatsManager.getInstance().getInventory().getListItemPlayer().size() < MAX_LIST_JOUEUR) && !isSameType) {

            itemFound.setIsAbove(false); //la souris n'est plus au dessus de l'objet (puisqu'il change de place)
            CharacterStatsManager.getInstance().getInventory().getListItemPlayer().add(itemFound); // changer l'objet de liste

            //changer les stats du joueur
            for (int k = 0; k < itemFound.getStats().length; k++) {
                statsManager.getStats()[k] += itemFound.getStats()[k];
            }
            
            //ajouter la stat de vie de l'objet a la stat de vie courante du joueur
            statsManager.setHp(statsManager.getHp() + itemFound.getStats()[0]);
            
            //deplacer les icones d'items
            int x = 430 + (40 * (CharacterStatsManager.getInstance().getInventory().getListItemPlayer().size()-1));
            int y = 155;
            try {
                CharacterStatsManager.getInstance().getInventory().getListItemPlayer().get(CharacterStatsManager.getInstance().getInventory().getListItemPlayer().size()-1).setInventoryX(x);
                CharacterStatsManager.getInstance().getInventory().getListItemPlayer().get(CharacterStatsManager.getInstance().getInventory().getListItemPlayer().size()-1).setInventoryY(y);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            
            listItemDeleted.add(itemFound);
        }
    }

    private void itemPlayerClicked(Equipment itemPlayer) throws SlickException{
        itemPlayer.setIsAbove(false);//la souris n'est plus au dessus de l'objet (puisqu'il change de place)
        CharacterStatsManager.getInstance().getInventory().getListItemFound().add(itemPlayer);// changer l'objet de liste

        //changer les stats du joueur
        for (int k = 0; k < statsManager.getStats().length; k++) {
            statsManager.getStats()[k] -= itemPlayer.getStats()[k];
        }
        //enlever la stat de vie de l'objet a la stat de vie courante du joueur
        statsManager.setHp(statsManager.getHp() - itemPlayer.getStats()[0]);

        //replacer les objets restants
        for (int k = CharacterStatsManager.getInstance().getInventory().getListItemPlayer().indexOf(itemPlayer); k < CharacterStatsManager.getInstance().getInventory().getListItemPlayer().size(); k++) {
            CharacterStatsManager.getInstance().getInventory().getListItemPlayer().get(k).setInventoryX(CharacterStatsManager.getInstance().getInventory().getListItemPlayer().get(k).getInventoryX() - 40);
        }
        listItemDeleted.add(itemPlayer);
    }

}
