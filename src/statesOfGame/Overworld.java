/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import gameEngine.Model;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author 1455367
 */
public class Overworld extends BasicGameState {

    private static int stateID;
    private static Model model;
    private GameContainer container;
    private TiledMap map;
    private Animation[] animations = new Animation[8];

    public Overworld(int stateID, Model model) {
        Overworld.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.container = gc;
        this.map = new TiledMap("res/maps/map_1-1.tmx");

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        this.map.render(0, 0);
        grphcs.drawAnimation(model.getPlayer().getAnimation()[model.getPlayer().getDirection() + (model.getPlayer().getMoving() ? 4 : 0)], (float) model.getPlayer().getX() - 32, (float) model.getPlayer().getY() - 60);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
            model.getPlayer().setMoving(true);
            model.getPlayer().setDirection(2);
        }
        if (gc.getInput().isKeyDown(Input.KEY_UP)) {
            model.getPlayer().setMoving(true);
            model.getPlayer().setDirection(1);
        }
        if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
            model.getPlayer().setMoving(true);
            model.getPlayer().setDirection(0);
        }
        if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            model.getPlayer().setMoving(true);
            model.getPlayer().setDirection(3);
        }
    }
}
