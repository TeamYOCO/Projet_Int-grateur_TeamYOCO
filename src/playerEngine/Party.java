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
    
    private PartyMember [] party;
    

    public Party() throws SlickException {
        party = new PartyMember[3];
    }

    public PartyMember[] getParty() {
        return party;
    }
    
    public boolean isNotNull(int index){
        if (party[index] == null)
            return false;
        else
            return true;
    }

    
}
