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
    ArrayList<Equipment> listItemDeleted;

    public InventoryMenu(int stateID, PlayerGameManager manager) throws SlickException {
        InventoryMenu.stateID = stateID;
        this.manager = manager;
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

//  creer la police de caractère
//        Font font = new Font("Serif", Font.BOLD, 15);
//        ufont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
//        ufont.addAsciiGlyphs();
//        ufont.addGlyphs(400, 600);
//        ufont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
//        ufont.loadGlyphs();
        
        manager.addItem("Casque Antique");
        manager.addItem("Casque Metallique");
        manager.addItem("Armure d'Argent");
        manager.addItem("Jambieres Royales");
        manager.addItem("Bouclier legendaire");
        manager.addItem("Jesus");
        manager.addItem("Banane");
        manager.addItem("Epee legendaire");
        manager.addItem("Livre bleu");
        manager.addItem("Livre mauve");
        manager.addItem("Tri-dent");
        manager.addItem("Arc de feu");
        manager.addItem("Arc artisanal");
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Overworld.getScreenShot().draw(0, 0);
        inventoryPic.draw(0, 0);
        g.setColor(Color.black);
//        g.setFont(ufont);
        int y = 350, x = 200, i = 0;

        for (int j = 0; j < statsManager.getStats().length; j++) {
            g.drawString(statsManager.getStatsName(j) + statsManager.getStats()[j], 200, 155 + (j * 23));
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
                for (int j = 0; j < itemFound.getStats().length; j++) {
                    g.drawString(itemFound.getStatName(j) + itemFound.getStats()[j], 405 + (65*j), 310);
                }
            }
            i++;
        }

        for (Equipment playerItem : manager.getInventory().getListItemPlayer()) {
            playerItem.getIcon().draw(playerItem.getInventoryX(), playerItem.getInventoryY());
            if (playerItem.getIsAbove()) {
                g.drawString(playerItem.getName(), 415, 200);
                g.drawString(playerItem.getDescription(), 435, 220);
                for (int j = 0; j < playerItem.getStats().length; j++) {
                    g.drawString(playerItem.getStatName(j) + playerItem.getStats()[j], 405 + (65*j), 310);
                }
            }
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();
        int x, y;
        boolean isSameType;

        if (gc.getInput().isKeyPressed(23) || ((mouseX < 163 || mouseX > 863 || mouseY > 580 || mouseY < 119) && gc.getInput().isMousePressed(0)) || gc.getInput().isMousePressed(1)) { //sortir du menu en pesant 'i', le bouton droit de la souris ou en clickant hors de la fenetre inventaire
            sbg.enterState(Game.OVERWORLD);
        }
        
        //passe au travers de la boucle d'item trouve
        for(Equipment itemFound : manager.getInventory().getListItemFound()){
            isSameType = false;
            //verifie si la souris est au dessus de l'item
            if ((mouseX > itemFound.getInventoryX() && mouseX < itemFound.getInventoryX() + 40)
                    && (mouseY < (700 - itemFound.getInventoryY()) && mouseY > (700 - itemFound.getInventoryY() - 50))) { 
                
                if (gc.getInput().isMousePressed(0)) {  //si l'item est clique
                    
                    //s'il y a deja un objet du meme type
                    for (Equipment itemPlayer : manager.getInventory().getListItemPlayer()) {
                        System.out.println(itemFound.getType());
                        System.out.println(itemPlayer.getType());
                        if(itemFound.getType() == itemPlayer.getType()) isSameType = true;
                    }
                    
                    //impossible de rajouter des objet si la limite d'equipement est atteinte ou s'il y a deja un objet du meme type
                    if ((manager.getInventory().getListItemPlayer().size() < MAX_LIST_JOUEUR) && !isSameType) { 
                        
                        itemFound.setIsAbove(false); //la souris n'est plus au dessus de l'objet (puisqu'il change de place)
                        manager.getInventory().getListItemPlayer().add(itemFound); // changer l'objet de liste
                        
                        //changer les stats du joueur
                        for (int k = 0; k < itemFound.getStats().length; k++) {
                            statsManager.getStats()[k] += itemFound.getStats()[k];
                        }
                        //ajouter la stat de vie de l'objet a la stat de vie courante du joueur
                        statsManager.setHp(statsManager.getHp() + itemFound.getStats()[0]);
                        listItemDeleted.add(itemFound);
                    }
                    
                } else {
                     //si l'item n'est pas clique, mais que le curseur est par dessus
                    itemFound.setIsAbove(true);
                }
            } else {
                try{
                    itemFound.setIsAbove(false); //met tous les items non-selectionne a false
                } catch (ArrayIndexOutOfBoundsException e) {} catch (IndexOutOfBoundsException e) {}            }
        }
        
        manager.getInventory().getListItemFound().removeAll(listItemDeleted);
        listItemDeleted.clear();
        
         //passe a travers la liste d'objet equipe
        for (int j = 0; j < manager.getInventory().getListItemPlayer().size(); j++) {
            //placer tous les items equipes a leur place
            x = 430 + (40 * j);
            y = 155;
            try {
                manager.getInventory().getListItemPlayer().get(j).setInventoryX(x);
                manager.getInventory().getListItemPlayer().get(j).setInventoryY(y);
            } catch (IndexOutOfBoundsException e) {}
        }

        for (Equipment itemPlayer : manager.getInventory().getListItemPlayer()) {
            
            //verifie si la souris est au dessus de l'item
            if ((mouseX > itemPlayer.getInventoryX() && mouseX < itemPlayer.getInventoryX() + 40)
                    && (mouseY < (700 - itemPlayer.getInventoryY()) && mouseY > (700 - itemPlayer.getInventoryY() - 50))) { 
                
                if (gc.getInput().isMousePressed(0)) { //si l'item est clique 
                    itemPlayer.setIsAbove(false);//la sourie n'est plus au dessus de l'objet (puisqu'il change de place)
                    manager.getInventory().getListItemFound().add(itemPlayer);// changer l'objet de liste
                    
                    //changer les stats du joueur
                    for (int k = 0; k < statsManager.getStats().length; k++) {
                        statsManager.getStats()[k] -= itemPlayer.getStats()[k];
                    }
                    //enlever la stat de vie de l'objet a la stat de vie courante du joueur
                    statsManager.setHp(statsManager.getHp() - itemPlayer.getStats()[0]);
                    
                    //replacer les objets restants
                    for (int k = manager.getInventory().getListItemPlayer().indexOf(itemPlayer); k < manager.getInventory().getListItemPlayer().size(); k++) {
                        manager.getInventory().getListItemPlayer().get(k).setInventoryX(manager.getInventory().getListItemPlayer().get(k).getInventoryX() - 40);
                    }
                    listItemDeleted.add(itemPlayer);
                    
                } else {
                    
                    itemPlayer.setIsAbove(true);  //si l'item n'est pas clique -> true
                }
                
            } else {
                try{
                itemPlayer.setIsAbove(false); //met tous les items non-selectionne a false
                } catch (ArrayIndexOutOfBoundsException e) {} catch (IndexOutOfBoundsException e) {}
            }
        }
        
        manager.getInventory().getListItemPlayer().removeAll(listItemDeleted);
        listItemDeleted.clear();
    }

    protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

}
