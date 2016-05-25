package gameEngine;

import entities.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import playerEngine.CharacterStatsManager;

/*
 * Cette classe gère le controle du joueur dans le overworld
 * @author Seb
 */

/**
 *
 * @author Tomas
 */

public class PlayerController implements KeyListener {

    // Variables de la classe
    private Player player;

    // Constructeur

    /**
     *
     * @param player
     */
        public PlayerController(Player player) {
        this.player = player;
    }

    /**
     *
     * @param input
     */
    @Override
    public void setInput(Input input) {

    }

    /**
     *
     * @return
     */
    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    /**
     *
     */
    @Override
    public void inputEnded() {

    }

    /**
     *
     */
    @Override
    public void inputStarted() {

    }

    /**
     *
     * @param key
     * @param c
     */
    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_UP:
                this.player.setDirection(0);
                this.player.setMoving(true);
                break;
            case Input.KEY_LEFT:
                this.player.setDirection(1);
                this.player.setMoving(true);
                break;
            case Input.KEY_DOWN:
                this.player.setDirection(2);
                this.player.setMoving(true);
                break;
            case Input.KEY_RIGHT:
                this.player.setDirection(3);
                this.player.setMoving(true);
                break;
            case Input.KEY_D:
                try {
                    this.player.attack();
                } catch (SlickException ex) {
                }
                break;
            case Input.KEY_S:
                try {
                    this.player.shoot();
                } catch (SlickException ex) {
                }
                break;
            case Input.KEY_A:
                try {
                    this.player.cast();
                } catch (SlickException ex) {
                }
                break;
            case Input.KEY_3:
                System.out.println(player.getX() + " " + player.getY());
                break;
        }
    }

    /**
     *
     * @param key
     * @param c
     */
    @Override
    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_UP:
                if (player.getDirection() == 0) {
                    this.player.setMoving(false);
                }
                break;
            case Input.KEY_LEFT:
                if (player.getDirection() == 1) {
                    this.player.setMoving(false);
                }
                break;
            case Input.KEY_DOWN:
                if (player.getDirection() == 2) {
                    this.player.setMoving(false);
                }
                break;
            case Input.KEY_RIGHT:
                if (player.getDirection() == 3) {
                    this.player.setMoving(false);
                }
                break;
        }
    }

}
