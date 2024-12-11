Turn-Based RPG Battle Manager

This repository contains a simple yet extensible Turn-Based RPG Battle Manager implemented in Java. The project demonstrates object-oriented design principles, game mechanics, and interaction loops suitable for a console-based RPG. It's designed to showcase programming skills relevant to game development, with a focus on flexibility and modularity.

Features

1. Turn-Based Combat System

Supports dynamic turn queues for both players and monsters.

Handles individual and multi-target actions.

Alternates turns between the player and enemies until victory or defeat.

2. Skill System

Implements SingleSkill for targeted attacks.

Implements WideSkill for area-of-effect (AoE) attacks.

Critical hit functionality for added randomness.

3. Item Management

Integrates an inventory system to use consumables like potions during battles.

Item effects are applied dynamically based on the item type.

4. Shop Simulation

Generates a fixed inventory for a shop where players can "purchase" items.

Ensures purchased items are added to the player’s inventory.

5. Dynamic Status Display

Displays player and enemy statuses, including health and conditions.

Prints an up-to-date battle log to keep players informed.

6. Input Validation

Handles invalid inputs (e.g., letters, out-of-range numbers).

Allows users to backtrack from menus using a "B" option.
