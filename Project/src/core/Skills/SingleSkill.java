package core.Skills;

public class SingleSkill extends Skill {
	private SingleSkill(String skillName, int attackDamage, int manaCost) {
		super(skillName, attackDamage, manaCost);
	}
	
	public static SingleSkill creation(String skillName, int attackDamage, int manaCost) {
		return new SingleSkill(skillName, attackDamage, manaCost);
	}
	
	@Override
	public String toString() {
		return " | Single Target" + super.toString();
	}
}
