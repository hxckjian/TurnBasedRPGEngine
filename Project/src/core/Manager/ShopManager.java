package core.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.Items.Item;
import core.Items.ItemPool;

public class ShopManager {
    private final List<Item> shopInventory;

    public ShopManager() {
        this.shopInventory = new ArrayList<>();
    }

    // Generate a fixed inventory for the shop
    public void generateFixedInventory(List<String> itemKeys) {
        shopInventory.clear();
        for (String key : itemKeys) {
            shopInventory.add(ItemPool.getItem(key));
        }
    }

    // Generate a random inventory from the pool
    public void generateRandomInventory(int itemCount) {
        shopInventory.clear();
        List<String> allKeys = new ArrayList<>(ItemPool.getAllItems().keySet());
        Random random = new Random();
        for (int i = 0; i < itemCount; i++) {
            String randomKey = allKeys.get(random.nextInt(allKeys.size()));
            shopInventory.add(ItemPool.getItem(randomKey));
        }
    }

    public void displayShop() {
        System.out.println("---- Shop Inventory ----");
        for (int i = 0; i < shopInventory.size(); i++) {
            Item item = shopInventory.get(i);
            System.out.println(i + " | " + item.getName() + " + " + item.getDescription());
        }
    }

    public Item buyItem(int index) {
        if (index < 0 || index >= shopInventory.size()) {
            throw new IndexOutOfBoundsException("Invalid shop item index!");
        }
        return shopInventory.get(index);
    }
}
