package core.Manager;

import java.util.ArrayList;
import java.util.List;

import core.Monsters.Monster;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;

public class MonsterManager {
	List<Monster> monsterList;
	private int currentIndex = 0;
	private int[] aliveList;
	
	private MonsterManager(List<Monster> monsterList) {
		this.monsterList = monsterList;
		this.aliveList = this.getAliveMonstersIndex();
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

	public int[] getAliveMonstersIndex() {
		int[] test = new int[this.monsterList.size()];
		for (int i = 0; i < this.getMonsterSize(); i++) {
			test[i] = i;
		}
		return test;
	}

	public void removeElementByIndex(int indexToRemove) {
		// Check if the index is valid
		if (indexToRemove < 0 || indexToRemove >= aliveList.length) {
			throw new IllegalArgumentException("Index out of bounds");
		}

		// Create a new array that is one element smaller
		int[] newAliveList = new int[this.aliveList.length - 1];

		// Copy elements from the original array to the new array, skipping the index to remove
		for (int i = 0, j = 0; i < this.aliveList.length; i++) {
			if (i != indexToRemove) {
				newAliveList[j++] = this.aliveList[i];
			}
		}

		// Update the aliveList reference to point to the new array
		this.aliveList = newAliveList;
	}

	public int getALiveListLength() {
		return this.aliveList.length;
	}

	public int getMonsterSize() {
		return this.monsterList.size();
	}

	public void handleKeyPress(KeyCode keyCode) {
		System.out.println(keyCode);
		System.out.println("Selected Index: " + currentIndex );
		switch (keyCode) {
			case UP:
				if (currentIndex > 0)  {
					currentIndex--;
				}
				break;
			case DOWN:
				if (currentIndex < this.getALiveListLength() - 1) currentIndex++;
				break;
			case ENTER:
//				executeMenuItem(currentIndex);
				//ATTACKKKKKKKKK!!!!!!
				HandleManager.getInstance().playerAttacksEnemy(currentIndex);
				break;
			case ESCAPE:
				HandleManager.getInstance().MenuSelection();
				break;
			default:
				// Handle other keys if necessary
				break;
		}
		if (keyCode != KeyCode.ESCAPE && keyCode != KeyCode.ENTER) {
			this.updateSelection();
		} else {
			this.currentIndex = 0;
			this.unselectAll();
		}
	}

	private void updateSelection() {
		System.out.println(this.aliveList[currentIndex]);
		for (int i = 0; i < this.getALiveListLength(); i++) {
//            Label label = menuItems[i].getLabel();
			if (i == currentIndex) {
				this.getSpecificMonster(this.aliveList[currentIndex]).selectedMonster();
			} else {
				this.getSpecificMonster(this.aliveList[i]).unselectedMonster();
			}
		}
	}

	private void unselectAll() {
		for (int i = 0; i < this.getMonsterSize(); i++) {
			this.getSpecificMonster(i).unselectedMonster();
		}
	}

	public int[] getAliveList() {
		return this.aliveList;
	}

	public void selectFirstAlive() {
		if (!this.checkAllDead()) {
			this.getSpecificMonster(this.aliveList[0]).selectedMonster();
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
