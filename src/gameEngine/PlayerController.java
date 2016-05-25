package gameEngine;

import entities.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import playerEngine.CharacterStatsManager;

/**
 * Cette classe gère le controle du joueur dans le overworld
 * @author Seb
 */


public class PlayerController implements KeyListener {

    private Player player;

    /**
     * Constructeur des contrôle du joueur
     * @param player le joueur
     */
        public PlayerController(Player player) {
        this.player = player;
    }

    /**
     * Change l'entrée de commande
     * @param input le nouvel entrée de commande
     */
    @Override
    public void setInput(Input input) {

    }

    /**
     * retourne si le jeu accepte des entrées de commande
     * @return si le jeu accepte des entrées de commande
     */
    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    /**
     * si l'entrée de commande fini
     */
    @Override
    public void inputEnded() {

    }

    /**
     * quand l'entrée de commande commence
     */
    @Override
    public void inputStarted() {

    }

    /**
     * Ce qui se passe lorsqu'on appuit sur une touche
     * @param key La touche appuyée
     * @param c il est la parce qu'il a besoin d'être implémenté
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
            case Input.KEY_1:
                try {
                    CharacterStatsManager.getInstance().gainExp(5);
                } catch (SlickException ex) {
                }
                break;
            case Input.KEY_2:
        {
            try {
                System.out.println(CharacterStatsManager.getInstance().getStats()[1]);
            } catch (SlickException ex) {
                Logger.getLogger(PlayerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case Input.KEY_3:
                System.out.println(player.getX() + " " + player.getY());
                break;
        }
    }

    /**
     * Ce qui se passe lorsqu'on enlève une touche
     * @param key La touche enlevé
     * @param c il est la parce qu'il a besoin d'être implémenté
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
