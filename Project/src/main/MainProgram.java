package main;

import java.util.Arrays;

import core.Items.Item;
import core.Items.Potion;
import core.Manager.ItemManager;
import core.Manager.ShopManager;

public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		core.Manager.BattleManager test = core.Manager.BattleManager.createBattle();
		System.out.println("Start");
		try {
			test.startBattleSequence();
		} catch (Exception e) {
			
		}
//		ItemManager playerInventory = new ItemManager();
//        ShopManager shop = new ShopManager();
//
//        // Generate a fixed shop inventory
//        shop.generateFixedInventory(Arrays.asList("Potion_50HP", "Potion_100HP", "ManaPotion_30MP"));
//        shop.displayShop();
//
//        // Player buys a potion
//        Item boughtItem = shop.buyItem(0); // Buys the first item
//        playerInventory.addItem(boughtItem);
//        playerInventory.addItem(boughtItem);
//        playerInventory.addItem(shop.buyItem(1));
//
//        // Show player inventory
//        playerInventory.showInventoryWithIndices();
//
//        // Use the potion
////        if (boughtItem instanceof Potion) {
////            Potion potion = (Potion) boughtItem;
////            System.out.println("Using potion that heals " + potion.getHealingAmount() + " HP!");
////        }
//        
//        System.out.println(playerInventory.getItemByIndex(1));
//        System.out.println(playerInventory.getSize());

	}

}
