package core.Skills;

public class WideSkill extends Skill {
	private WideSkill(String skillName, int attackDamage, int manaCost) {
		super(skillName, attackDamage, manaCost);
	}
	
	public static WideSkill creation(String skillName, int attackDamage, int manaCost) {
		return new WideSkill(skillName, attackDamage, manaCost);
	}
	
	@Override
	public String toString() {
	    return " | Wide Target" + super.toString();
	}
}
