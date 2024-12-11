package core.Monsters;

public class Slime extends Monster{
	private Slime() {
		super("Slime", 50, 10);
	}
	
	public static Slime creation() {
		return new Slime();
	}
}
