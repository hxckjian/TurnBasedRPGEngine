package main;

public class MainProgram {

	public static void main(String[] args) {
		core.Manager.BattleManager battle1 = core.Manager.BattleManager.createBattle();
		try {
			battle1.startBattleSequence();
		} catch (Exception e) {
			
		}

	}

}
