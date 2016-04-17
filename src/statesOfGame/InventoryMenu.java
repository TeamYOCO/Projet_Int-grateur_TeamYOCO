/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import items.Equipment;
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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import playerEngine.PlayerGameManager;

/**
 *
 * @author Hassaoui
 */
public class InventoryMenu extends BasicGameState {

    private final PlayerGameManager manager;
    private static int stateID;
    private Image inventoryPic;
    private final Animation itemIcons[];
    private String itemName[];
    private String itemDescription[];
    private final ArrayList<int[]> listStats;
    private ArrayList<Equipment> listItemFound;
    private static ArrayList<Equipment> listItemPlayer;
    private int lastPosition = -1;
    private final int MAX_LIST_JOUEUR = 10, SPRITE_SHEET_HEIGHT = 13, SPRITE_SHEET_WIDTH = 5;
    private UnicodeFont ufont;

    public InventoryMenu(int stateID, PlayerGameManager manager) throws SlickException {
        this.itemIcons = new Animation[65];
        InventoryMenu.stateID = stateID;
        this.manager = manager;
        listStats = new ArrayList();
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        listItemFound = new ArrayList();
        listItemPlayer = new ArrayList();
        int compt = 0;
        inventoryPic = new Image("res/pictures/inventory1player.png");
        SpriteSheet moveSpriteSheet = new SpriteSheet("res/sprites/new_items.png", 32, 32);
        for (int j = 0; j < SPRITE_SHEET_HEIGHT; j++) {
            for (int i = 0; i < SPRITE_SHEET_WIDTH; i++) {
                itemIcons[compt] = loadAnimation(moveSpriteSheet, i, (i + 1), j);
                compt += 1;
            }
        }
//  creer la police de caractère
//        Font font = new Font("Serif", Font.BOLD, 15);
//        ufont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
//        ufont.addAsciiGlyphs();
//        ufont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
//        ufont.loadGlyphs();

        this.itemName = new String[]{"Casque Antique", "Casque Metallique", "Casque d'Argent", "Casque Emeraude", "Casque Royal",
            "Armure Antique", "Armure Metallique", "Armure d'Argent", "Armure Emeraude", "Armure Royale",
            "Jambieres Antiques", "Jambieres Metalliques", "Jambieres d'Argent", "Jambieres Emeraudes", "Jambieres Royales",
            "Bottes Antiques", "Bottes Metalliques", "Bottes d'Argent", "Bottes Emeraudes", "Bottes Royales",
            "Bouclier Serpent", "Bouclier de bois", "Bouclier legendaire", "Bouclier simple", "Jesus",
            "Epee de bois", "Epee rudimentaire", "Epee metallique", "Epee d'argent", "Epee Royale",
            "Banane", "Epee legendaire", "Epee de glace", "Epee rose", "Epee extraterrestre",
            "Hache de bois", "Hache rudimentaire", "Hache metallique", "Hache d'argent", "Hache Royale",
            "Hache lourde", "Hache legere", "Hache de feu", "Hache sinistre", "Hache artisanale",
            "Baton clair", "Baton offensif", "Tri-dent magique", "Baton etrangleur", "Large baton",
            "Baton clair", "Baton metallique", "Baton jaune", "Cane de Noel", "Baton precieux",
            "Arc simple", "Arc elegant", "Arc lourd", "Arc sombre", "Arc clair",
            "Arc infernal", "Arc de feu", "Arc angelique", "Arc metallique", "Arc artisanal"};
        this.itemDescription = new String[]{"Procure une protection minimale \ncontre les attaques directes",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""};
        
        listStats.add(new int[]{944,944,944,944});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});
        listStats.add(new int[]{1,1,1,1});

        for (int i = 0; i < 65; i++) {
            listItemFound.add(new Equipment(0, 0, itemIcons[i], itemName[i], itemDescription[i],listStats.get(i)));
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Overworld.getScreenShot().draw(0, 0);
        inventoryPic.draw(0, 0);
        g.setColor(Color.black);
//        g.setFont(ufont);
        int y = 350, x = 200;
        for (int i = 0; i < listItemFound.size(); i++) {
            if (i % 16 == 0) {
                x = 200;
            } else {
                x = 200 + (40 * ((i - (i / 16) * 16)));
            }

            if (i % 16 == 0 && i != 0) {
                y = 350 + ((i / 16) * 44);
            }
            listItemFound.get(i).setInventoryX(x);
            listItemFound.get(i).setInventoryY(y);
            listItemFound.get(i).getIcon().draw(listItemFound.get(i).getInventoryX(), listItemFound.get(i).getInventoryY());

            if (listItemFound.get(i).getIsAbove()) {
                g.drawString(listItemFound.get(i).getName(), 415, 200);
                g.drawString(listItemFound.get(i).getDescription(), 435, 220);
                g.drawString("Vie-" + listItemFound.get(i).getHpMax(), 405, 310);
                g.drawString("Attack-" + listItemFound.get(i).getAttack(), 490, 310);
                g.drawString("Defense-" + listItemFound.get(i).getDefence(), 610, 310);
                g.drawString("Vitesse-" + listItemFound.get(i).getSpeed(), 735, 310);
            }
        }

        for (Equipment playerItem : listItemPlayer) {
            playerItem.getIcon().draw(playerItem.getInventoryX(), playerItem.getInventoryY());
            if (playerItem.getIsAbove()) {
                g.drawString(playerItem.getName(), 415, 200);
                g.drawString(playerItem.getDescription(), 435, 220);
                g.drawString("Vie-" + playerItem.getHpMax(), 405, 310);
                g.drawString("Attack-" + playerItem.getAttack(), 490, 310);
                g.drawString("Defense-" + playerItem.getDefence(), 615, 310);
                g.drawString("Vitesse-" + playerItem.getSpeed(), 735, 310);
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

        for (int j = 0; j < listItemFound.size(); j++) {
            if ((mouseX > listItemFound.get(j).getInventoryX() && mouseX < listItemFound.get(j).getInventoryX() + 40)
                    && (mouseY < (700 - listItemFound.get(j).getInventoryY()) && mouseY > (700 - listItemFound.get(j).getInventoryY() - 50))) { //verifie si la souris est au dessus de l'item
                try {
                    listItemFound.get(lastPosition).setIsAbove(false); //set le dernier item selectionne a false
                } catch (ArrayIndexOutOfBoundsException e) {
                } catch (IndexOutOfBoundsException e) {
                }
                lastPosition = j;
                if (gc.getInput().isMousePressed(0)) {  //si l'item est clique -> false
                    if (listItemPlayer.size() < MAX_LIST_JOUEUR) {
                        listItemFound.get(j).setIsAbove(false);
                        listItemPlayer.add(listItemFound.remove(j));
                    }
                } else {
                    listItemFound.get(j).setIsAbove(true); //si l'item n'est pas clique -> true
                }
            } else {
                listItemFound.get(j).setIsAbove(false); //met tous les items non-selectionne a false
            }
        }
        for (int j = 0; j < listItemPlayer.size(); j++) { //changer la position des objets qui viennent d'etre deplace
            x = 430 + (40 * j);
            y = 155;
            try {
                listItemPlayer.get(j).setInventoryX(x);
                listItemPlayer.get(j).setInventoryY(y);
            } catch (IndexOutOfBoundsException e) {
            }
        }

        for (int j = 0; j < listItemPlayer.size(); j++) {
            if ((mouseX > listItemPlayer.get(j).getInventoryX() && mouseX < listItemPlayer.get(j).getInventoryX() + 40)
                    && (mouseY < (700 - listItemPlayer.get(j).getInventoryY()) && mouseY > (700 - listItemPlayer.get(j).getInventoryY() - 50))) { //verifie si la souris est au dessus de l'item
                try {
                    listItemPlayer.get(lastPosition).setIsAbove(false); //set le dernier item selectionne a false
                } catch (ArrayIndexOutOfBoundsException e) {
                } catch (IndexOutOfBoundsException e) {
                }
                lastPosition = j;
                if (gc.getInput().isMousePressed(0)) { //si l'item est clique -> false
                    listItemPlayer.get(j).setIsAbove(false);
                    listItemFound.add(listItemPlayer.remove(j));
                    for (int k = j; k < listItemPlayer.size(); k++) {
                        listItemPlayer.get(k).setInventoryX(listItemPlayer.get(k).getInventoryX() - 40);
                    }
                } else {
                    listItemPlayer.get(j).setIsAbove(true);  //si l'item n'est pas clique -> true
                }
            } else {
                listItemPlayer.get(j).setIsAbove(false); //met tous les items non-selectionne a false
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
    
    public static ArrayList<Equipment> getListItemPlayer(){
        return listItemPlayer;
    }

}
