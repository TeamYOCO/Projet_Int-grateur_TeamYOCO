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
    private ArrayList<Animation> listItemFound, listItemPlayer, listItemFriend1, listItemFriend2;
    private ArrayList<int[]> listItemFoundLocation;
    private boolean firstClick = true, playerSelected = false, friend1Selected = false, friend2Selected = false;
    int[] itemLocation = new int[2];

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
        listItemFriend1 = new ArrayList();
        listItemFriend2 = new ArrayList();
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
            listItemFound.add(itemIcons[i]);
        }
    }

    @Override
    @SuppressWarnings("empty-statement")
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
            listItemFound.get(i).draw(x, y);
            itemLocation[0] = x;
            itemLocation[1] = y;
            listItemFoundLocation.add(0,itemLocation);
            System.out.println(listItemFoundLocation.size());
        }

        for (int i = 0; i < 6; i++) {
            x = 415 + (40 * i);
            try {
                listItemPlayer.get(i).draw(x, 150);
            } catch (IndexOutOfBoundsException e) {}
            try {
                listItemFriend1.get(i).draw(x, 225);
            } catch (IndexOutOfBoundsException e) {}
            try {
                listItemFriend2.get(i).draw(x, 300);
            } catch (IndexOutOfBoundsException e) {}
        }
        
        if (playerSelected) {
            g.drawRect(368, 148, 40, 45);
        } else if (friend1Selected){
            g.drawRect(368, 213, 40, 45);
        } else if (friend2Selected){
            g.drawRect(368, 278, 40, 45);
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();

        if (gc.getInput().isKeyPressed(23)) {
            sbg.enterState(Game.OVERWORLD);
        }

        if ((mouseX < 408) && (mouseX > 368) && (mouseY > 511 && mouseY < 556) && (gc.getInput().isMousePressed(0))) {
            if (!playerSelected) {
                playerSelected = true;
                friend1Selected = false;
                friend2Selected = false;
            } else if (playerSelected) {
                playerSelected = false;
            }
        }
        
        if ((mouseX < 408) && (mouseX > 368) && (mouseY > 451 && mouseY < 496) && (gc.getInput().isMousePressed(0))) {
            if (!friend1Selected) {
                playerSelected = false;
                friend1Selected = true;
                friend2Selected = false;
                firstClick = false;
            } else if (friend1Selected) {
                friend1Selected = false;
            }
        }
        
        if ((mouseX < 408) && (mouseX > 368) && (mouseY > 376 && mouseY < 421) && (gc.getInput().isMousePressed(0))) {
            if (!friend2Selected) {
                playerSelected = false;
                friend1Selected = false;
                friend2Selected = true;
            } else if (friend2Selected) {
                friend2Selected = false;
            }
        }

        if (playerSelected || friend1Selected || friend2Selected) {
            for (int j = 0; j < listItemFound.size(); j++) {
                if ((mouseX > listItemFoundLocation.get(i)[0] && mouseX < listItemFoundLocation.get(i)[0] + 40) && (mouseY > (704 - listItemFoundLocation.get(i)[1]) && mouseY < (704 - listItemFoundLocation.get(i)[1] - 50))) {
                    if (gc.getInput().isMousePressed(0)) {
                        if(playerSelected) listItemPlayer.add(listItemFound.remove(i));
                        else if(friend1Selected) listItemFriend1.add(listItemFound.remove(i));
                        else listItemFriend2.add(listItemFound.remove(i));
                    }
                }
            }
        }
    }

    private void addItems(ArrayList<Animation> list) {

    }

    protected Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

}
