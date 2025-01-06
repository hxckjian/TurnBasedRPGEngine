package core.Manager;

import core.Monsters.Monster;
import core.Player;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import java.net.URL;

public class GameApp extends Application {
    private int currentIndex = 0;
    private MonsterManager monsterManager;
    public Scene scene = null;
//    private MenuItem[] menuItems;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");

        this.initialize(root);

        setupGame(root);

        root.setBottom(HandleManager.getInstance().getMenuManager().getMenuBox());

        // Set up the scene and stage
        HandleManager.getInstance().MenuSelection();

        primaryStage.setTitle("Turn-based RPG");
        primaryStage.setScene(scene);
        primaryStage.show();
        root.requestFocus();
    }

    //TO BE EDITED IF NEEDED
    private void initialize(Pane root) {
        scene = new Scene(root, 960, 720);
        MonsterManager monsterManager = MonsterManager.createRandomMonsterManager(2);
        PlayerManager playerManager = PlayerManager.of(new Player());
        String[] menuOptions = {"Attack", "Items", "Skill"};
        MenuManager menuManager = new MenuManager(menuOptions, Pos.CENTER, 10,
                monsterManager);
        HandleManager.initialize(scene, menuManager, monsterManager, playerManager);
    }

    private void setupGame(Pane root) {
        // Initialize your game managers and pass them to the controllers/views
        core.Manager.BattleManager battleManager = core.Manager.BattleManager.createBattle();

        // Assuming you have a method to setup graphical components
        loadSprites(root);

        // Here you could add buttons, status displays, etc.
    }

    public void loadSprites(Pane root) {
        /*
         * *** Player ***
         * Idle - 64px x 64px, 14 Frames
         * Attack - 128px x 64px, 6 Frames
         * Hurt - 64px x 64px, 2 Frames
         * Death - 128px x 64px, 5 Frames
         */

        String playerPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Player/character_ninja_idle.png";
        String playerAttackPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Player/character_ninja_basicattack.png";
        String playerHurtPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Player/character_ninja_hurt.png";
        String playerDeathPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Player/character_ninja_death.png";
        SpriteAnimation playerSpriteAnimation = SpriteAnimation.of(playerPath, playerAttackPath, playerHurtPath, playerDeathPath,
//                100,200,
                64, 64, 3,14,
                true, false);
        root.getChildren().add(playerSpriteAnimation.getSpriteView());
        playerSpriteAnimation.startIdleAnimation();

//        Button attackButton = new Button("Attack");
//        attackButton.setLayoutX(100); // Set X position
//        attackButton.setLayoutY(500); // Set Y position
//        root.getChildren().add(attackButton);
//
//        // Handling attack animation
//        attackButton.setOnAction(event -> playerSpriteAnimation.startAttackAnimation(128, 6));
//
//        Button hurtButton = new Button("Hurt");
//        hurtButton.setLayoutX(160); // Set X position
//        hurtButton.setLayoutY(500); // Set Y position
//        root.getChildren().add(hurtButton);
//
//        // Handling attack animation
//        hurtButton.setOnAction(event -> playerSpriteAnimation.startHurtAnimation(64, 2));
//
//        Button deathButton = new Button("Death");
//        deathButton.setLayoutX(200); // Set X position
//        deathButton.setLayoutY(500); // Set Y position
//        root.getChildren().add(deathButton);
//
//        // Handling attack animation
//        deathButton.setOnAction(event -> playerSpriteAnimation.startDeathAnimation(128, 5));

        /*
         * *** Ghost ***
         * Idle - 64px x 64px, 13 Frames
         * Attack - 128px x 64px, 26 Frames
         * Hurt - 64px x 64px, 2 Frames
         * Death - 64px x 64px, 13 Frames
         */

//        String ghostPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_idle.png";
//        String ghostAttackPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_attack.png";
//        String ghostHurtPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_hurt.png";
//        String ghostDeathPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_death.png";
//        SpriteAnimation ghostSpriteAnimation = SpriteAnimation.of(ghostPath, ghostAttackPath, ghostHurtPath, ghostDeathPath,
////                0,0,
//                64, 64, 3,13,
//                false, false);
////        String pointerPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Pointer.png";
////        SpriteAnimation pointerAnimation = SpriteAnimation.idle(pointerPath,
////                32, 32, 2,8,
////                false, false);
//        SpriteAnimation pointerAnimation = SpriteAnimation.pointerAnimation(2, false, false);
//        ImageView pixelArtView = ghostSpriteAnimation.getSpriteView();
//        ImageView pointerView = pointerAnimation.getSpriteView();
//        HBox hbox = new HBox(0); // Horizontal spacing between the views
//        hbox.setAlignment(Pos.CENTER_LEFT); // Ensures vertical centering of children
//
//        // Create and configure the pixelArtView
////        pixelArtView.setFitHeight(100); // Example height, adjust as needed
//        pixelArtView.setPreserveRatio(true);
//
//        // Create and configure the pointerView
//        pointerView.setPreserveRatio(true);
//        pointerView.setTranslateX(50); // Adjust this value to control the overlap
//        pointerView.setTranslateY(0);
//        // Add both ImageView to the HBox
//        hbox.getChildren().addAll(pointerView, pixelArtView);
//        hbox.setLayoutX(600);
//        hbox.setLayoutY(200);
//        root.getChildren().add(hbox);
//        ghostSpriteAnimation.startIdleAnimation();
//        pointerAnimation.startIdleAnimation();


        //SECOND MONSTER
//        SpriteAnimation ghostSpriteAnimation1 = SpriteAnimation.of(ghostPath, ghostAttackPath, ghostHurtPath, ghostDeathPath,
////                600,350,
//                64, 64, 3,13,
//                false, false);
//        root.getChildren().add(ghostSpriteAnimation1.getSpriteView());
//        ghostSpriteAnimation1.startIdleAnimation();

//        Button attackButton1 = new Button("Attack");
//        attackButton1.setLayoutX(600); // Set X position
//        attackButton1.setLayoutY(500); // Set Y position
//        root.getChildren().add(attackButton1);
//
//        // Handling attack animation
//        attackButton1.setOnAction(event -> ghostSpriteAnimation.startAttackAnimation(128, 26));
        setupMonsters(root);

    }

    public void setupMonsters(Pane root) {
        int[] monsterPositions = {200, 500};
        for (int i = 0; i < 2; i++) {
            Monster currentMonster = HandleManager.getInstance().getMonsterManager().getSpecificMonster(i);
            SpriteAnimation monsterAnimation = currentMonster.getMonsterAnimation();
            SpriteAnimation pointerAnimation = currentMonster.getPointerAnimation();
            ImageView pixelArtView = monsterAnimation.getSpriteView();
            ImageView pointerView = pointerAnimation.getSpriteView();

            HBox hbox = new HBox(0); // Horizontal spacing between the views
            hbox.setAlignment(Pos.CENTER_LEFT); // Ensures vertical centering of children

            // Create and configure the pixelArtView
//        pixelArtView.setFitHeight(100); // Example height, adjust as needed
            pixelArtView.setPreserveRatio(true);

            // Create and configure the pointerView
            pointerView.setPreserveRatio(true);
            pointerView.setTranslateX(50); // Adjust this value to control the overlap
            pointerView.setTranslateY(0);
            // Add both ImageView to the HBox
            hbox.getChildren().addAll(pointerView, pixelArtView);

            monsterAnimation.startIdleAnimation();
//            pointerAnimation.startIdleAnimation();
//            currentMonster.getPointerAnimation().startIdleAnimation();

            //Position
            hbox.setLayoutX(600);
            hbox.setLayoutY(monsterPositions[i]);
            root.getChildren().add(hbox);

        }

    }

    public static void main(String[] args) {
        launch(args);
    }


}