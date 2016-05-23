/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import entities.BadEntity;
import entities.Bee;
import entities.DamageMarker;
import entities.Entity;
import entities.Mob;
import entities.Player;
import gameEngine.Camera;
import gameEngine.Hud;
import gameEngine.PlayerController;
import java.util.ArrayList;
import java.util.Random;
import maps.MiniMap;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import playerEngine.PlayerGameManager;
import entities.FriendlyEntity;
import items.Equipment;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author 1455367
 */
public class Overworld extends BasicGameState implements Serializable{

    private Random rnd = new Random();
    private PlayerGameManager manager;
    private ArrayList<Entity> list = new ArrayList();
    private ArrayList<Entity> listRemove = new ArrayList();
    private static int stateID;
    private GameContainer container;
    private MiniMap map = new MiniMap();
    private Player player = new Player(map, list);
    private PlayerController controller = new PlayerController(player);
    private Camera cam = new Camera(player, map);
    private Hud hud = new Hud();
    private boolean running = false, firstTime;
    private static boolean newGame;
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
        this.hud.init();
        container.getInput().addKeyListener(controller);
        screenShot = new Image(container.getWidth(), container.getHeight());
        overworldMusic = new Music(overworldTheme);
        firstTime = true;
        this.list.add(new Bee(true, 725, 178, player, map)); //725.6064 178.80164
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        this.cam.place(container, g);
        this.map.renderBackground(g);
        this.player.render(g);
        for (Entity entity : list) {
            entity.render(g);
        }
        this.map.renderForeground();
        this.hud.render(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = container.getInput();
        this.player.update(delta);
        for (Entity entity : list) {
            entity.update(delta);
            for(Entity entity2 : list){
                if (entity != entity2 && entity instanceof BadEntity 
                        && entity2 instanceof FriendlyEntity 
                        && entity.getHitBox().collision(entity2.getHitBox())
                        && ((BadEntity)entity).isHitable()){
                    ((BadEntity)entity).takeHit(((FriendlyEntity)entity2).getDamage(), ((FriendlyEntity)entity2).getDirection());
//                    list.add(new DamageMarker(entity.getX(), entity.getY(), ((FriendlyEntity)entity2).getDamage()));
                }
            }
            if (entity instanceof BadEntity
                    && ((BadEntity)entity).isHitable() && player.isHitable()
                    && player.getHitBox().collision(entity.getHitBox())){
                player.takeHit(((BadEntity)entity).getDamage(), ((BadEntity)entity).getDirection());
                System.out.println("il take "+((BadEntity)entity).getDamage());
            }
            if (entity.isDead()) {
                listRemove.add(entity);
            }
        }
        for (Entity entity : listRemove) {
            list.remove(entity);
        }
        listRemove.clear();
        this.cam.update(container);
        updateTrigger();

        if (input.isKeyPressed(18) || input.isMousePressed(1)) { // entrer dans le menu inventaire en pesant sur 'e' ou en clickant sur le bouton droit de la souris
            container.getGraphics().copyArea(screenShot, 0, 0); // le contenu graphique du container est placé dans l'image "screenshot"
            sbg.enterState(Game.INVENTORY);
        }
        
        //peser sur la touche 'p' pour save
        if(input.isKeyPressed(25)){
            save();
        }
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        if (firstTime) {
            overworldMusic.play();
            overworldMusic.loop();
            firstTime = false;
            
            if(!newGame){
                this.load();
            }
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
        this.list.clear();
        this.listRemove.clear();
    }

    public static Image getScreenShot() {
        return screenShot;
    }

    public void setMusic(String musicPath) {
        overworldTheme = musicPath;
    }
    
    public static void setNewGame(boolean newG){
        newGame = newG;
    }
    
    public void save() throws SlickException{
        try {
            ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("save.dat"));
            save.writeObject((ArrayList<Equipment>)manager.getInventory().getListItemFound());
            save.writeObject((ArrayList<Equipment>)manager.getInventory().getListItemPlayer());
            for (int i = 0; i < Equipment.MAX_STATS; i++) {
                save.writeInt(CharacterStatsManager.getInstance().getStats()[i]);
            }
            save.writeInt(CharacterStatsManager.getInstance().getHp());
            save.flush();
            save.close();
        } catch (IOException ex) {
            System.out.println("Erreur d'entrées-sorties");
        }
    }
    
    public void load() throws SlickException{
        try {
            ObjectInputStream load = new ObjectInputStream(new FileInputStream("save.dat"));
            ArrayList<Equipment> list = (ArrayList<Equipment>)load.readObject();
            manager.getInventory().setListItemFound(list);
            list = (ArrayList<Equipment>) load.readObject();
            manager.getInventory().setListItemPlayer(list);
            for (int i = 0; i < Equipment.MAX_STATS; i++) {
                CharacterStatsManager.getInstance().setStats(i, load.readInt());
            }
            CharacterStatsManager.getInstance().setHp(load.readInt());
            load.close();
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier");
        } catch (ClassNotFoundException e) {
            System.out.println("Fichier introuvable");
        }
    }

}
