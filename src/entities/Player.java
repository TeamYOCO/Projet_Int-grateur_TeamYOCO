/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import gameEngine.ResManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import maps.MiniMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import playerEngine.CharacterStatsManager;

/**
 *
 *
 * @author Seb
 */
public class Player extends Mob {

    private int direction = 2, attackCounter = 0, attackDirection;
    private float speed = 0.25f;
    private boolean moving;
    private MiniMap map;
    private int hp = 0, attack = 0, defense = 0;

    private boolean attacking = false, shooting = false, casting = false, shot = true;
    private Animation[] attackAnimation;
    private Animation[] bowShootAnimation;
    private Animation[] bowAnimation;
    private Animation[] castAnimations;

    /**
     * Crée le joueur
     * @param map La carte
     * @param list Une liste d'entité
     */
    public Player(MiniMap map, ArrayList<Entity> list) {
        this.map = map;
        this.list = list;
    }

    /**
     * Initialise le joueur
     * @throws SlickException
     */
    public void init() throws SlickException {
        this.moveAnimations = new Animation[8];
        this.attackAnimation = new Animation[4];
        this.bowShootAnimation = new Animation[4];
        this.bowAnimation = new Animation[4];
        this.castAnimations = new Animation[4];
        this.setX(64);
        this.setY(256);
        this.xOff = -16;
        this.yOff = -50;
        moving = false;
        this.hitpoints = 100;
        this.hitBox = new Box(x + xOff, y + yOff, 32, 50);
        SpriteSheet moveSpriteSheet = ResManager.getInstance().getSpriteSheet("main_character_walk");
        SpriteSheet attackSpriteSheet = ResManager.getInstance().getSpriteSheet("main_character_swing");
        SpriteSheet bowShootSpriteSheet = ResManager.getInstance().getSpriteSheet("main_character_shoot");
        SpriteSheet bowSpriteSheet = ResManager.getInstance().getSpriteSheet("recurvebow");
        SpriteSheet castSheet = ResManager.getInstance().getSpriteSheet("main_character_cast");
        for (int i = 0; i < 4; i++) {
            this.moveAnimations[i] = loadAnimation(moveSpriteSheet, 0, 1, i);
            this.moveAnimations[i + 4] = loadAnimation(moveSpriteSheet, 1, 9, i);
            this.attackAnimation[i] = loadAnimation(attackSpriteSheet, 1, 6, i);
            this.bowShootAnimation[i] = loadAnimation(bowShootSpriteSheet, 1, 13, i);
            this.bowAnimation[i] = loadAnimation(bowSpriteSheet, 1, 13, i);
            this.castAnimations[i] = loadAnimation(castSheet, 1, 7, i);
        }

    }

    /**
     * Dessine le joueur pour la première fois
     * @param g  Le graphique qui permet de dessiner
     * @throws SlickException
     */
    @Override
    public void render(Graphics g) throws SlickException {
        speed = 0.02f * CharacterStatsManager.getInstance().getStats()[5];
        for (int i = 0; i < moveAnimations.length; i++) {
            moveAnimations[i].setSpeed(speed * 4);
        }
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x - 16, y - 8, 32, 16);
        if (attacking && isHitable()) {
            g.drawAnimation(attackAnimation[attackDirection], x - 32, y - 60);
        } else if (shooting && isHitable()) {
            g.drawAnimation(bowShootAnimation[attackDirection], x - 32, y - 60);
            g.drawAnimation(bowAnimation[attackDirection], x - 32, y - 60);
        } else if (casting && isHitable()) {
            g.drawAnimation(castAnimations[attackDirection], x - 32, y - 60);
        } else if (isHitable()) {
            g.drawAnimation(moveAnimations[direction + (moving ? 4 : 0)], x - 32, y - 60);
        } else if (!isHitable()) {
            g.drawAnimation(moveAnimations[direction + (moving ? 4 : 0)], x - 32, y - 60, Color.red);
        }
    }

   
    /**
     *Update la position et l'action du joueur
     * @param delta Le temps d'une ittération
     */
    @Override
    public void update(int delta) {
        try {
            CharacterStatsManager.getInstance().updateCooldown(delta);
        } catch (SlickException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setX(Player.getSaveX());
        this.setY(Player.getSaveY());
        //  vérifie si le déplacement est possible et déplace le joueur si possible
        if (moving && !attacking && !shooting && !casting && knockbackTimer <= 0) {
            if (!map.isCollision(futurX(delta), futurY(delta))) {
                this.setX(futurX(delta));
                this.setY(futurY(delta));
            }
        } // calcule le temps restant au joueur durant sont attaque
        else if (knockbackTimer <= 0) {
            attackCounter -= delta;
            // Vérifie si le joueur est en train de décocher un flèche et la tire au bon momment 
            if (shooting && attackCounter <= 500 && shot == false) {
                shot = true;
                Arrow arrow = null;
                float sx = x, sy = y;
                switch (direction) {
                    case 0:
                    case 1:
                        sy = y - 20;
                        break;
                    case 2:
                    case 3:
                        sy = y - 20;
                        break;
                }
                try {
                    arrow = new Arrow(sx, sy, attackDirection, 4000, map);
                } catch (SlickException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                list.add(arrow);
            }
            // Si l'attaque du joueur est terminer permet au joueur de se déplacer à nouveau
            if (attackCounter <= 0) {
                attacking = false;
                shooting = false;
                casting = false;
            }
        } // Repousse le joueur si il vient de se faire frapper
        else if (knockbackTimer > 0) {
            float tempSpeed = speed;
            speed = 0.5f;
            if (!map.isCollision(futurX(-delta), futurY(-delta))) {
                this.setX(futurX(-delta));
                this.setY(futurY(-delta));
            }
            speed = tempSpeed;
            knockbackTimer -= delta;
        }
        // Update la hitbox du joueur
        hitBox.setPos(x + xOff, y + yOff);

    }

    /**
     * Calcul la position en x après l'ittération
     * @param delta La durée d'une ittération
     * @return la position en x après l'ittération
     */
    private float futurX(int delta) {
        float futurX = this.x;
        switch (this.direction) {
            case 1:
                futurX = this.x - speed * delta;
                break;
            case 3:
                futurX = this.x + speed * delta;
                break;
        }
        return futurX;
    }

    /**
     * Calcul la position en y après l'ittération
     * @param delta La durée d'une ittération
     * @return la position en y après l'ittération
     */
    private float futurY(int delta) {
        float futurY = this.y;
        switch (this.direction) {
            case 0:
                futurY = this.y - speed * delta;
                break;
            case 2:
                futurY = this.y + speed * delta;
                break;
        }
        return futurY;
    }

    /**
     * Donne un coup d'épée
     * @throws SlickException
     */
    public void attack() throws SlickException {
        if (!attacking && !shooting && !casting && isHitable() && CharacterStatsManager.getInstance().getInventory().isSwordEquiped()) {
            attacking = true;
            attackDirection = direction;
            attackCounter = 500;
            for (int i = 0; i < attackAnimation.length; i++) {
                attackAnimation[i].restart();
            }
            SwordSwing swordSwing = null;
            float sx = 0, sy = 0;
            if (CharacterStatsManager.getInstance().getInventory().isItemEquiped("Epee legendaire")
                    || CharacterStatsManager.getInstance().getInventory().isItemEquiped("Epee d'argent")
                    || CharacterStatsManager.getInstance().getInventory().isItemEquiped("Epee Royale")) {
                sx = x;
                sy = y;
                swordSwing = new BigSwordSwing(sx, sy, direction, 500);
            } else {
                switch (attackDirection) {
                    case 0:
                        sx = x - 64;
                        sy = y - 92;
                        break;
                    case 1:
                        sx = x - 64;
                        sy = y - 87;
                        break;
                    case 2:
                        sx = x - 64;
                        sy = y - 87;
                        break;
                    case 3:
                        sx = x - 64;
                        sy = y - 83;
                        break;
                }
                swordSwing = new SwordSwing(sx, sy, attackDirection, 500);
            }
            list.add(swordSwing);
        }
    }

    /**
     * Tire a l'arc
     * @throws SlickException
     */
    public void shoot() throws SlickException {
        if (!attacking && !shooting && !casting && isHitable() && CharacterStatsManager.getInstance().getInventory().isBowEquiped()) {
            shooting = true;
            attackDirection = direction;
            attackCounter = 1000;
            shot = false;
            for (int i = 0; i < bowShootAnimation.length; i++) {
                bowShootAnimation[i].restart();
                bowAnimation[i].restart();
            }
        }
    }

    /**
     * Lance un sort
     * @throws SlickException
     */
    public void cast() throws SlickException {
        if (!attacking && !shooting && !casting && isHitable() && CharacterStatsManager.getInstance().getInventory().isSpellTomeEquiped()
                && CharacterStatsManager.getInstance().getStats()[6] == 5000) {
            casting = true;
            attackDirection = direction;
            attackCounter = 500;
            CharacterStatsManager.getInstance().getStats()[6] = 0;

            Particle spell = null;
            if (CharacterStatsManager.getInstance().getInventory().isItemEquiped("Livre rouge")) {
                spell = new Fireball(this.x, this.y - 25, attackDirection, 1000, map, list);
            } else if (CharacterStatsManager.getInstance().getInventory().isItemEquiped("Livre cyan")) {
                CharacterStatsManager.getInstance().heal(CharacterStatsManager.getInstance().getStats()[3] * 2);
                SpriteSheet sheet = ResManager.getInstance().getSpriteSheet("healing");
                Animation anim = loadAnimation(sheet, 0, 6, 0);
                spell = new Particle(anim, 500, 0, 0, 0, x-16, y-32, new Box(x, y, 0, 0));
            }else if (CharacterStatsManager.getInstance().getInventory().isItemEquiped("Livre gris")){
                spell = new Tornade(x, y-64, direction);
            }
            if (spell != null) {
                list.add(spell);
            }
            for (int i = 0; i < castAnimations.length; i++) {
                castAnimations[i].restart();
            }
        }
    }

    // Les méthodes suivantes sont des getters/setters
    /**
     * Retourne la direction du joueur
     * @return la direction du joueur
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Change la direction du joueur
     * @param direction la nouvelle direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * retourne si le joueur bouge
     * @return un bolléen si le joueur bouge
     */
    public boolean getMoving() {
        return moving;
    }

    /**
     * Change le booléen de mouvement
     * @param moving le  nouveau booléen
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     * Retourne si le joueur est en train d'attaquer
     * @return si le joueur est en train d'attaquer
     */
    public boolean isAttacking() {
        return attacking;
    }

    /**
     * Lorsque le joueur se fait toucher
     * @param damage le dégat physique de l'ennemie
     * @param damageSpecial le dégat magique de l'ennemie
     * @param hitDirection la direction du coup
     */
    @Override
    public void takeHit(int damage, int damageSpecial, int hitDirection) {
        super.takeHit(damage, damageSpecial, hitDirection);
        try {
            CharacterStatsManager.getInstance().takeDamage(damage, damageSpecial);
        } catch (SlickException ex) {
        }
    }

}
