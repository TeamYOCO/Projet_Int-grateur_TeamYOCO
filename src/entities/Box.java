/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author The Ninetail
 */
public class Box {
    private float x1,x2,y1,y2;
    private float width,height;

    /**
     * Crée la boite
     * @param x1 Le x où commence la boîte
     * @param y1 Le y où commence la boîte
     * @param width La longueur de la boîte
     * @param height La hauteur de la boîte
     */
    public Box(float x1, float y1, float width, float height) {
        this.x1 = x1;
        this.y1 = y1;
        this.width = width;
        this.height = height;
        this.x2 = x1 + width;
        this.y2 = y1 +height;
    }
    
    /**
     * Dessine la boîte
     * @param g Le graphique qui permet de dessiner
     * @throws SlickException
     */
    public void render(Graphics g) throws SlickException{
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillRect(x1, y1, width, height);
    }

    /**
     * Retourne le début de la boîte en x
     * @return le début de la boîte en x
     */
    public float getX1() {
        return x1;
    }

    /**
     * Retourne le début de la boîte en y
     * @return le début de la boîte en y
     */
    public float getY1() {
        return y1;
    }

    /**
     *Retourne la fin de la boîte en x
     * @return la fin de la boîte en x
     */
    public float getX2() {
        return x2;
    }

    /**
     *Retourne la fin de la boîte en y
     * @return la fin de la boîte en y
     */
    public float getY2() {
        return y2;
    }
    
    /**
     * Mets la position de la boîte delon un x et un y
     * @param x La position x du début de la nouvel position de la boîte en x
     * @param y La position x du début de la nouvel position de la boîte en y
     */
    public void setPos(float x, float y){
        x1 = x;
        y1 = y;
        x2 = x+width;
        y2 = y+height;
    }
    
    /**
     * Requarde si une boîte rentre en collision avec une autre
     * @param box La boîte à comparer
     * @return un booléen de si il y a une collision
     */
    public boolean collision(Box box){
        if (y2 < box.getY1())
            return false;
        if (x2 < box.getX1())
            return false;
        if (y1 > box.getY2())
            return false;
        if (x1 > box.getX2())
            return false;
        return true;
    }

}
