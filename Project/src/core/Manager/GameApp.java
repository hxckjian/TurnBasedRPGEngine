package core.Manager;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);  // Adjust size based on your game design

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
        Image spriteSheet = new Image("file:/Users/hockjianteh/intellij turn-based-rpg/" +
                "TurnBasedRPGEngine/Project/" +
                "artwork/Player/character_ninja_idle.png");
        ImageView spriteView = new ImageView(spriteSheet);
        spriteView.setScaleX(-2.0); // Doubles the width of the image
        spriteView.setScaleY(2.0); // Doubles the height of the image
        final int count = 14;  // Number of frames in the animation
        final int columns = 14;  // Number of columns in the sprite sheet
        final int offsetX = 0;  // X offset in the sprite sheet
        final int offsetY = 0;  // Y offset in the sprite sheet
        final int width = 64;  // Width of each frame
        final int height = 64;  // Height of each frame

        spriteView.setViewport(new javafx.geometry.Rectangle2D(offsetX, offsetY, width, height));
        spriteView.setX(100);  // Position on the pane
        spriteView.setY(200);

        root.getChildren().add(spriteView);

        // Create an animation timeline
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(100), e -> {
                    int index = (int) ((spriteView.getViewport().getMinX() + width) / width) % columns;
                    spriteView.setViewport(new javafx.geometry.Rectangle2D(index * width, offsetY, width, height));
                })
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    public static void main(String[] args) {
        launch(args);
    }


}