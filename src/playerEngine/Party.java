/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerEngine;

import org.newdawn.slick.SlickException;
import partyMember.*;

/**
 *
 * @author 1455367
 */
public class Party {
    
    private PartyMember [] party = new PartyMember[3];

    public Party() throws SlickException {
        party[0] = new MC();
    }
    
}
