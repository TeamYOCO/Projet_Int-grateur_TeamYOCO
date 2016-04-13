/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import entities.OverworldEntity;
import entities.OverworldPlayer;
import gameEngine.Camera;
import gameEngine.PlayerController;
import java.util.ArrayList;
import java.util.Random;
import maps.MiniMap;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeOutTransition;
import playerEngine.PlayerGameManager;

/**
 *
 * @author 1455367
 */
public class Overworld extends BasicGameState {

    private Random rnd = new Random();
    private PlayerGameManager manager;
    private ArrayList<OverworldEntity> list = new ArrayList();
    private ArrayList<OverworldEntity> listRemove = new ArrayList();
    private static int stateID;
    private GameContainer container;
    private MiniMap map = new MiniMap();
    private OverworldPlayer player = new OverworldPlayer(map, list);
    private PlayerController controller = new PlayerController(player);
    private Camera cam = new Camera(player, map);
    private boolean running = false, firstTime;
    private static Image screenShot;
    private Music overworldMusic;
    private String overworldTheme = "res/musics/006-link-s-house.WAV";

    public Overworld(int stateID, PlayerGameManager manager) {
        Overworld.stateID = stateID;
        this.manager = manager;
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
        screenShot = new Image(container.getWidth(), container.getHeight());
        overworldMusic = new Music(overworldTheme);
        firstTime = true;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        this.cam.place(container, g);
        this.map.renderBackground(g);
        this.player.render(g);
        for (OverworldEntity entity : list) {
            entity.render(g);
        }
        this.map.renderForeground();

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = container.getInput();
        detectFight();
        this.player.update(delta);
        for (OverworldEntity entity : list) {
            entity.update(delta);
            if (entity.isDead()) {
                listRemove.add(entity);
            }
        }
        for (OverworldEntity entity : listRemove) {
            list.remove(entity);
        }
        listRemove.clear();
        this.cam.update(container);
        updateTrigger();

        if (!player.getMoving() && rnd.nextInt(5000) == 0){
            sbg.enterState(Game.COMBATSCREEN, new FadeOutTransition(), new FadeOutTransition());
        }
        
        if (input.isKeyPressed(23)) { // touche 23 = 'i'
            container.getGraphics().copyArea(screenShot, 0, 0); // le contenu graphique du container est placé dans l'image "screenshot"
            sbg.enterState(Game.INVENTORY);
        }
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
        if (firstTime) {
            overworldMusic.play();
            overworldMusic.loop();
            firstTime = false;
        }
    }

    private void updateTrigger() throws SlickException {
        for (int objectID = 0; objectID < this.map.getTiledMap().getObjectCount(0); objectID++) {
            if (isInTrigger(objectID)) {
                if ("tp".equals(this.map.getTiledMap().getObjectType(0, objectID))) {
                    changeMap(objectID);
                }
            }
        }
    }

    private boolean isInTrigger(int id) {
        return player.getX() > this.map.getTiledMap().getObjectX(0, id)
                && player.getX() < this.map.getTiledMap().getObjectX(0, id) + this.map.getTiledMap().getObjectWidth(0, id)
                && player.getY() > this.map.getTiledMap().getObjectY(0, id)
                && player.getY() < this.map.getTiledMap().getObjectY(0, id) + this.map.getTiledMap().getObjectHeight(0, id);
    }

    private void changeMap(int objectID) throws SlickException {
        player.setX(Float.parseFloat(this.map.getTiledMap().getObjectProperty(0, objectID, "destX", Float.toString(player.getX()))));
        player.setY(Float.parseFloat(this.map.getTiledMap().getObjectProperty(0, objectID, "destY", Float.toString(player.getY()))));
        String newMap = this.map.getTiledMap().getObjectProperty(0, objectID, "destMap", "undefined");
        if (!"undefined".equals(newMap)) {
            this.map.changeMap(newMap);
        }
    }

    public static Image getScreenShot() {
        return screenShot;
    }
    
    public void setMusic(String musicPath){
        overworldTheme = musicPath;
    }
    
    public void detectFight(){
        
        if (player.getMoving() && rnd.nextInt(500)==1)
            System.out.println("Encounter");
    }
}
