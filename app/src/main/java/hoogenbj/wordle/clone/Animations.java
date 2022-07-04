package hoogenbj.wordle.clone;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
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
}
