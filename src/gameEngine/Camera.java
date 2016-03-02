/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import entities.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 *
 * @author Seb
 */
public class Camera {

    // Variables de la classe
    private Player player;
    private float xCamera, yCamera;

    // constructeur de la classe
    public Camera(Player player) {
        this.player = player;
        this.xCamera = player.getX();
        this.yCamera = player.getY();
    }

    // Gêre le placement de la caméra
    public void place(GameContainer container, Graphics g) {
        g.translate(container.getWidth() / 2 - (int) this.xCamera,
                container.getHeight() / 2 - (int) this.yCamera);
    }

    // Se charge de positionner la caméra
    public void update(GameContainer container) {
        int w = container.getWidth() / 10;
        if (this.player.getX() > this.xCamera + w) {
            this.xCamera = this.player.getX() - w;
        } else if (this.player.getX() < this.xCamera - w) {
            this.xCamera = this.player.getX() + w;
        }
        int h = container.getHeight() / 10;
        if (this.player.getY() > this.yCamera + h) {
            this.yCamera = this.player.getY() - h;
        } else if (this.player.getY() < this.yCamera - h) {
            this.yCamera = this.player.getY() + h;
        }
    }
}
