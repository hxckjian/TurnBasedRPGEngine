package core.Items;

import java.util.Objects;

public class Item {
	private String name;
	private String description;
	private int value;
	
	public Item(String name, String description, int value) {
		this.name = name;
		this.description = description;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getValue() {
		return this.value;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
	
	@Override
    public String toString() {
        return name + " - " + description + " (Value: " + value + " gold)";
    }
}
