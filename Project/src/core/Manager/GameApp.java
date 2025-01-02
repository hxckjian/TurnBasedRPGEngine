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

    public ImageView createImageSprite(String imagePath, int frameWidth, int frameHeight, int scale, boolean flipHorizontal, boolean flipVertical, double posX, double posY, int frameCount, Pane root) {
        ImageView spriteView = new ImageView();
        spriteView.setImage(new Image(imagePath, frameWidth * scale * frameCount, frameHeight * scale, true, false));
//        Image spriteSheet = new Image(imagePath, frameWidth * scale * frameCount, frameHeight * scale, true, false);
//        ImageView spriteView = new ImageView(spriteSheet);

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

//        ProgressBar progressBar = new ProgressBar(0.8);
//        progressBar.setPrefWidth(frameWidth * scale);  // Set width to match or relate to character width
//
//        // VBox to hold character and progress bar
//        VBox vbox = new VBox(5);  // 5 pixels spacing between elements
//        vbox.getChildren().addAll(spriteView, progressBar);

        root.getChildren().add(spriteView);

//        vbox.setLayoutX(posX);  // X position
//        vbox.setLayoutY(posY);   // Y position

        return spriteView;
    }

    public Timeline createAnimatedSprite(ImageView spriteView, int frameWidth, int frameHeight, int scale, int frameCount, boolean infinite) {

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            int currentIndex = (int) ((spriteView.getViewport().getMinX() + frameWidth * scale) / (frameWidth * scale)) % frameCount;
            if (currentIndex < frameCount - 1) {
                spriteView.setViewport(new javafx.geometry.Rectangle2D(currentIndex * frameWidth * scale, 0, frameWidth * scale, frameHeight * scale));
            } else {
                // Optionally reset or hide the sprite at the end of the animation
                spriteView.setViewport(new javafx.geometry.Rectangle2D((frameCount - 1) * frameWidth * scale, 0, frameWidth * scale, frameHeight * scale));
            }
        }));
        if (infinite) {
            animation.setCycleCount(Animation.INDEFINITE);
        } else {
            animation.setCycleCount(frameCount);
        }
        return animation;
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

//        ImageView spriteGhost = createImageSprite("file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_idle.png",
//                64, 64, 3, false, false, 600, 200, 13, root);
//        Timeline animationGhost = createAnimatedSprite(
//                spriteGhost ,64, 64, 3, 13, true);
//        animationGhost.play();
    }

    public static void main(String[] args) {
        launch(args);
    }


}