package hoogenbj.wordle.clone;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Animations {
    public static void flashKey(Node node, KeyStroke.State oldState, KeyStroke.State newState) {
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(100));
        KeyStroke.State saveState = oldState;
        if (saveState == null)
            saveState = KeyStroke.State.Unknown;
        node.getStyleClass().clear();
        node.getStyleClass().add(newState.getCssClass());
        KeyStroke.State finalSaveState = saveState;
        pauseTransition.setOnFinished(event -> {
            node.getStyleClass().clear();
            node.getStyleClass().add(finalSaveState.getCssClass());
        });
        pauseTransition.play();
    }

    /**
     * Show animation when changing a letter's state changes
     */
    public static void flip(StackPane box, Letter.State newValue) {
        ScaleTransition disappear = new ScaleTransition(Duration.millis(300), box);
        disappear.setFromY(1);
        disappear.setToY(0);
        ScaleTransition reappear = new ScaleTransition(Duration.millis(300), box);
        reappear.setFromY(0);
        reappear.setToY(1);
        disappear.setOnFinished(event -> {
            box.getStyleClass().add(newValue.getCssClass());
            reappear.play();
        });
        disappear.play();
    }
}
