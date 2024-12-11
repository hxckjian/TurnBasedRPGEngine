package core.Monsters;

import core.Player;

public class Monster {
	private String name;
	private int health;
	private int attackDamage;
	
	public Monster(String name, int health, int attackDamage) {
		this.name = name;
		this.health = health;
		this.attackDamage = attackDamage;
	}
	
	public void takeDamage(int damageTaken) {
		this.health -= damageTaken;
		if (this.health < 0) {
	        this.health = 0; // Health cannot go negative
	    }
		System.out.println(this.name + " has taken " + damageTaken + " of damage!");
		
		if (this.health <= 0) {
			this.defeatedMsg();
		}
	}
	
	public void basicAttack(Player player) {
		System.out.println("Monster has used light Attack!");
		player.takeDamage(this.attackDamage);
	}
	
	public boolean isAlive() {
		return this.health != 0;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public int getAttackDamage() {
		return this.attackDamage;
	}
	
	public void defeatedMsg() {
		System.out.println(this.name + " has been defeated!");
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
