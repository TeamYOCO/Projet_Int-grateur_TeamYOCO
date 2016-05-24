/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import ca.qc.bdeb.info204.Game;
import static ca.qc.bdeb.info204.Game.HEIGHT;
import static ca.qc.bdeb.info204.Game.MAINMENU;
import static ca.qc.bdeb.info204.Game.WIDTH;
import gameEngine.ResManager;
import static items.Equipment.MAX_STATS;
import java.awt.Font;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import playerEngine.CharacterStatsManager;
import playerEngine.PlayerGameManager;

/**
 *
 * @author Olivier
 */
public class LevelUpScreen extends BasicGameState {

    private PlayerGameManager manager;
    private static int stateID;
    private Image background;
    private UnicodeFont ufont50,ufont24;
    
    public LevelUpScreen(int stateID, PlayerGameManager manager) throws SlickException {
        LevelUpScreen.stateID = stateID;
        this.manager = manager;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        ufont50 = ResManager.getInstance().getFont("Abadi MT Condensed Light", 50);
        ufont24 = ResManager.getInstance().getFont("Abadi MT Condensed Light", 24);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Overworld.getScreenShot().draw(0, 0);
        ResManager.getInstance().getImage("LevelUpScreenPic").draw(0, 0);
        g.setFont(ufont50);
        g.drawString(""+CharacterStatsManager.getInstance().getLvlBoost(), 475, 260);
        g.setFont(ufont24);
        for (int i = 0; i < MAX_STATS; i++) {
            g.drawString(""+CharacterStatsManager.getInstance().getStats()[i] + "+" + CharacterStatsManager.getInstance().getStatsUpgrade()[i], 52+(143*i), 555);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();

        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();
        
        //si le joueur clique sur "vie"
        if((mouseX > 21 && mouseX < 145) && (mouseY>166 && mouseY<303)){
            //clique droit -> augementer la stat
            if((CharacterStatsManager.getInstance().getLvlBoost() > 0) && (input.isMousePressed(0))){
                    CharacterStatsManager.getInstance().buffStat(0);
                    CharacterStatsManager.getInstance().setHp(CharacterStatsManager.getInstance().getHp()+1);
              //clique gauche -> diminuer la stat
            } else if ((CharacterStatsManager.getInstance().getLvlBoost() < 10) 
                    && (CharacterStatsManager.getInstance().getStatsUpgrade()[0] > 0) 
                    && (input.isMousePressed(1))){
                    CharacterStatsManager.getInstance().nerfStat(0);
                    CharacterStatsManager.getInstance().setHp(CharacterStatsManager.getInstance().getHp()-1);
            }
        }
        
        //si le joueur clique sur "att"
        if((mouseX > 164 && mouseX < 288) && (mouseY>166 && mouseY<303)){
            //clique droit -> augementer la stat
            if((CharacterStatsManager.getInstance().getLvlBoost() > 0) && (input.isMousePressed(0))){
                    CharacterStatsManager.getInstance().buffStat(1);
              //clique gauche -> diminuer la stat
            } else if ((CharacterStatsManager.getInstance().getLvlBoost() < 10) 
                    && (CharacterStatsManager.getInstance().getStatsUpgrade()[1] > 0) 
                    && (input.isMousePressed(1))){
                    CharacterStatsManager.getInstance().nerfStat(1);
            }
        }
        
        //si le joueur clique sur "def"
        if((mouseX > 307 && mouseX < 431) && (mouseY>166 && mouseY<303)){
            //clique droit -> augementer la stat
            if((CharacterStatsManager.getInstance().getLvlBoost() > 0) && (input.isMousePressed(0))){
                    CharacterStatsManager.getInstance().buffStat(2);
              //clique gauche -> diminuer la stat
            } else if ((CharacterStatsManager.getInstance().getLvlBoost() < 10) 
                    && (CharacterStatsManager.getInstance().getStatsUpgrade()[2] > 0) 
                    && (input.isMousePressed(1))){
                    CharacterStatsManager.getInstance().nerfStat(2);
            }
        }
        
        //si le joueur clique sur "spAtt"
        if((mouseX > 450 && mouseX < 574) && (mouseY>166 && mouseY<303)){
            //clique droit -> augementer la stat
            if((CharacterStatsManager.getInstance().getLvlBoost() > 0) && (input.isMousePressed(0))){
                    CharacterStatsManager.getInstance().buffStat(3);
              //clique gauche -> diminuer la stat
            } else if ((CharacterStatsManager.getInstance().getLvlBoost() < 10) 
                    && (CharacterStatsManager.getInstance().getStatsUpgrade()[3] > 0) 
                    && (input.isMousePressed(1))){
                    CharacterStatsManager.getInstance().nerfStat(3);
            }
        }
        
        //si le joueur clique sur "spDef"
        if((mouseX > 593 && mouseX < 717) && (mouseY>166 && mouseY<303)){
            //clique droit -> augementer la stat
            if((CharacterStatsManager.getInstance().getLvlBoost() > 0) && (input.isMousePressed(0))){
                    CharacterStatsManager.getInstance().buffStat(4);
              //clique gauche -> diminuer la stat
            } else if ((CharacterStatsManager.getInstance().getLvlBoost() < 10) 
                    && (CharacterStatsManager.getInstance().getStatsUpgrade()[4] > 0) 
                    && (input.isMousePressed(1))){
                    CharacterStatsManager.getInstance().nerfStat(4);
            }
        }
        
        //si le joueur clique sur "vit"
        if((mouseX > 736 && mouseX < 860) && (mouseY>166 && mouseY<303)){
            //clique droit -> augementer la stat
            if((CharacterStatsManager.getInstance().getLvlBoost() > 0) && (input.isMousePressed(0))){
                    CharacterStatsManager.getInstance().buffStat(5);
              //clique gauche -> diminuer la stat
            } else if ((CharacterStatsManager.getInstance().getLvlBoost() < 10) 
                    && (CharacterStatsManager.getInstance().getStatsUpgrade()[5] > 0) 
                    && (input.isMousePressed(1))){
                    CharacterStatsManager.getInstance().nerfStat(5);
            }
        }
        
        //si le joueur clique sur "eng"
        if((mouseX > 879 && mouseX < 1003) && (mouseY>166 && mouseY<303)){
            //clique droit -> augementer la stat
            if((CharacterStatsManager.getInstance().getLvlBoost() > 0) && (input.isMousePressed(0))){
                    CharacterStatsManager.getInstance().buffStat(6);
              //clique gauche -> diminuer la stat
            } else if ((CharacterStatsManager.getInstance().getLvlBoost() < 10) 
                    && (CharacterStatsManager.getInstance().getStatsUpgrade()[6] > 0) 
                    && (input.isMousePressed(1))){
                    CharacterStatsManager.getInstance().nerfStat(6);
            }
        }
        
        //si le joueur clique sur "effacer", réinitialiser toutes les stats
        if((mouseX > 252 && mouseX < 472) && (mouseY > 37 && mouseY < 87) && (input.isMousePressed(0))){
           CharacterStatsManager.getInstance().resetStats();
        }
        
        //si le joueur clique sur "enregistrer", retourner dans le overworld
        if((mouseX > 552 && mouseX < 772) && (mouseY > 37 && mouseY < 87) && (input.isMousePressed(0))){
            for (int j = 0; j < MAX_STATS; j++) {
                CharacterStatsManager.getInstance().setStatsUpgrade(j, 0);
            }
            sbg.enterState(Game.OVERWORLD);
        }

    }
}
