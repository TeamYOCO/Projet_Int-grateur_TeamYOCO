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

    @Override
    public void levelUp() {
        
    }
    

   
}
