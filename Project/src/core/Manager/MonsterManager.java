package core.Manager;

import java.util.ArrayList;
import java.util.List;

import core.Monsters.Monster;
import javafx.scene.input.KeyCode;

public class MonsterManager {
	List<Monster> monsterList;
	private int currentIndex = 0;
	
	private MonsterManager(List<Monster> monsterList) {
		this.monsterList = monsterList;
	}
	
//	//Testing of fixed creation
//	public static MonsterManager of(core.Monsters.Monster... monsterArgs) {
//		List<Monster> monsterList = Arrays.asList(monsterArgs);
//		return new MonsterManager(monsterList);
//	}
	
	// create a randomized list of monsters
	public static MonsterManager createRandomMonsterManager(int numMonsters) {
	    List<Monster> randomMonsters = new ArrayList<>();
	    for (int i = 0; i < numMonsters; i++) {
	        randomMonsters.add(MonsterFactory.createRandomMonster());
	    }
	    return new MonsterManager(randomMonsters);
	}

	
	public List<Monster> getAllMonsters() {
		return this.monsterList;
	}
	
	public int getMonsterSize() {
		return this.monsterList.size();
	}

	public void handleKeyPress(KeyCode keyCode) {
//		this.getSpecificMonster().selectedMonster();
		switch (keyCode) {
			case UP:
				if (currentIndex > 0) currentIndex--;
				break;
			case DOWN:
				if (currentIndex < this.getMonsterSize() - 1) currentIndex++;
				break;
			case ENTER:
				System.out.println("KILL HIMM!!!");
//				executeMenuItem(currentIndex);
				break;
			default:
				// Handle other keys if necessary
				break;
		}
		this.updateSelection();
	}

	private void updateSelection() {
		for (int i = 0; i < this.getMonsterSize(); i++) {
//            Label label = menuItems[i].getLabel();
			if (i == currentIndex) {
//                label.setTextFill(Color.RED); // Highlight the selected item
//				System.out.println("Hi");
//				System.out.println(i);
//				this.getSpecificMonster(i).getPointerAnimation().startIdleAnimation();
				this.getSpecificMonster(i).selectedMonster();
			} else {
//                label.setTextFill(Color.WHITE); // Revert others to default color
				this.getSpecificMonster(i).unselectedMonster();
			}
		}
	}
	
	public Monster getSpecificMonster(int target) {
		return this.monsterList.get(target);
	}
	
	public String generateMonsterStatus() {
		String text = "";
		for (int i = 0; i < this.monsterList.size(); i++) {
			String aliveText = "";
			if (this.monsterList.get(i).isAlive()) {
				aliveText = "Alive";
			} else {
				aliveText = "Dead";
			}
			text += i + " | " + this.getSpecificMonster(i).toString() + " | Health: " 
			+ this.getSpecificMonster(i).getHealth() + " | "
			+ aliveText +  "\n";
		}
		return text;
	}
	
	// monsters.stream().anyMatch(Monster::isAlive)
	public boolean checkAllDead() {
		for (int i = 0; i < this.monsterList.size(); i++) {
			if (this.getSpecificMonster(i).isAlive()) {
				return false;
			}
		}
		return true;
	}
}
