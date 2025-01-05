package core.Manager;

import java.util.Random;

import core.Monsters.Goblin;
import core.Monsters.Monster;
import core.Monsters.Slime;
import core.Monsters.Ghost;

public class MonsterFactory {
    private static final Random RANDOM = new Random();

    // Define the pool of monsters
    private static final Monster[] MONSTER_POOL = {
            Slime.creation(),
            Goblin.creation(),
            Ghost.creation()
        // Add more monster types here
    };

//    // Method to get a random monster
//    public static Monster createRandomMonster() {
//        int randomIndex = RANDOM.nextInt(MONSTER_POOL.length);
//        Monster template = MONSTER_POOL[randomIndex];
//        return new Monster(template.toString(), template.getHealth(), template.getAttackDamage());
//    }
    
    //Randomized Attack
    public static Monster createRandomMonster() {
        int randomIndex = RANDOM.nextInt(MONSTER_POOL.length);
        Monster template = MONSTER_POOL[randomIndex];
        
        // Randomize health and attack within a range
        int randomizedHealth = template.getHealth() + RANDOM.nextInt(21) - 10; // ±10 variation
        int randomizedAttack = template.getAttackDamage() + RANDOM.nextInt(3) - 1; // ±1 variation
        
        return new Monster(template.toString(), Math.max(randomizedHealth, 1), Math.max(randomizedAttack, 1));
    }

}
