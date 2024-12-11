package core.Manager;
import java.util.Scanner;

import core.Player;

public class GameManager {
	private Player player;
//    private List<Stage> stages;
    private Scanner scanner;
    
    public GameManager() {
    	this.player = new Player();
    	this.scanner = new Scanner(System.in);
    }
    
    public void startGame() {
    	//Future implementation of shop and stages
    }

 
}
