/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partyMember;

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Tomas
 */
public class MC extends PartyMember {

    public MC(int hpMax, int hp, int mpMax, int mp, int attack, int speed, int specialAttack, int defence, int specialDefence, int expNeeded, int exp, ArrayList skillSet, Image anim, int level) {
        super(hpMax, hp, mpMax, mp, attack, speed, specialAttack, defence, specialDefence, expNeeded, exp, skillSet, anim, level);
    }

    public MC() throws SlickException {
        super(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, 0);
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

    @Override
    public void levelUp() {
    }
    

   
}
