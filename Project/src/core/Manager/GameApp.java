package core.Manager;

import core.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import java.net.URL;

public class GameApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 960, 720);  // Adjust size based on your game design

        setupGame(root);

        primaryStage.setTitle("Turn-based RPG");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupGame(Pane root) {
        // Initialize your game managers and pass them to the controllers/views
        core.Manager.BattleManager battleManager = core.Manager.BattleManager.createBattle();

        // Assuming you have a method to setup graphical components
        loadSprites(root);

        // Here you could add buttons, status displays, etc.
    }

    public void loadSprites(Pane root) {
        String playerPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Player/character_ninja_idle.png";
        String playerAttackPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Player/character_ninja_basicattack.png";
        SpriteAnimation playerSpriteAnimation = SpriteAnimation.of(playerPath, playerAttackPath,
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

        String ghostPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_idle.png";
        String ghostAttackPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Player/character_ghost_attack.png";
        SpriteAnimation ghostSpriteAnimation = SpriteAnimation.of(ghostPath, ghostAttackPath,
                600,200,
                64, 64, 3,13,
                false, false);
        root.getChildren().add(ghostSpriteAnimation.getSpriteView());
        ghostSpriteAnimation.startIdleAnimation();
    }

    public static void main(String[] args) {
        launch(args);
    }


}