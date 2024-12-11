package core.Items;

public class Potion extends Item {
	private final int healingAmount;
	
	private Potion(String name, String description, int value, int healingAmount) {
        super(name, description, value);
        this.healingAmount = healingAmount;
    }

    public static Potion createPotion(String name, int value, int healingAmount) {
        return new Potion(name, "Restores " + healingAmount + "HP", value, healingAmount);
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    @Override
    public String toString() {
        return super.toString() + " (Heals: " + healingAmount + " HP)";
    }
}
