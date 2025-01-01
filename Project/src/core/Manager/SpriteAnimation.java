package core.Manager;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SpriteAnimation {
    private ImageView spriteView = new ImageView();
    private Timeline currentAnimation = null;
    private String imageIdlePath;
    private String imageAttackPath;
    private double posX;
    private double posY;
    private int frameWidth;
    private int frameHeight;
    private int scale;
    private int frameCount;
    private boolean flipHorizontal;
    private boolean flipVertical;

    private SpriteAnimation(String imageIdlePath, String imageAttackPath,
                           double posX, double posY,
                           int frameWidth, int frameHeight, int scale, int frameCount,
                           boolean flipHorizontal, boolean flipVertical) {
        this.imageIdlePath = imageIdlePath;
        this.imageAttackPath = imageAttackPath;
        this.posX = posX;
        this.posY = posY;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.scale = scale;
        this.frameCount = frameCount;
        this.flipHorizontal = flipHorizontal;
        this.flipVertical = flipVertical;
        this.checkFlip();
    }

    public static SpriteAnimation of(String imageIdlePath, String imageAttackPath,
                                     double posX, double posY,
                                     int frameWidth, int frameHeight, int scale, int frameCount,
                                     boolean flipHorizontal, boolean flipVertical) {
        return new SpriteAnimation(imageIdlePath, imageAttackPath,
                posX, posY,
                frameWidth, frameHeight, scale, frameCount,
                flipHorizontal, flipVertical);
    }

    public ImageView getSpriteView() {
        return this.spriteView;
    }

    private void checkFlip() {
        if (this.flipHorizontal) {
            this.spriteView.setScaleX(-1);
        }
        if (flipVertical) {
            this.spriteView.setScaleY(-1);
        }
    }

    public void startIdleAnimation() {
        if (this.currentAnimation != null) {
            this.currentAnimation.stop();
        }
        this.spriteView.setImage(new Image(this.imageIdlePath, this.frameWidth * this.scale * this.frameCount, this.frameHeight * this.scale, true, false));
        this.spriteView.setViewport(new Rectangle2D(0, 0, this.frameWidth * this.scale, this.frameHeight * this.scale));
        this.currentAnimation = new Timeline(new KeyFrame(Duration.millis(100), e -> updateAnimationFrame(64, this.frameCount)));
        this.currentAnimation.setCycleCount(Animation.INDEFINITE);
        this.spriteView.setLayoutX(this.posX);
        this.spriteView.setLayoutY(this.posY);
        this.currentAnimation.play();
    }

    public void startAttackAnimation(int adjustedImageWidth, int attackFrameCount) {
        if (this.currentAnimation != null) {
            this.currentAnimation.stop();
        }
        // Assume that height is the same for now unless adjusted in future
        // Idle animation will always be consistent
        this.spriteView.setImage(new Image(this.imageAttackPath, adjustedImageWidth * this.scale * attackFrameCount , this.frameHeight * this.scale, true, false));
        this.spriteView.setViewport(new Rectangle2D(0, 0, adjustedImageWidth * this.scale, this.frameHeight * this.scale));
        this.currentAnimation = new Timeline(new KeyFrame(Duration.millis(100), e -> updateAnimationFrame(128, attackFrameCount)));
        this.currentAnimation.setCycleCount(attackFrameCount); // Play once
        this.spriteView.setLayoutX(this.posX - ((adjustedImageWidth - this.frameWidth) * this.scale / 2.0)); // Adjust position for larger sprite
        this.spriteView.setLayoutY(this.posY);
        this.currentAnimation.play();
//
        // Switch back to idle after attack completes
        this.currentAnimation.setOnFinished(e -> startIdleAnimation());
    }

    private void updateAnimationFrame(int frameWidth, int frameCount) {
        double currentMinX = spriteView.getViewport().getMinX();
        int currentIndex = (int) (currentMinX / (frameWidth * this.scale)) + 1;
        if (currentIndex >= frameCount) {
            currentIndex = 0; // Reset index if it exceeds the frame count
        }
        spriteView.setViewport(new Rectangle2D(currentIndex * frameWidth * this.scale, 0, frameWidth * this.scale, this.frameHeight * this.scale));
    }
}
