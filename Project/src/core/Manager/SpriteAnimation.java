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
    private String imageHurtPath;
    private String imageDeathPath;
    private double posX;
    private double posY;
    private int frameWidth;
    private int frameHeight;
    private int scale;
    private int frameCount;
    private boolean flipHorizontal;
    private boolean flipVertical;

    private SpriteAnimation(String imageIdlePath, String imageAttackPath, String imageHurtPath, String imageDeathPath,
                           double posX, double posY,
                           int frameWidth, int frameHeight, int scale, int frameCount,
                           boolean flipHorizontal, boolean flipVertical) {
        this.imageIdlePath = imageIdlePath;
        this.imageAttackPath = imageAttackPath;
        this.imageHurtPath = imageHurtPath;
        this.imageDeathPath = imageDeathPath;
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

    public static SpriteAnimation of(String imageIdlePath, String imageAttackPath, String imageHurtPath, String imageDeathPath,
                                     double posX, double posY,
                                     int frameWidth, int frameHeight, int scale, int frameCount,
                                     boolean flipHorizontal, boolean flipVertical) {
        return new SpriteAnimation(imageIdlePath, imageAttackPath, imageHurtPath, imageDeathPath,
                posX, posY,
                frameWidth, frameHeight, scale, frameCount,
                flipHorizontal, flipVertical);
    }

    public static SpriteAnimation idle(String imageIdlePath,
                                     int frameWidth, int frameHeight, int scale, int frameCount,
                                     boolean flipHorizontal, boolean flipVertical) {
        return new SpriteAnimation(imageIdlePath, null, null, null,
                0, 0,
                frameWidth, frameHeight, scale, frameCount,
                flipHorizontal, flipVertical);
    }

    public ImageView getSpriteView() {
        this.spriteView.setViewport(new Rectangle2D(0, 0, this.frameWidth * this.scale, this.frameHeight * this.scale));
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

    public void playAnimation(String imagePath, int adjustedImageWidth, int frameCount, boolean infinite) {
        if (this.currentAnimation != null) {
            this.currentAnimation.stop();
        }
        this.spriteView.setImage(new Image(imagePath, adjustedImageWidth * this.scale * frameCount, this.frameHeight * this.scale, true, false));
        this.spriteView.setViewport(new Rectangle2D(0, 0, adjustedImageWidth * this.scale, this.frameHeight * this.scale));
        this.currentAnimation = new Timeline(new KeyFrame(Duration.millis(100), e -> updateAnimationFrame(adjustedImageWidth, frameCount)));
//        this.currentAnimation.setCycleCount(Animation.INDEFINITE);
        if (infinite) {
            this.currentAnimation.setCycleCount(Animation.INDEFINITE);
        } else {
            this.currentAnimation.setCycleCount(frameCount);
        }
//        this.spriteView.setLayoutX(this.posX);
        this.spriteView.setLayoutX(this.posX - ((adjustedImageWidth - this.frameWidth) * this.scale / 2.0));
        this.spriteView.setLayoutY(this.posY);
        this.currentAnimation.play();
    }

    public void removeAnimation() {
        if (this.currentAnimation != null) {
            this.currentAnimation.stop();
        }
        this.spriteView.setImage(null);
//        this.spriteView.setViewport(null);
    }

    public void startIdleAnimation() {
        this.playAnimation(this.imageIdlePath, this.frameWidth, this.frameCount, true);
    }

    public void startAttackAnimation(int adjustedImageWidth, int attackFrameCount) {
        this.playAnimation(this.imageAttackPath, adjustedImageWidth, attackFrameCount, false);
        // Switch back to idle after attack completes
        this.currentAnimation.setOnFinished(e -> startIdleAnimation());
    }

    public void startHurtAnimation(int adjustedImageWidth, int hurtFrameCount) {
        this.playAnimation(this.imageHurtPath, adjustedImageWidth, hurtFrameCount, false);
        // Switch back to idle after hurt completes
        // if check dead, start Death Animation and stop, else start Idle Animation ***
        this.currentAnimation.setOnFinished(e -> startIdleAnimation());
    }

    public void startDeathAnimation(int adjustedImageWidth, int deathFrameCount) {
        this.playAnimation(this.imageDeathPath, adjustedImageWidth, deathFrameCount, false);
        this.currentAnimation.setOnFinished(e -> {
            // Calculate the position of the last frame in the sprite sheet
            double lastFrameX = (deathFrameCount - 1) * adjustedImageWidth * this.scale;

            // Set the viewport to display only the last frame
            this.spriteView.setViewport(new Rectangle2D(lastFrameX, 0, adjustedImageWidth * this.scale, this.frameHeight * this.scale));
        });
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
