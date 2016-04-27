/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerEngine;

import java.util.ArrayList;
import org.newdawn.slick.Image;

/**
 *
 * @author 1455367
 */
public class CharacterStatsManager {
    protected int hpMax;
    protected int hp;
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

    public CharacterStatsManager(int[]stats, int expNeeded, int exp, int level, Image anim) {
        this.hpMax = stats[0];
        this.attack = stats[1];
        this.defence = stats[2];
        this.specialAttack = stats[3];
        this.specialDefence = stats[4];
        this.speed = stats[5];
        this.expNeeded = expNeeded;
        this.exp = exp;
        this.level = level;
        this.anim = anim;
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

    public void levelUp(){
        
    }

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

    public void setAnim(Image anim) {
        this.anim = anim;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSkillSet(ArrayList skillSet) {
        this.skillSet = skillSet;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public void setSpecialDefence(int specialDefence) {
        this.specialDefence = specialDefence;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
