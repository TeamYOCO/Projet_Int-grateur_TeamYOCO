package gameEngine;

import entities.Player;
import maps.MiniMap;
import org.newdawn.slick.SlickException;

/**
 * 
 * @author Seb et Tomas
 */
public class TriggerController {

	private MiniMap map;
	private Player player;

	public TriggerController(MiniMap map, Player player) {
		this.map = map;
		this.player = player;
	}

        // Méthode qui détecte si un trigger à été activé
	public void update() throws SlickException {
		for (int objectID = 0; objectID < this.map.getObjectCount(); objectID++) {
			if (isInTrigger(objectID)) {
				if ("teleport".equals(this.map.getObjectType(objectID))) {
					this.teleport(objectID);
				}  else if ("change_map".equals(this.map.getObjectType(objectID))) {
					this.changeMap(objectID);
				}
			}
		}
	}
        
        // Méthode qui détecte si un joueur est dans un trigger
	private boolean isInTrigger(int id) {
		return this.player.getX() > this.map.getObjectX(id)
				&& this.player.getX() < this.map.getObjectX(id) + this.map.getObjectWidth(id)
				&& this.player.getY() > this.map.getObjectY(id)
				&& this.player.getY() < this.map.getObjectY(id) + this.map.getObjectHeight(id);
	}

        // Méthode qui gère la téléportation d'un joueur à l'intérieur d'une même map
	private void teleport(int objectID) {
		this.player.setX(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-x",
				Float.toString(this.player.getX()))));
		this.player.setY(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-y",
				Float.toString(this.player.getY()))));
	}

        // Méthode qui gère le changement de maps
	private void changeMap(int objectID) throws SlickException {
		this.teleport(objectID);
		String newMap = this.map.getObjectProperty(objectID, "destMmap", "undefined");
		if (!"undefined".equals(newMap)) {
			this.map.changeMap("res/map/" + newMap);
		}
	}

}
