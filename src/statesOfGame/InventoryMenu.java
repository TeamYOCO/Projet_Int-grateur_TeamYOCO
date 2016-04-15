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
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import playerEngine.PlayerGameManager;

/**
 *
 * @author Hassaoui
 */
public class InventoryMenu extends BasicGameState {

    private PlayerGameManager manager;
    private static int stateID;
    private Image inventoryPic;
    private Animation itemIcons[];
    private ArrayList<Equipment> listItemFound, listItemPlayer;
    private ArrayList<int[]> listItemFoundLocation;
    private boolean playerSelected = false;
    private int lastPosition = -1;
    private final int MAX_LIST_JOUEUR = 10,SPRITE_SHEET_HEIGHT = 8, SPRITE_SHEET_WIDTH = 12;

    public InventoryMenu(int stateID, PlayerGameManager manager) throws SlickException {
        this.itemIcons = new Animation[144];
        InventoryMenu.stateID = stateID;
        this.manager = manager;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        listItemFound = new ArrayList();
        listItemFoundLocation = new ArrayList();
        listItemPlayer = new ArrayList();
        int compt = 0;
        inventoryPic = new Image("res/pictures/inventory1player.png");
        SpriteSheet moveSpriteSheet = new SpriteSheet("res/sprites/weapons.png", 32, 32);
        for (int j = 0; j < SPRITE_SHEET_HEIGHT; j++) {
            for (int i = 0; i < SPRITE_SHEET_WIDTH; i++) {
                itemIcons[compt] = loadAnimation(moveSpriteSheet, i, (i + 1), j);
                compt += 1;
            }
        }
        for (int i = 0; i < 32; i++) {
            listItemFound.add(new Equipment(0, 0, itemIcons[i]));
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Overworld.getScreenShot().draw(0, 0);
        inventoryPic.draw(0, 0);
        int y = 350, x = 200;
        for (int i = 0; i < listItemFound.size(); i++) {
            if (i % 16 == 0) {
                x = 200;
            } else {
                x = 200 + (40 * ((i - (i / 16) * 16)));
            }

            if (i % 16 == 0 && i != 0) {
                y = 350 + ((i / 16) * 50);
            }
            listItemFound.get(i).setInventoryX(x);
            listItemFound.get(i).setInventoryY(y);
            listItemFound.get(i).getIcon().draw(listItemFound.get(i).getInventoryX(), listItemFound.get(i).getInventoryY());
            
            if (listItemFound.get(i).getIsAbove()) {
                g.drawString("working", 450 + (10 * i), 250);
            }
        }
        
        for (Equipment playerItem : listItemPlayer) {
            playerItem.getIcon().draw(playerItem.getInventoryX(), playerItem.getInventoryY());
            if (playerItem.getIsAbove()) {
                g.drawString("working", 450, 250);
            }
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();
        int x, y;

        if (gc.getInput().isKeyPressed(23)) {
            sbg.enterState(Game.OVERWORLD);
        }

        for (int j = 0; j < listItemFound.size(); j++) {
            if ((mouseX > listItemFound.get(j).getInventoryX() && mouseX < listItemFound.get(j).getInventoryX() + 40)
                    && (mouseY < (700 - listItemFound.get(j).getInventoryY()) && mouseY > (700 - listItemFound.get(j).getInventoryY() - 50))) { //verifie si la souris est au dessus de l'item
                try{
                listItemFound.get(lastPosition).setIsAbove(false); //set le dernier item selectionne a false
                } catch (ArrayIndexOutOfBoundsException e){
                } catch (IndexOutOfBoundsException e){
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
            x = 432 + (40 * j);
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
                try{
                listItemPlayer.get(lastPosition).setIsAbove(false); //set le dernier item selectionne a false
                } catch (ArrayIndexOutOfBoundsException e){
                } catch (IndexOutOfBoundsException e){
                }
                lastPosition = j;
                if (gc.getInput().isMousePressed(0)) { //si l'item est clique -> false
                    listItemPlayer.get(j).setIsAbove(false);
                    listItemFound.add(listItemPlayer.remove(j));
                    for (int k = j; k < listItemPlayer.size(); k++) {
                        listItemPlayer.get(k).setInventoryX(listItemPlayer.get(k).getInventoryX() - 40);
                    }
                }  else {
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

}
