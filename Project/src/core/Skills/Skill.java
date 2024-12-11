package core.Skills;

public class Skill {
	private String skillName;
	private int attackDamage;
	private int manaCost;
	
	public Skill(String skillName, int attackDamage, int manaCost) {
		this.skillName = skillName;
		this.attackDamage = attackDamage;
		this.manaCost = manaCost;
	}
	
	public String getSkillName() {
		return this.skillName;
	}
	
	public int getDamage() {
		return this.attackDamage;
	}
	
	public int getManaCost() {
		return this.manaCost;
	}
	
	@Override
	public String toString() {
		return " | Name: " + this.skillName 
				+ " | Attack Damage: " + this.attackDamage
				+ " | Mana Cost: " + this.manaCost;
	}
}
