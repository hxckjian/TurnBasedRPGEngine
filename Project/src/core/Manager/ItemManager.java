package core.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.Items.Item;

public class ItemManager {
    private final Map<Item, Integer> itemCounts;

    public ItemManager() {
        this.itemCounts = new HashMap<>();
    }

    public void addItem(Item item) {
        // Use the item reference directly
        this.itemCounts.put(item, this.itemCounts.getOrDefault(item, 0) + 1);
        System.out.println(item.getName() + " added to inventory. Total: " + itemCounts.get(item) + "x.");
    }


    public boolean removeItem(Item item) {
        if (itemCounts.containsKey(item)) {
            int count = itemCounts.get(item);
            if (count > 1) {
                itemCounts.put(item, count - 1);
            } else {
                itemCounts.remove(item);
            }
            System.out.println(item.getName() + " removed from inventory.");
            return true;
        } else {
            System.out.println(item.getName() + " is not in the inventory.");
            return false;
        }
    }

    public List<Item> showInventoryWithIndices() {
        System.out.println("---- Inventory ----");
        List<Item> indexedItems = new ArrayList<>();
        if (this.checkEmpty()) {
            System.out.println("No items in inventory.");
        } else {
            int index = 0;
            for (Map.Entry<Item, Integer> entry : this.itemCounts.entrySet()) {
                System.out.println(index + " | " + entry.getKey().toString() + " | X " + entry.getValue());
                indexedItems.add(entry.getKey());
                index++;
            }
        }
        return indexedItems;
    }

    public Item getItemByIndex(int index) {
        List<Item> indexedItems = new ArrayList<>(this.itemCounts.keySet());
        if (index >= 0 && index < indexedItems.size()) {
            return indexedItems.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid item index selected!");
        }
    }
    
    public Item getAndUseItem(int index) {
    	Item toBeUsed = this.getItemByIndex(index);
    	this.removeItem(toBeUsed);
    	return toBeUsed;
    }
    
    public int getSize() {
    	return this.itemCounts.size();
    }
    
    public boolean checkEmpty() {
    	return this.itemCounts.isEmpty();
    }
}
