package core.Manager;

import core.Monsters.Monster;
import core.Player;
import javafx.scene.Scene;

import java.awt.*;

public class HandleManager {
    private Scene scene;
    private MenuManager menuManager;
    private MonsterManager monsterManager;
    private PlayerManager playerManager;

    private static HandleManager instance;

    private HandleManager(Scene scene, MenuManager menuManager, MonsterManager monsterManager, PlayerManager playerManager) {
        this.scene = scene;
        this.menuManager = menuManager;
        this.monsterManager = monsterManager;
        this.playerManager = playerManager;
    }

    // Public method to initialize the instance
    public static synchronized void initialize(Scene scene, MenuManager menuManager, MonsterManager monsterManager, PlayerManager playerManager) {
        if (HandleManager.instance != null) {
            throw new IllegalStateException("Instance already initialized.");
        }
        HandleManager.instance = new HandleManager(scene, menuManager, monsterManager, playerManager);
    }

    // Public method to get the instance of the class
    public static HandleManager getInstance() {
        if (HandleManager.instance == null) {
            throw new IllegalStateException("Instance not initialized.");
        }
        return HandleManager.instance;
    }

    public void MonsterSelection() {
        //Auto Select First Monster
        this.monsterManager.selectFirstMonster();
        this.scene.setOnKeyPressed(event -> this.monsterManager.handleKeyPress(event.getCode()));
    }

    public void MenuSelection() {
        //Auto select first
        this.menuManager.selectFirstOption();
        this.scene.setOnKeyPressed(event -> this.menuManager.handleKeyPress(event.getCode()));
    }

    public void playerAttacksEnemy(int targetIndex) {
        Player player = this.playerManager.getSpecificPlayer(0);
        Monster target = this.monsterManager.getSpecificMonster(targetIndex);
        player.basicAttack(target);

        //Return to Menu Selection
        this.MenuSelection();
    }

    public Scene getScene() {
        return this.scene;
    }

    public MonsterManager getMonsterManager() {
        return this.monsterManager;
    }

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public MenuManager getMenuManager() {
        return this.menuManager;
    }

}
