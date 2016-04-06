/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partyMember;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Tomas
 */
public class MainCharacter extends PartyMember {

    public MainCharacter() throws SlickException {
        hpMax = 100;
        hp = hpMax;
        mpMax = 20;
        mp = 20;
        attack = 10;
        defence = 10;
        specialAttack = 10;
        specialDefence = 10;
        speed = 10;
        level = 0;
        exp = 0;
        expNeeded = 0;
        anim = new Image("res/pictures/hero_Combat.png");
        skillSet = new ArrayList();
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMpMax(int mpMax) {
        this.mpMax = mpMax;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setSpecialDefence(int specialDefence) {
        this.specialDefence = specialDefence;
    }

    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSkillSet(ArrayList skillSet) {
        this.skillSet = skillSet;
    }

    public void setAnim(Image anim) {
        this.anim = anim;
    }

    public int getHp() {
        return hp;
    }

    public int getMpMax() {
        return mpMax;
    }

    public int getMp() {
        return mp;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpecialDefence() {
        return specialDefence;
    }

    public int getExpNeeded() {
        return expNeeded;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList getSkillSet() {
        return skillSet;
    }

    public Image getAnim() {
        return anim;
    }

    @Override
    public void levelUp() {
        
    }
    

   
}
