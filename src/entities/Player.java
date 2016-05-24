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

    public Player(MiniMap map, ArrayList<Entity> list) {
        this.map = map;
        this.list = list;
    }

    public void init() throws SlickException {
        this.moveAnimations = new Animation[8];
        this.attackAnimation = new Animation[4];
        this.bowShootAnimation = new Animation[4];
        this.bowAnimation = new Animation[4];
        this.castAnimations = new Animation[4];
        this.setX(205);
        this.setY(169);
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

    // Update la position et l'action du joueur
    @Override
    public void update(int delta) {
        this.setX(Player.getSaveX());
        this.setY(Player.getSaveY());
        //  vérifie si le déplacement est possible et déplace le joueur si possible
        if (moving && !attacking && !shooting && !casting && knockbackTimer <= 0) {
            if (!map.isCollision(futurX(delta), futurY(delta))) {
                this.setX(futurX(delta));
                this.setY(futurY(delta));
            }
        }
        // calcule le temps restant au joueur durant sont attaque
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
        }
        // Repousse le joueur si il vient de se faire frapper
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

    // Teste la position pour se déplacer en X
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

    // Teste la position pour se déplacer en Y
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
            list.add(swordSwing);
        }
    }

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

    public void cast() throws SlickException {
        if (!attacking && !shooting && !casting && isHitable() && CharacterStatsManager.getInstance().getInventory().isSpellTomeEquiped()) {
            casting = true;
            attackDirection = direction;
            attackCounter = 500;
            for (int i = 0; i < castAnimations.length; i++) {
                castAnimations[i].restart();
            }
        }
    }

    // Les méthodes suivantes sont des getters/setters
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isAttacking() {
        return attacking;
    }

    @Override
    public void takeHit(int damage, int hitDirection) {
        super.takeHit(damage, hitDirection);
        try {
            CharacterStatsManager.getInstance().takeDamage(damage);
        } catch (SlickException ex) {
        }
    }

    public void test(){
        System.out.println(map.isCollision(x, y));
        try {
            System.out.println(CharacterStatsManager.getInstance().getExp());
        } catch (SlickException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
