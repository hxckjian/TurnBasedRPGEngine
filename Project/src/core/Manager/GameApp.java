package core.Manager;

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
//    private StackPane[] menuPanes;
    private int currentIndex = 0;
    private MenuItem[] menuItems;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");
        VBox menuBox = new VBox(10);
        menuBox.setAlignment(Pos.CENTER);

        String[] labels = {"Attack", "Items", "Skill"};
//        menuPanes = new StackPane[labels.length];
        this.menuItems = new MenuItem[labels.length];
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            label.setFont(new Font("Arial", 16));
            label.setTextFill(Color.WHITE);

            /*
             * *** Pointer ***
             * Idle - 32px x 32px, 8 Frames
             */

            String pointerPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Pointer.png";
            SpriteAnimation pointerAnimation = SpriteAnimation.idle(pointerPath,
                    32, 32, 1,8,
                    false, false);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(pointerAnimation.getSpriteView());
            stackPane.getChildren().add(label);

            StackPane.setMargin(pointerAnimation.getSpriteView(), new Insets(0,100,0,0) );
            this.menuItems[i] = new MenuItem(stackPane, pointerAnimation);
            menuBox.getChildren().add(stackPane);
        }
        updateSelection();

        root.setBottom(menuBox);
        // Set up the scene and stage
        Scene scene = new Scene(root, 960, 720);
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));

        setupGame(root);

        primaryStage.setTitle("Turn-based RPG");
        primaryStage.setScene(scene);
        primaryStage.show();
        root.requestFocus();
    }

    private void handleKeyPress(KeyCode keyCode) {
        System.out.println("Key Pressed: " + keyCode);
        switch (keyCode) {
            case UP:
                if (currentIndex > 0) currentIndex--;
                break;
            case DOWN:
                if (currentIndex < this.menuItems.length - 1) currentIndex++;
                break;
            default:
                break;
        }
        updateSelection();
    }

    private void updateSelection() {
        for (int i = 0; i < this.menuItems.length; i++) {
            Label label = (Label) this.menuItems[i].pane.getChildren().get(1);
            if (i == currentIndex) {
//                label.setTextFill(Color.RED);
                this.menuItems[i].animation.startIdleAnimation();  // Start animation for the selected item
            } else {
//                label.setTextFill(Color.WHITE);
                this.menuItems[i].animation.removeAnimation();  // Stop animation for non-selected items
            }
        }
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
                100,200,
                64, 64, 3,14,
                true, false);
        root.getChildren().add(playerSpriteAnimation.getSpriteView());
        playerSpriteAnimation.startIdleAnimation();

        Button attackButton = new Button("Attack");
        attackButton.setLayoutX(100); // Set X position
        attackButton.setLayoutY(500); // Set Y position
        root.getChildren().add(attackButton);

        // Handling attack animation
        attackButton.setOnAction(event -> playerSpriteAnimation.startAttackAnimation(128, 6));

        Button hurtButton = new Button("Hurt");
        hurtButton.setLayoutX(160); // Set X position
        hurtButton.setLayoutY(500); // Set Y position
        root.getChildren().add(hurtButton);

        // Handling attack animation
        hurtButton.setOnAction(event -> playerSpriteAnimation.startHurtAnimation(64, 2));

        Button deathButton = new Button("Death");
        deathButton.setLayoutX(200); // Set X position
        deathButton.setLayoutY(500); // Set Y position
        root.getChildren().add(deathButton);

        // Handling attack animation
        deathButton.setOnAction(event -> playerSpriteAnimation.startDeathAnimation(128, 5));

        /*
         * *** Ghost ***
         * Idle - 64px x 64px, 13 Frames
         * Attack - 128px x 64px, 26 Frames
         * Hurt - 64px x 64px, 2 Frames
         * Death - 64px x 64px, 13 Frames
         */

        String ghostPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_idle.png";
        String ghostAttackPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_attack.png";
        String ghostHurtPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_hurt.png";
        String ghostDeathPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_death.png";
        SpriteAnimation ghostSpriteAnimation = SpriteAnimation.of(ghostPath, ghostAttackPath, ghostHurtPath, ghostDeathPath,
                600,200,
                64, 64, 3,13,
                false, false);
        root.getChildren().add(ghostSpriteAnimation.getSpriteView());
        ghostSpriteAnimation.startIdleAnimation();

        Button attackButton1 = new Button("Attack");
        attackButton1.setLayoutX(600); // Set X position
        attackButton1.setLayoutY(500); // Set Y position
        root.getChildren().add(attackButton1);

        // Handling attack animation
        attackButton1.setOnAction(event -> ghostSpriteAnimation.startAttackAnimation(128, 26));

    }

    public static void main(String[] args) {
        launch(args);
    }


}