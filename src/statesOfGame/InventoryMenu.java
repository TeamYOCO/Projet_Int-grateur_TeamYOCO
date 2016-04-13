/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
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
    private ArrayList<iItem> listItemFound, listItemPlayer;
    private ArrayList<int[]> listItemFoundLocation;
    private boolean playerSelected = false;

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
        inventoryPic = new Image("res/pictures/inventory.png");
        SpriteSheet moveSpriteSheet = new SpriteSheet("res/sprites/items1_0.png", 32, 32);
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 16; i++) {
                itemIcons[compt] = loadAnimation(moveSpriteSheet, i, (i + 1), j);
                compt += 1;
            }
        }
        for (int i = 0; i < 32; i++) {
            listItemFound.add(new iItem(0, 0, itemIcons[i]));
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
            listItemFound.get(i).getIcon().draw(x, y);
            listItemFound.get(i).setX(x);
            listItemFound.get(i).setY(y);
        }

        for (int i = 0; i < listItemPlayer.size(); i++) {
            x = 432 + (40 * i);
            y = 155;
            try {
                listItemPlayer.get(i).getIcon().draw(x, y);
                listItemPlayer.get(i).setX(x);
                listItemPlayer.get(i).setY(y);
            } catch (IndexOutOfBoundsException e) {
            }
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();

        if (gc.getInput().isKeyPressed(23)) {
            sbg.enterState(Game.OVERWORLD);
        }

        if (gc.getInput().isMousePressed(0)) {
            for (int j = 0; j < listItemFound.size(); j++) {
                if ((mouseX > listItemFound.get(j).getX() && mouseX < listItemFound.get(j).getX() + 40) && (mouseY < (700 - listItemFound.get(j).getY()) && mouseY > (700 - listItemFound.get(j).getY() - 50))) {
                    if(listItemPlayer.size() < 10) listItemPlayer.add(listItemFound.remove(j));
                }
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

    private class iItem {

        private int x, y;
        Animation itemIcon;

        private iItem(int x, int y, Animation itemIcon) {
            this.x = x;
            this.y = y;
            this.itemIcon = itemIcon;
        }

        private int getX() {
            return x;
        }

        private int getY() {
            return y;
        }

        private Animation getIcon() {
            return itemIcon;
        }

        private void setX(int x) {
            this.x = x;
        }

        private void setY(int y) {
            this.y = y;
        }
    }

}
