/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerEngine;

import org.newdawn.slick.SlickException;
import partyMember.MainCharacter;
import partyMember.PartyMember;

/**
 *
 * @author Seb
 */
public class PlayerGameManager {
    
    private Party party;
    private Inventory inventory;

    public PlayerGameManager() throws SlickException {
        party = new Party();
        PartyMember mn = new MainCharacter();
        party.getParty()[0] = mn;
    }

    public Party getPartyClass() {
        return party;
    }

    public Inventory getInventoryClass() {
        return inventory;
    }
}
