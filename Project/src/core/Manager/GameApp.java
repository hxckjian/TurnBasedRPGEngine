package core.Manager;

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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

public class GameApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 960, 720);  // Adjust size based on your game design
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        // Create and configure the progress bar
        ProgressBar healthBar = new ProgressBar(1);  // Set initial progress (50%)
        healthBar.setLayoutX(100);  // Set X position
        healthBar.setLayoutY(200);  // Set Y position
        healthBar.setPrefWidth(128);  // Preferred width to match your image dimensions
        healthBar.setPrefHeight(24);  // Preferred height, adjust to your image dimensions
        healthBar.getStyleClass().add("progress-bar");  // CSS class linkage

        root.getChildren().add(healthBar);

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

    public Timeline createAnimatedSprite(String imagePath, int frameWidth, int frameHeight, int scale, boolean flipHorizontal, boolean flipVertical, double posX, double posY, int frameCount, Pane root) {
        Image spriteSheet = new Image(imagePath, frameWidth * scale * frameCount, frameHeight * scale, true, false);
        ImageView spriteView = new ImageView(spriteSheet);

        spriteView.setPreserveRatio(true);
        spriteView.setFitWidth(frameWidth * scale);
        spriteView.setFitHeight(frameHeight * scale);

        if (flipHorizontal) {
            spriteView.setScaleX(-1);
        }
        if (flipVertical) {
            spriteView.setScaleY(-1);
        }

        spriteView.setViewport(new javafx.geometry.Rectangle2D(0, 0, frameWidth * scale, frameHeight * scale));
        spriteView.setX(posX);
        spriteView.setY(posY);

        root.getChildren().add(spriteView);

//        ProgressBar healthBar = new ProgressBar(1);
//        healthBar.setPrefWidth(200);
//        healthBar.setLayoutX(spriteView.getX());
//        healthBar.setLayoutY(spriteView.getY() - 20); // Set above the player sprite
//        root.getChildren().add(healthBar);


        Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            int index = (int) ((spriteView.getViewport().getMinX() + frameWidth * scale) / (frameWidth * scale)) % frameCount;
            spriteView.setViewport(new javafx.geometry.Rectangle2D(index * frameWidth * scale, 0, frameWidth * scale, frameHeight * scale));
        }));
        animation.setCycleCount(Animation.INDEFINITE);

        return animation;
    }

    public void loadSprites(Pane root) {

        Timeline animationPlayer = createAnimatedSprite(
                "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Player/character_ninja_idle.png",
                64, 64, 3, true, false, 100, 200, 14, root
        );
        animationPlayer.play();

        Timeline animationGhost = createAnimatedSprite(
                "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_idle.png",
                64, 64, 3, false, false, 600, 200, 13, root
        );
        animationGhost.play();
    }

    public static void main(String[] args) {
        launch(args);
    }


}