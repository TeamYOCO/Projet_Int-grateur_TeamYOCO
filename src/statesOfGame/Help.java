/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statesOfGame;

import static ca.qc.bdeb.info204.Game.HEIGHT;
import static ca.qc.bdeb.info204.Game.MAINMENU;
import static ca.qc.bdeb.info204.Game.WIDTH;
import gameEngine.ResMng;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Olivier
 */
public class Help extends BasicGameState {

    private static int stateID;
    private Image background;
    
    public Help(int stateID) throws SlickException {
        Help.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = ResMng.getInstance().getImage("treesun");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw(0,0);
        g.setColor(Color.darkGray);
        g.drawString("Vous pouvez effectuer les attaque suivante dans le jeu\n"
                + "A : attaque magique\nS : Tirer une flèche\nD : Coup d'épée\n"
                + "Chaque attaque inflige des dégats aux ennemis en fonction de vos \nstatistiques d'attaque et d'attaque spéciale et vous coûte une certaine quantité d'énergie\n"
                + "Chaque ennemi tué vous fait gagner de l'argent et de l'expérience\n"
                + "À chaque fois que vous augmenté de niveau, vous obtenez 10 points \nd'augmentation de stats que vous pouvez utiliser pour augmenter n'importe quelle des stats suivantes :\n"
                + "- Vie\n- Attaque\n- Défense\n- Spécial Attaque\n- Spécial Défense\n- Vitesse\n- Énergie\n"
                + "Vous pouvez également accéder à l'inventaire avec la touche 'e'\n"
                + "Pour sauvegarder votre proression, cliquer sur 'p'", 100, 100);
        g.drawString("< Retour", 15, HEIGHT-25);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();

        int mouseX = Mouse.getX();
        int mouseY = Mouse.getY();

        if ((mouseX > 0 && mouseX < 100) && (mouseY > 0 && mouseY < 25)) {
            if (input.isMouseButtonDown(0)) {
                sbg.enterState(MAINMENU);
            }
        }
    }
}
