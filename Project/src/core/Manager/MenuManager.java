package core.Manager;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;

public class MenuManager {
    private VBox menuBox;
    private MenuItem[] menuItems;
    private int currentIndex = 0;

    public MenuManager(String[] labels, Pos position, double spacing) {
        menuBox = new VBox(spacing);
        menuBox.setAlignment(position);
        initializeMenuItems(labels);
    }

    private void initializeMenuItems(String[] labels) {
        menuItems = new MenuItem[labels.length];
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            label.setFont(new Font("Arial", 16));
            label.setTextFill(Color.WHITE);

            String pointerPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Pointer.png";
            SpriteAnimation pointerAnimation = SpriteAnimation.idle(pointerPath,
                    32, 32, 1,8,
                    false, false);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(pointerAnimation.getSpriteView());
            stackPane.getChildren().add(label);

            StackPane.setMargin(pointerAnimation.getSpriteView(), new Insets(0,150,0,0) );

            MenuItem menuItem = new MenuItem(stackPane, pointerAnimation);
            menuItems[i] = menuItem;
            menuBox.getChildren().add(stackPane);
        }
        updateSelection();
    }

    public VBox getMenuBox() {
        return menuBox;
    }

    public void handleKeyPress(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                if (currentIndex > 0) currentIndex--;
                break;
            case DOWN:
                if (currentIndex < menuItems.length - 1) currentIndex++;
                break;
            case ENTER:
                executeMenuItem(currentIndex);
                System.out.println("Entered key");
                break;
            default:
                // Handle other keys if necessary
                break;
        }
        updateSelection();
    }

    private void updateSelection() {
        for (int i = 0; i < menuItems.length; i++) {
//            Label label = menuItems[i].getLabel();
            if (i == currentIndex) {
//                label.setTextFill(Color.RED); // Highlight the selected item
                this.menuItems[i].animation.startIdleAnimation();
            } else {
//                label.setTextFill(Color.WHITE); // Revert others to default color
                this.menuItems[i].animation.removeAnimation();
            }
        }
    }

    private void executeMenuItem(int index) {
        // Implement actions based on the selected menu item
//        System.out.println("Selected: " + menuItems[index].getLabel().getText());
        // For example, start game, open settings, exit, etc.
    }

    // Inner class to represent menu items
    private static class MenuItem {
        StackPane pane;
        SpriteAnimation animation;

        public MenuItem(StackPane pane, SpriteAnimation animation) {
            this.pane = pane;
            this.animation = animation;
        }
    }
}
