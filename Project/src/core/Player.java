package core;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.Items.Item;
import core.Items.Potion;
import core.Monsters.Monster;
import core.Skills.SingleSkill;
import core.Skills.Skill;
import core.Skills.WideSkill;

public class Player {
	private String name;
	private int health;
	private int attackDamage;
	private int defense;
	private int speed;
	private int mana;
	private int gold;
	private double criticalChance;
	
	//Player has a list of skills
	private List<Skill> skillTab;
	
	public Player() {
		this.name = "Player1";
		this.health = 100;
		this.attackDamage = 20;
		this.defense = 10;
		this.speed = 10;
		this.mana = 100;
		this.gold = 0;
		this.criticalChance = 0.2;
		this.skillTab = new ArrayList<Skill>();
		
		this.initializeDefaultSkill();
	}
	
	private void initializeDefaultSkill() {
		this.skillTab.add(SingleSkill.creation("Single Origin", 25, 25));
		this.skillTab.add(WideSkill.creation("All-Roundly Slash", 10, 35));
	}
	
	public void takeDamage(int damageTaken) {
		this.health -= damageTaken;
		if (this.health < 0) {
	        this.health = 0; // Health cannot go negative
	    }
		System.out.println(this.name + " has taken " + damageTaken + " of damage!");
		
		if (this.health == 0) {
			System.out.println("You have fallen!");
		}
		System.out.println(this.name + " is left with " + this.health + " of health!");
	}
	
	public void heal(int healthTaken) {
		this.health += healthTaken;
		if (this.health > 100) { // Assume 100 is the max health
	        this.health = 100;
	    }
		System.out.println(this.name + " has gain " + healthTaken + " of health!");
	}
	
	public void basicAttack(Monster monster) {
		System.out.println("Player has used light Attack!");
		int damageDealt = this.addCriticalDamage(this.attackDamage);
	    monster.takeDamage(damageDealt);
	    System.out.println(this.name + " has dealt " + damageDealt + " damage to " + monster.toString());
	}
	
	public void useItem(Item item) {
        if (item instanceof Potion) {
            Potion potion = (Potion) item;
            this.heal(potion.getHealingAmount());
            System.out.println(this.name + " has used " + potion.getName());
        } else {
            System.out.println("Item cannot be used.");
        }
    }
	
	public void singleTargetAttack(Monster monster, int skillIndex) throws InterruptedException {
		Skill useSkill = this.getSpecificSkill(skillIndex);
		System.out.println(this.name + " has used " + useSkill.getSkillName() + "!");
		int damageDealt = this.addCriticalDamage(this.attackDamage + useSkill.getDamage());
		
		Thread.sleep(3000);
		
		this.useMana(useSkill.getManaCost());
		monster.takeDamage(damageDealt);
//		System.out.println(this.name + " has dealt " + damageDealt + " damage to " + monster.toString());
	}
	
	public void multiTargetAttack(List<Monster> monsterList, int skillIndex) throws InterruptedException {
		Skill useSkill = this.getSpecificSkill(skillIndex);
		System.out.println(this.name + " has used " + useSkill.getSkillName() + "!");
		int damageDealt = this.addCriticalDamage(this.attackDamage + useSkill.getDamage());
		
		Thread.sleep(3000);
		
		this.useMana(useSkill.getManaCost());
		monsterList.stream().forEach(monster -> monster.takeDamage(damageDealt));
//		System.out.println(this.name + " has dealt " + damageDealt + " damage to all monsters!");
	}
	
	public String viewSkills() {
		String text = "****All Skills****\n";
		for (int i = 0; i < this.skillTab.size(); i++) {
			text += i + this.skillTab.get(i).toString() + "\n";
		}
		return text;
	}
	
	public int addCriticalDamage(int currentDamage) {
		Random random = new Random();
	    boolean isCritical = random.nextDouble() < this.criticalChance;

	    int damageDealt = currentDamage;
	    if (isCritical) {
	        damageDealt *= 2; // Critical hit deals double damage
	        System.out.println("Critical hit! Damage is doubled!");
	    }
	    return damageDealt;
	}
	
	public boolean checkEmptySkillTab() {
		if (this.skillTab.isEmpty()) {
			System.out.println("No skills available");
			return true;
		}
		return false;
	}
	
	public void useMana(int manaCost) {
		this.mana -= manaCost;
		if (this.mana < 0) {
			this.mana = 0;
		}
	}
	
	public List<Skill> getAllSkills() {
		return this.skillTab;
	}
	
	public boolean checkManaAvailable(Skill skill) {
		return this.mana > skill.getManaCost();
	}
	
	public Skill getSpecificSkill(int index) {
		return this.skillTab.get(index);
	}
	
	public boolean isAlive() {
		return this.health != 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getMana() {
		return this.mana;
	}
}
