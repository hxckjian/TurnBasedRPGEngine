package core.Manager;

import java.util.Arrays;
import java.util.List;

import core.Player;

public class PlayerManager {
	List<Player> playerList;
	
	private PlayerManager(List<Player> playerList) {
		this.playerList = playerList;
	}
	
	public static PlayerManager of(core.Player... playerArgs) {
		List<Player> playerList = Arrays.asList(playerArgs);
		return new PlayerManager(playerList);
	}
	
	public int getPlayerSize() {
		return this.playerList.size();
	}
	
	public Player getSpecificPlayer(int target) {
		return this.playerList.get(target);
	}
	
	public String generatePlayerStatus() {
		String text = "";
		for (int i = 0; i < this.playerList.size(); i++) {
			String aliveText = "";
			if (this.playerList.get(i).isAlive()) {
				aliveText = "Alive";
			} else {
				aliveText = "Dead";
			}
			text += i + " | " + this.getSpecificPlayer(i).getName() 
					+ " | Health: "  + this.getSpecificPlayer(i).getHealth()
					+ " | Mana : " + this.getSpecificPlayer(i).getMana()
					+ " | " + aliveText +  "\n";
		}
		return text;
	}
}
