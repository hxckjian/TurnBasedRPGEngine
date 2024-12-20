package core.Items;

public class ManaPotion extends Potion {
	private final int manaAmount;
	
	private ManaPotion(String name, String description, int value, int manaAmount) {
        super(name, description, value);
        this.manaAmount = manaAmount;
    }
	
	public static ManaPotion createPotion(String name, int value, int manaAmount) {
		return new ManaPotion(name, "Restores " + manaAmount + "MP", value, manaAmount);
	}
	
	public int getManaAmount() {
		return this.manaAmount;
	}
	
    @Override
    public String toString() {
        return super.toString() + " (Restores: " + this.manaAmount + " MP)";
    }
}
