/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import entities.Entity;
import entities.Player;
import gameEngine.Camera;
import gameEngine.PlayerController;
import java.util.ArrayList;
import maps.MiniMap;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tests.TileMapTest;

/**
 *
 * @author 1455367
 */
public class Overworld extends BasicGameState {

    private static int stateID;
    private GameContainer container;
    private MiniMap map = new MiniMap();
    private Player player = new Player(map);
    private PlayerController controller = new PlayerController(player);
    private Camera cam = new Camera(player, map);
    private boolean running = false;
    private ArrayList<Entity> list = new ArrayList();
    private ArrayList<Entity> listRemove = new ArrayList();

    public Overworld(int stateID) {
        Overworld.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        this.container = container;
        this.map.init();
        this.player.init();
        this.controller.setInput(container.getInput());
        container.getInput().addKeyListener(controller);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        this.cam.place(container, g);
        this.map.renderBackground();
        this.player.render(g);
        this.map.renderForeground();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        this.player.update(delta);
        this.cam.update(container);
    }
}
