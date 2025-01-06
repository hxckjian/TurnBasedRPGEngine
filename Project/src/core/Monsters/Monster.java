package core.Monsters;

import core.Manager.SpriteAnimation;
import core.Player;

public class Monster {
	private String name;
	private int health;
	private int attackDamage;
	private SpriteAnimation pointerAnimation = SpriteAnimation.pointerAnimation(2,false,false);
	private SpriteAnimation monsterAnimation;
	
	public Monster(String name, int health, int attackDamage, SpriteAnimation monsterAnimation) {
		this.name = name;
		this.health = health;
		this.attackDamage = attackDamage;
		this.monsterAnimation = monsterAnimation;
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

	//Immutable
	public SpriteAnimation getImmutableMonsterAnimation() {
		return new SpriteAnimation(this.monsterAnimation);
	}

	public SpriteAnimation getMonsterAnimation() {
		return this.monsterAnimation;
	}

	public SpriteAnimation getPointerAnimation() {
		return this.pointerAnimation;
	}
	
	public void defeatedMsg() {
		System.out.println(this.name + " has been defeated!");
	}

	public void selectedMonster() {
		this.pointerAnimation.startIdleAnimation();
	}

	public void unselectedMonster() {
		this.pointerAnimation.removeAnimation();
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
