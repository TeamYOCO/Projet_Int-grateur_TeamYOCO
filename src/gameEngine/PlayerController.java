package gameEngine;

import entities.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;

public class PlayerController implements KeyListener {

    // Variables de la classe
    private Player player;

    // Constructeur
    public PlayerController(Player player) {
        this.player = player;
    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }

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
        {
            try {
                this.player.attack();
            } catch (SlickException ex) {
                Logger.getLogger(PlayerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
        }
    }

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
