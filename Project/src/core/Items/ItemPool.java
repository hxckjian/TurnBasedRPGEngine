package core.Items;

import java.util.HashMap;
import java.util.Map;

public class ItemPool {
    private static final Map<String, Item> ITEM_POOL = new HashMap<>();

    //creating fixed list of items in the Item Pool
    static {
        ITEM_POOL.put("Potion_50HP", Potion.createPotion("Potion", 10, 50));
        ITEM_POOL.put("Potion_100HP", Potion.createPotion("Potion", 15, 100));

        // Add other items as needed
        ITEM_POOL.put("ManaPotion_30MP", new Item("Mana Potion", "Restores 30 MP", 5));
        ITEM_POOL.put("Elixir", new Item("Elixir", "Fully restores HP and MP", 25));
    }

    private ItemPool() {
        // Private constructor to prevent instantiation
    }

    // Get an item by key
    public static Item getItem(String key) {
        if (!ITEM_POOL.containsKey(key)) {
            throw new IllegalArgumentException("Item not found: " + key);
        }
        return ITEM_POOL.get(key);
    }

    // Get all items as a map
    public static Map<String, Item> getAllItems() {
        return new HashMap<>(ITEM_POOL); // Return a copy to prevent modification
    }
}
