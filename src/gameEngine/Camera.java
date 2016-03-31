/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameEngine;

import entities.Player;
import maps.MiniMap;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Cette classes contient toutes les méthodes qui claculent la position de la caméra
 * @authors Seb and Tomas
 */
public class Camera {

    // Variables de la classe
    private Player player;
    private MiniMap map;
    private float xCamera, yCamera;

    // constructeur de la classe
    public Camera(Player player, MiniMap map) {
        this.player = player;
        this.map = map;
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
        int min = container.getWidth() / 2;
        int max = this.map.getTiledMap().getWidth() * this.map.getTiledMap().getTileWidth() - container.getWidth() / 2;
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
        if(this.map.getTiledMap().getWidth() * this.map.getTiledMap().getTileWidth()>=container.getWidth()){
        if (this.xCamera < min) {
            this.xCamera = min;
        } else if (this.xCamera > max) {
            this.xCamera = max;
        }
        }
        min = container.getHeight() / 2;
        max = this.map.getTiledMap().getHeight() * this.map.getTiledMap().getTileHeight() - container.getHeight() / 2;
        if(this.map.getTiledMap().getHeight() * this.map.getTiledMap().getTileHeight()>=container.getHeight()){
        if (this.yCamera < min) {
            this.yCamera = min;
        } else if (this.yCamera > max) {
            this.yCamera = max;
        }
        }
    }
}
