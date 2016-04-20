/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
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
        Font font = new Font("Serif", Font.BOLD, 15);
        ufont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
        ufont.addAsciiGlyphs();
        ufont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        ufont.loadGlyphs();

        this.itemName = new String[]{"Casque Antique", "Casque Metallique", "Casque d'Argent", "Casque Emeraude", "Casque Royal",
            "Armure Antique", "Armure Metallique", "Armure d'Argent", "Armure Emeraude", "Armure Royale",
            "Jambieres Antiques", "Jambieres Metalliques", "Jambieres d'Argent", "Jambieres Emeraudes", "Jambieres Royales",
            "Bottes Antiques", "Bottes Metalliques", "Bottes d'Argent", "Bottes Emeraudes", "Bottes Royales",
            "Bouclier Serpent", "Bouclier de bois", "Bouclier legendaire", "Bouclier simple", "Jesus",
            "Epee de bois", "Epee rudimentaire", "Epee metallique", "Epee d'argent", "Epee Royale",
            "Banane", "Epee legendaire", "Epee de glace", "Epee rose", "Epee extraterrestre",
            "Hache de bois", "Hache rudimentaire", "Hache metallique", "Hache d'argent", "Hache Royale",
            "Hache lourde", "Hache legere", "Hache de feu", "Hache sinistre", "Hache artisanale",
            "Sceptre clair", "Sceptre offensif", "Tri-dent magique", "Sceptre etrangleur", "Large Sceptre",
            "Sceptre clair 2.0", "Sceptre metallique", "Sceptre jaune", "Cane de Noel", "Sceptre precieux",
            "Arc simple", "Arc elegant", "Arc lourd", "Arc sombre", "Arc clair",
            "Arc infernal", "Arc de feu", "Arc angelique", "Arc metallique", "Arc artisanal"};
        this.itemDescription = new String[]{
/*Casque Antique*/            "Casque ayant appartenu à un soldat mort durant \nsa premiere bataille il y a plusieurs siecles.  \nOffre une petite augmentation de la vie \nmaximale",
/*Casque Metallique*/         "Casque en métal moderne. Procure une bonne \nOffre une bonne augmentation de la vie maximale",
/*Casque d'Argent*/           "A appartenu a un général emerite. Offre une \ntrès bonne augmentation de la vie maximale",
/*Casque Emeraude*/           "Casque forgé par les plus grands forgerons du \npays, il est incruster d'emeraude, augmentant \nla vie maximale",
/*Casque Royal*/              "Casque dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Augmentation vitale maximale",
/*Armure Antique*/            "Armure ayant appartenu à un soldat mort durant \nsa premiere bataille il y a plusieurs siecles.  \nProcure une protection minimale contre les \nattaques physiques",
/*Armure Metallique*/         "Armure en métal moderne. Procure une bonne \nprotection contre les attaques physiques",
/*Armure d'Argent*/           "A appartenu a un général emerite. Procure une \ntres bonne protection contre les attaques \nphysiques",
/*Armure Emeraude*/           "Armure forgée par les plus grands forgerons du \npays, il est incruster d'emeraude, augmentant \nsa résistance contre les attaques physiques",
/*Armure Royale*/             "Armure dont l'ancien proprietaire est inconnu, \nmais on croit qu'il s'agirait d'un roi tres \npuissant.  Protection maximale",
/*Jambieres Antiques*/        "Jambieres ayant appartenu à un soldat mort \ndurant sa premiere bataille il y a plusieurs siecles.  \nProcure une protection minimale contre les \nattaques physiques",
/*Jambieres Metalliques*/     "",
/*Jambieres d'Argent*/            "",
/*Jambieres Emeraudes*/            "",
/*Jambieres Royales*/            "",
/*Bottes Antiques*/            "",
/*Bottes Metalliques*/            "",
/*Bottes d'Argent*/            "",
/*Bottes Emeraudes*/            "",
/*Bottes Royales*/            "",
/*Bouclier Serpent*/            "",
/*Bouclier de bois*/            "",
/*Bouclier legendaire*/            "",
/*Bouclier simple*/            "",
/*Jesus*/            "",
/*Epee de bois*/            "",
/*Epee rudimentaire*/            "",
/*Epee metallique*/            "",
/*Epee d'argent*/            "",
/*Epee Royale*/            "",
/*Banane*/            "",
/*Epee legendaire*/            "",
/*Epee de glace*/            "",
/*Epee rose*/            "",
/*Epee extraterrestre*/            "",
/*Hache de bois*/            "",
/*Hache rudimentaire*/            "",
/*Hache metallique*/            "",
/*Hache d'argent*/            "",
/*Hache Royale*/            "",
/*Hache lourde*/            "",
/*Hache legere*/            "",
/*Hache de feu*/            "",
/*Hache sinistre*/            "",
/*Hache artisanale*/            "",
/*Sceptre clair*/            "",
/*Sceptre offensif*/            "",
/*Tri-dent magique*/            "",
/*Sceptre etrangleur*/            "",
/*Large Sceptre*/            "",
/*Sceptre clair 2.0*/            "",
/*Sceptre metallique*/            "",
/*Sceptre jaune*/            "",
/*Cane de Noel*/            "",
/*Sceptre precieux*/            "",
/*Arc simple*/            "",
/*Arc elegant*/            "",
/*Arc lourd*/            "",
/*Arc sombre*/            "",
/*Arc clair*/            "",
/*Arc infernal*/            "",
/*Arc de feu*/            "",
/*Arc angelique*/            "",
/*Arc metallique*/            "",
/*Arc artisanal*/            ""};
        
/*Casque Antique*/         listStats.add(new int[]{944,944,944,944,944,944});
/*Casque Metallique*/         listStats.add(new int[]{1,1,1,1,1,1});
/*Casque d'Argent*/         listStats.add(new int[]{1,1,1,1,1,1});
/*Casque Emeraude*/         listStats.add(new int[]{1,1,1,1,1,1});
/*Casque Royal*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Armure Antique*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Armure Metallique*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Armure d'Argent*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Armure Emeraude*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Armure Royale*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Jambieres Antiques*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Jambieres Metalliques*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Jambieres d'Argent*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Jambieres Emeraudes*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Jambieres Royales*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Bottes Antiques*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Bottes Metalliques*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Bottes d'Argent*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Bottes Emeraudes*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Bottes Royales*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Bouclier Serpent*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Bouclier de bois*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Epee rudimentaire*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Epee metallique*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Epee d'argent*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Epee Royale*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Banane*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Epee legendaire*/        listStats.add(new int[]{1,1,1,1,1,1});
/*Epee de glace*/        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});
        listStats.add(new int[]{1,1,1,1,1,1});

        for (int i = 0; i < 65; i++) {
            manager.getInventory().getListItemFound().add(new Equipment(0, 0, itemIcons[i], itemName[i], itemDescription[i],listStats.get(i)));
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Overworld.getScreenShot().draw(0, 0);
        inventoryPic.draw(0, 0);
        g.setColor(Color.black);
//        g.setFont(ufont);
        int y = 350, x = 200;
        for (int i = 0; i < manager.getInventory().getListItemFound().size(); i++) {
            if (i % 16 == 0) {
                x = 200;
            } else {
                x = 200 + (40 * ((i - (i / 16) * 16)));
            }

            if (i % 16 == 0 && i != 0) {
                y = 350 + ((i / 16) * 44);
            }
            manager.getInventory().getListItemFound().get(i).setInventoryX(x);
            manager.getInventory().getListItemFound().get(i).setInventoryY(y);
            manager.getInventory().getListItemFound().get(i).getIcon().draw(manager.getInventory().getListItemFound().get(i).getInventoryX(), manager.getInventory().getListItemFound().get(i).getInventoryY());

            if (manager.getInventory().getListItemFound().get(i).getIsAbove()) {
                g.drawString(manager.getInventory().getListItemFound().get(i).getName(), 415, 200);
                g.drawString(manager.getInventory().getListItemFound().get(i).getDescription(), 435, 220);
                g.drawString("Vie-" + manager.getInventory().getListItemFound().get(i).getHpMax(), 405, 310);
                g.drawString("Att-" + manager.getInventory().getListItemFound().get(i).getAttack(), 480, 310);
                g.drawString("Def-" + manager.getInventory().getListItemFound().get(i).getDefence(), 555, 310);
                g.drawString("SpA-" + manager.getInventory().getListItemFound().get(i).getSpecialAttack(), 630, 310);
                g.drawString("SpD-" + manager.getInventory().getListItemFound().get(i).getSpecialDefence(), 705, 310);
                g.drawString("Vit-" + manager.getInventory().getListItemFound().get(i).getSpeed(), 780, 310);
            }
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
                        manager.getInventory().getListItemPlayer().add(manager.getInventory().getListItemFound().remove(j));
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
                    manager.getInventory().getListItemFound().add(manager.getInventory().getListItemPlayer().remove(j));
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
