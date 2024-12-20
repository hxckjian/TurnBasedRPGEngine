package core.Items;

public class HealthPotion extends Potion {
	private final int healingAmount;
	
	private HealthPotion(String name, String description, int value, int healingAmount) {
        super(name, description, value);
        this.healingAmount = healingAmount;
    }
	
	public static HealthPotion createPotion(String name, int value, int healingAmount) {
		return new HealthPotion(name, "Restores " + healingAmount + "HP", value, healingAmount);
	}
	
	public int getHealingAmount() {
		return this.healingAmount;
	}
	
    @Override
    public String toString() {
        return super.toString() + " (Heals: " + this.healingAmount + " HP)";
    }
}
