package core.Items;

public class Elixir extends Potion {
	private final int healingAmount;
	private final int manaAmount;
	
	private Elixir(String name, String description, int value, int healingAmount, int manaAmount) {
        super(name, description, value);
        this.healingAmount = healingAmount;
        this.manaAmount = manaAmount;
    }
	
	public static Elixir createPotion(String name, int value, int healingAmount, int manaAmount) {
		return new Elixir(name, "Restores " + healingAmount + "HP and " + manaAmount + " MP"
				, value, healingAmount, manaAmount);
	}
	
	public int getHealingAmount() {
		return this.healingAmount;
	}
	
	public int getManaAmount() {
		return this.manaAmount;
	}
	
    @Override
    public String toString() {
        return super.toString() + " (Heals: " + this.healingAmount + " HP)";
    }
}
