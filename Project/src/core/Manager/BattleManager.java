package core.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import core.Player;
import core.Items.Item;
import core.Monsters.Monster;
import core.Skills.SingleSkill;
import core.Skills.Skill;
import core.Skills.WideSkill;

public class BattleManager {
	private final List<Object> turnQueue;
	private final MonsterManager monsterManager;
	private final PlayerManager playerManager;
	private final ItemManager itemManager;
	private final Scanner scanner;

	private BattleManager(MonsterManager monsterManager, PlayerManager playerManager, ItemManager itemManager) {
		this.monsterManager = monsterManager;
		this.playerManager = playerManager;
		this.itemManager = itemManager;
		this.scanner = new Scanner(System.in);
		this.turnQueue = this.initializeTurnQueue();
	}

	private List<Object> initializeTurnQueue() {
		List<Object> queue = new ArrayList<>();
		queue.add(this.playerManager.getSpecificPlayer(0));
		queue.addAll(this.monsterManager.getAllMonsters());
		return queue;
	}

	public static BattleManager createBattle() {
		MonsterManager monsterManager = MonsterManager.createRandomMonsterManager(2);
		PlayerManager playerManager = PlayerManager.of(new Player());
		ItemManager itemManager = new ItemManager();

		ShopManager shop = new ShopManager();
		shop.generateFixedInventory(Arrays.asList(
				"HealthPotion_50HP",
				"HealthPotion_100HP",
				"ManaPotion_30MP",
				"Elixir_MAX"));
		shop.displayShop();

		Item boughtItem = shop.buyItem(0);
		itemManager.addItem(boughtItem);
		itemManager.addItem(boughtItem);
		
		itemManager.addItem(shop.buyItem(2));
		itemManager.addItem(shop.buyItem(3));

		return new BattleManager(monsterManager, playerManager, itemManager);
	}

	private int promptInput(String message, int min, int max) {
		while (true) {
			try {
				System.out.println(message);
				String input = this.scanner.next().trim();
				if (input.equalsIgnoreCase("B"))
					return -1;
				int choice = Integer.parseInt(input);
				if (choice >= min && choice <= max)
					return choice;
				System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number or 'B' to go back.");
			}
		}
	}

	private void handlePlayerAction() throws InterruptedException {
		boolean playerTurn = true;
		while (playerTurn) {
			System.out.println("Choose your action:");
			System.out.println(
					"0 | Basic Attack\n1 | Use Items\n2 | View Enemies\n3 | View Players' Statuses\n4 | Use Skills\n");
			int choice = this.promptInput("Enter your choice: ", 0, 4);
			switch (choice) {
			case 0:
				playerTurn = this.handleBasicAttack();
				break;
			case 1:
				playerTurn = this.handleItemUsage();
				break;
			case 2:
				this.printMonsterStatus();
				break;
			case 3:
				this.printPlayerStatus();
				break;
			case 4:
				playerTurn = this.handleSkillUsage();
				break;
			default:
				System.out.println("Unexpected error.");
			}
		}
	}

	private boolean handleBasicAttack() {
		printMonsterStatus();
		int targetIndex = this.promptInput("Select a target:\nB | Go Back", 0, this.monsterManager.getMonsterSize() - 1);
		if (targetIndex == -1)
			return true;
		Monster target = this.monsterManager.getSpecificMonster(targetIndex);
		this.playerManager.getSpecificPlayer(0).basicAttack(target);
		this.queueChecker(target);
		return false;
	}

	private boolean handleItemUsage() {
		if (this.itemManager.checkEmpty()) {
			System.out.println("No items available.");
			return true;
		}
		this.showInventory();
		int itemIndex = this.promptInput("Select an item:\nB | Go Back", 0, itemManager.getSize() - 1);
		if (itemIndex == -1)
			return true;
		Item item = this.itemManager.getAndUseItem(itemIndex);
		this.playerManager.getSpecificPlayer(0).useItem(item);
		return false;
	}

	private boolean handleSkillUsage() throws InterruptedException {
		if (this.playerManager.getSpecificPlayer(0).checkEmptySkillTab())
			return true;
		while (true) { // Loop until a valid action is completed
			System.out.println(this.playerManager.getSpecificPlayer(0).viewSkills() + "B | Go Back");
			int skillIndex = promptInput("Select a skill:", 0,
					this.playerManager.getSpecificPlayer(0).getAllSkills().size() - 1);
			if (skillIndex == -1)
				return true; // Return to action menu if "B" is chosen

			if (this.executeSkill(skillIndex)) {
				return false; // Skill successfully executed, end the player's turn
			}
		}
	}

	private boolean executeSkill(int skillIndex) throws InterruptedException {
		Skill skill = this.playerManager.getSpecificPlayer(0).getSpecificSkill(skillIndex);
		if (!this.playerManager.getSpecificPlayer(0).checkManaAvailable(skill)) {
			System.out.println("Not enough mana!\n");
			return false;
		}
		if (skill instanceof SingleSkill) {
			while (true) { // Loop until a valid target is chosen or "B" is pressed
				this.printMonsterStatus();
				int targetIndex = this.promptInput("Select a target:\nB | Go Back", 0, monsterManager.getMonsterSize() - 1);
				if (targetIndex == -1)
					return false; // Return to skill menu if "B" is chosen
				Monster target = this.monsterManager.getSpecificMonster(targetIndex);
				this.playerManager.getSpecificPlayer(0).singleTargetAttack(target, skillIndex);
				this.queueChecker(target);
				return true; // Successfully executed the skill
			}
		} else if (skill instanceof WideSkill) {
			List<Monster> aliveMonsters = this.monsterManager.getAllMonsters().stream().filter(Monster::isAlive)
					.collect(Collectors.toList());
			this.playerManager.getSpecificPlayer(0).multiTargetAttack(aliveMonsters, skillIndex);
			return true; // Successfully executed the skill
		}
		return false; // In case of unexpected behavior
	}

	public void startBattleSequence() throws InterruptedException {
		System.out.println("----Battle Start----");
		while (this.playerManager.getSpecificPlayer(0).isAlive() && !this.monsterManager.checkAllDead()) {
			for (Object entity : new ArrayList<>(this.turnQueue)) {
				if (entity instanceof Player) {
					Player player = (Player) entity;
					if (player.isAlive()) {
						System.out.println("\nPlayer's Turn!");
						this.handlePlayerAction();
					}
				} else if (entity instanceof Monster) {
					Monster monster = (Monster) entity;
					if (monster.isAlive()) {
						System.out.println("\n" + monster + "'s Turn!");
						this.monsterAction(monster);
					}
				}
				if (!this.playerManager.getSpecificPlayer(0).isAlive()) {
					System.out.println("\nPlayer has been defeated! Game Over.");
					return;
				}
				if (this.monsterManager.checkAllDead()) {
					System.out.println("\nAll monsters have been defeated! Victory!");
					return;
				}
			}
		}
	}

	private void monsterAction(Monster monster) throws InterruptedException {
		Player target = this.playerManager.getSpecificPlayer(0);
		monster.basicAttack(target);
		this.queueChecker(target);
		Thread.sleep(2000);
	}

	private void queueChecker(Object entity) {
		if (entity instanceof Player) {
			Player player = (Player) entity;
			if (!player.isAlive())
				this.turnQueue.remove(player);
		}
		if (entity instanceof Monster) {
			Monster monster = (Monster) entity;
			if (!monster.isAlive())
				this.turnQueue.remove(monster);
		}
	}

	private void printMonsterStatus() {
		System.out.println("****Enemies' Status****\n" + this.monsterManager.generateMonsterStatus());
	}

	private void printPlayerStatus() {
		System.out.println("****Players' Status****\n" + this.playerManager.generatePlayerStatus());
	}

	public void showInventory() {
		this.itemManager.showInventoryWithIndices();
	}
}
