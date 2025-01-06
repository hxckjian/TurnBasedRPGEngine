package core.Monsters;

public class Goblin extends Monster {
	private Goblin() {
		super("Goblin", 100, 20, null);
	}
	
	public static Goblin creation() {
		return new Goblin();
	}
}
