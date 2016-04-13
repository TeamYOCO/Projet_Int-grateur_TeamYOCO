/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<Animation> listItemFound, listItemPlayer;
    private ArrayList<int[]> listItemFoundLocation;
    private boolean playerSelected = false;
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
            if (listItemFoundLocation.size() < listItemFound.size()) {
                listItemFoundLocation.add(itemLocation);
            } else {
                listItemFoundLocation.set(i, itemLocation);
            }
                
        }

        for (int i = 0; i < 6; i++) {
            x = 415 + (40 * i);
            try {
                listItemPlayer.get(i).draw(x, 150);
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

        try {
            for (int j = 0; j < listItemFound.size(); j++) {
//            System.out.println(j + "     " + Arrays.toString(listItemFoundLocation.get(j)));
                if ((mouseX > listItemFoundLocation.get(j)[0] && mouseX < listItemFoundLocation.get(j)[0] + 40) && (mouseY > (704 - listItemFoundLocation.get(j)[1]) && mouseY < (704 - listItemFoundLocation.get(j)[1] - 50))) {
                    System.out.println("sup");
                    if (gc.getInput().isMousePressed(0)) {
                        listItemPlayer.add(listItemFound.remove(i));
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("wtf");
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
