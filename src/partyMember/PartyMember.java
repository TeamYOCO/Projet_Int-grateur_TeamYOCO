/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partyMember;

import java.util.ArrayList;
import org.newdawn.slick.Image;

/**
 *
 * @author 1455367
 */
public abstract class PartyMember {

    protected int hpMax;
    protected int hp;
    protected int mpMax;
    protected int mp;
    protected int attack;
    protected int speed;
    protected int specialAttack;
    protected int defence;
    protected int specialDefence;
    protected int expNeeded;
    protected int exp;
    protected int level;
    protected ArrayList skillSet;
    protected Image anim;

    public PartyMember(int hpMax, int mpMax, int attack, int speed, int specialAttack, int defence, int specialDefence, int expNeeded, int exp, int level, Image anim) {
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.mpMax = mpMax;
        this.mp = mpMax;
        this.attack = attack;
        this.speed = speed;
        this.specialAttack = specialAttack;
        this.defence = defence;
        this.specialDefence = specialDefence;
        this.expNeeded = expNeeded;
        this.exp = exp;
        this.level = level;
        this.anim = anim;
    }

    public PartyMember() {
    }

    public void gainExp(int expGained) {
        exp += expGained;
        if (exp >= expNeeded) {
            exp -= expNeeded;
            expNeeded = (int) ((float) expNeeded * 1.5);
            level++;
            levelUp();
        }
    }

    public abstract void levelUp();

    public Image getAnim() {
        return anim;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHp() {
        return hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getExp() {
        return exp;
    }

    public int getExpNeeded() {
        return expNeeded;
    }

    public int getLevel() {
        return level;
    }

    public int getMp() {
        return mp;
    }

    public int getMpMax() {
        return mpMax;
    }

    public ArrayList getSkillSet() {
        return skillSet;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefence() {
        return specialDefence;
    }

    public int getSpeed() {
        return speed;
    }
    
}
