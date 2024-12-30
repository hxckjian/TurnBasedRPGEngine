package main;

import core.Manager.GameApp;
import javafx.application.Application;
import javafx.scene.image.Image;


public class MainProgram {

	public static void main(String[] args) {
//		core.Manager.BattleManager battle1 = core.Manager.BattleManager.createBattle();
//		try {
//			battle1.startBattleSequence();
//		} catch (Exception e) {
//
//		}
		Application.launch(GameApp.class, args);
	}


}
