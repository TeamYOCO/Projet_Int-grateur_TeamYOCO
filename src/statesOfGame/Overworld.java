/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import static ca.qc.bdeb.info204.Game.SHOP;
import entities.BadEntity;
import entities.Bat;
import entities.Bee;
import entities.Boss;
import entities.BigSwordSwing;
import entities.Entity;
import entities.Snake;
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
import entities.FriendlyEntity;
import entities.ItemDrop;
import entities.NPC;
import entities.NpcList;
import entities.PotionDrop;
import entities.Slime;
import gameEngine.DataManager;
import items.EquipmentList;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import org.lwjgl.input.Mouse;
import playerEngine.CharacterStatsManager;

/**
 *
 * @author 1455367
 */
public class Overworld extends BasicGameState implements Serializable {

    private Random rnd = new Random();
    private ArrayList<Entity> list = new ArrayList();
    private ArrayList<Entity> listRemove = new ArrayList();
    private static int stateID;
    private GameContainer container;
    private MiniMap map = new MiniMap();
    private Player player = new Player(map, list);
    private PlayerController controller = new PlayerController(player);
    private Camera cam = new Camera(player, map);
    private Hud hud = new Hud();
    private boolean running = false, firstTime, gameSaved = false;
    private static boolean newGame, gameOver = false;
    private static Image screenShot;
    private Music overworldMusic;
    private boolean mapChanger = false;
    private String oldMusic = "res/musics/006-link-s-house.WAV";
    private String overworldTheme = "res/musics/006-link-s-house.WAV";
    private int savedGameCompteur = 0;

    /**
     *
     * @param stateID
     */
    public Overworld(int stateID) {
        Overworld.stateID = stateID;
    }

    /**
     *
     * @return
     */
    @Override
    public int getID() {
        return stateID;
    }

    /**
     *
     * @param container
     * @param sbg
     * @throws SlickException
     */
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
        list.add(new PotionDrop(200, 200, null));
        list.add(NpcList.getNpc("Marchand", 323, 471));
        firstTime = true;
    }

    /**
     *
     * @param gc
     * @param sbg
     * @param g
     * @throws SlickException
     */
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

        if (CharacterStatsManager.getInstance().getlvlIsUp()) {
            container.getGraphics().copyArea(screenShot, 0, 0);
            sbg.enterState(Game.LEVELUPSCREEN);
            CharacterStatsManager.getInstance().setlvlIsUp(false);
        }

        if (gameSaved) {
            g.setColor(Color.white);
            g.drawString("Partie Sauvegardée", 800, 10);
        }

    }

    // méthode qui est passé chaque fois dans le thread du jeu
    /**
     *
     * @param gc
     * @param sbg
     * @param delta
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = container.getInput();
        try {
            this.player.update(delta); // Update le joueur en fonction du temps passé depuis la dernière update
            for (Entity entity : list) {
                entity.update(delta); // Update l'entity en fonction du temps passé depuis la dernière update
                for (Entity entity2 : list) {
                    if (entity instanceof BadEntity
                            && entity2 instanceof FriendlyEntity
                            && entity.getHitBox().collision(entity2.getHitBox())
                            && ((BadEntity) entity).isHitable()) { // détecte la collision entre un enemi et une attaque du joueur
                        ((BadEntity) entity).takeHit(((FriendlyEntity) entity2).getDamagePhysical(), ((FriendlyEntity) entity2).getDamageSpecial(), ((FriendlyEntity) entity2).getDirection());
//                    list.add(new DamageMarker(entity.getX(), entity.getY(), ((FriendlyEntity)entity2).getDamage()));
                    }
                }
                if (entity instanceof BadEntity
                        && ((BadEntity) entity).isHitable() && player.isHitable()
                        && player.getHitBox().collision(entity.getHitBox())) { // Détecte la collision entre le joueur et une attaque enemie
                    player.takeHit(((BadEntity) entity).getDamagePhysical(), ((BadEntity) entity).getDamageSpecial(), ((BadEntity) entity).getDirection());
                }
                if (entity instanceof ItemDrop
                        && player.isHitable()
                        && player.getHitBox().collision(entity.getHitBox())) {
                        ((ItemDrop) entity).pickUp();
                }
                if (entity instanceof NPC
                        && player.isHitable()
                        && entity.getHitBox().collision(player.getHitBox())
                        && input.isKeyPressed(Input.KEY_SPACE)) {
                    container.getGraphics().copyArea(screenShot, 0, 0);
                    ((NPC) entity).interact(sbg);
                }
                if (entity.isDead()) { // Retire l'entité du jeu si elle est morte
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
            if (mapChanger) {
                String[] temp;
                temp = map.getMapProperty("ennemy").split(";");
                String[] temp2;
                for (int i = 1; i <= Integer.parseInt(temp[0]); i++) {
                    temp2 = temp[i].split(",");
                    if (Integer.parseInt(temp2[0]) == 1) {
                        this.list.add(new Bee(Integer.parseInt(temp2[1]), Integer.parseInt(temp2[2]), player, map, list));
                    } else if (Integer.parseInt(temp2[0]) == 2) {
                        this.list.add(new Slime(Integer.parseInt(temp2[1]), Integer.parseInt(temp2[2]), player, map, list));
                    } else if (Integer.parseInt(temp2[0]) == 3) {
                        this.list.add(new Bat(Integer.parseInt(temp2[1]), Integer.parseInt(temp2[2]), player, map, list));
                    } else if (Integer.parseInt(temp2[0]) == 4) {
                        this.list.add(new Snake(Integer.parseInt(temp2[1]), Integer.parseInt(temp2[2]), player, map, list));
                    }
                }
                String[] temp3;
                temp3 = map.getMapProperty("npc").split(";");
                String[] temp4;
                for (int i = 1; i <= Integer.parseInt(temp3[0]); i++) {
                    temp4 = temp3[i].split(",");
                    if (Integer.parseInt(temp4[0]) == 1) {
                        int x = Integer.parseInt(temp4[1]);
                        int y = Integer.parseInt(temp4[2]);
                        this.list.add(NpcList.getNpc("Princess", x, y));
                    }
                }

                mapChanger = false;
            }
            if (!oldMusic.equals(map.getMapProperty("music"))) {
                this.setMusic(map.getMapProperty("music"));
                overworldMusic = new Music(map.getMapProperty("music"));
                overworldMusic.play();
                overworldMusic.loop();
                oldMusic = map.getMapProperty("music");
            }

            //peser sur la touche 'p' pour save
            if (input.isKeyPressed(25)) {
                DataManager.getInstance().save();
                gameSaved = true;
                savedGameCompteur = 1000;
            }
            if (savedGameCompteur > 0) {
                savedGameCompteur -= delta;
                if (savedGameCompteur <= 0) {
                    gameSaved = false;
                }
            }
            if (input.isMousePressed(0)) {
                System.out.println(input.getMouseX() + " " + input.getMouseY());
            }

            if (input.isKeyPressed(Input.KEY_0)) {
                container.getGraphics().copyArea(screenShot, 0, 0);
                sbg.enterState(Game.SHOP);
            }

            if (input.isKeyPressed(Input.KEY_9)) {
                container.getGraphics().copyArea(screenShot, 0, 0);
                sbg.enterState(Game.DIALOG);
            }

            if (gameOver) {
                sbg.enterState(Game.GAMEOVER);
                overworldMusic.stop();
                gameOver = false;
                CharacterStatsManager.getInstance().reset();
                MiniMap.changeMap("res/maps/Maison_1.tmx");
                list.clear();
                player.setX(64);
                player.setY(256);
            }
        } catch (ConcurrentModificationException e) {
        }
    }

    /**
     *
     * @param gc
     * @param sbg
     * @throws SlickException
     */
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        if (firstTime) {
            overworldMusic.play();
            overworldMusic.loop();
            firstTime = false;

            if (!newGame) {
                DataManager.getInstance().load();
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
            oldMusic = this.overworldTheme;
            MiniMap.changeMap(newMap);
            mapChanger = true;
        }
        this.list.clear();
        this.listRemove.clear();
    }

    /**
     *
     * @return
     */
    public static Image getScreenShot() {
        return screenShot;
    }

    /**
     *
     * @param musicPath
     */
    public void setMusic(String musicPath) {
        overworldTheme = musicPath;
    }

    /**
     *
     * @param newG
     */
    public static void setNewGame(boolean newG) {
        newGame = newG;
    }

    /**
     *
     * @param newG
     */
    public static void setGameOver(boolean gg) {
        gameOver = gg;
    }

}
