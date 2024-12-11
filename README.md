# Turn-Based RPG Battle Manager

This repository contains a simple yet extensible Turn-Based RPG Battle Manager implemented in Java. The project demonstrates object-oriented design principles, game mechanics, and interaction loops suitable for a console-based RPG. It's designed to showcase programming skills relevant to game development, with a focus on flexibility and modularity.

Features

### Turn-Based Combat System

Supports dynamic turn queues for both players and monsters.

Handles individual and multi-target actions.

Alternates turns between the player and enemies until victory or defeat.

### Skill System

Implements SingleSkill for targeted attacks.

Implements WideSkill for area-of-effect (AoE) attacks.

Critical hit functionality for added randomness.

### Item Management

Integrates an inventory system to use consumables like potions during battles.

Item effects are applied dynamically based on the item type.

### Dynamic Status Display

Displays player and enemy statuses, including health and conditions.

Prints an up-to-date battle log to keep players informed.

### Input Validation

Handles invalid inputs (e.g., letters, out-of-range numbers).

Allows users to backtrack from menus using a "B" option.

### Future Implementation

A shop system where players can buy skills, mana potions, and party members.

## How It Works

### Battle Flow

The player and monsters are added to a turn queue.

Each entity in the queue takes a turn until one side is defeated.

The player can:

**Basic Attack:** Target a specific enemy.

**Use Items:** Heal or restore resources.

**View Status:** Display enemy and player information.

**Use Skills:** Perform advanced attacks, including AoE damage.

### Key Classes

**BattleManager:** Orchestrates the battle flow, including turns and input handling.

**Player:** Represents the player, with health, mana, and a skill set.

**Monster:** Represents enemies with basic attack logic.

**Skill:** Abstract class extended by SingleSkill and WideSkill.

**ItemManager:** Manages the player’s inventory.

**MonsterManager:** Handles enemy creation and interactions.

### Example Console Output
```
----Battle Start----

Player1's Turn!
Choose your action:
0 | Basic Attack
1 | Use Items
2 | View Enemies
3 | View Players' Statuses
4 | Use Skills

Enter your choice: 0
****Enemies' Status****
0 | Slime | Health: 50 | Alive
1 | Goblin | Health: 70 | Alive
Select a target:
B | Go Back
0
Player1 attacks Slime for 20 damage.

Slime's Turn!
Slime attacks Player1 for 10 damage.
```

### Project Structure
```
core/
├── Manager/
│   ├── BattleManager.java
│   ├── MonsterManager.java
│   ├── PlayerManager.java
│   └── ItemManager.java
├── Items/
│   ├── Item.java
│   ├── Potion.java
│   └── ItemFactory.java
├── Skills/
│   ├── Skill.java
│   ├── SingleSkill.java
│   └── WideSkill.java
├── Monsters/
│   ├── Monster.java
│   ├── Slime.java
│   └── Goblin.java
└── Player.java
```

### Extensibility

This project is designed to be extensible for additional features:

**New Monster Types:** Add new monster classes to the Monsters package.

**Additional Skills:** Create new skill subclasses with unique behaviors.

**Expanded Item System:** Add items with different effects.

**Graphical Interface:** Integrate a GUI or transition to a game engine.

## Author

Developed by [Teh Hock Jian].
