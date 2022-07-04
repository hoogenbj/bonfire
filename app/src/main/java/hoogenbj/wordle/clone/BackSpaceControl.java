package hoogenbj.wordle.clone;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static hoogenbj.wordle.clone.Animations.flashKey;

/**
 * A custom control representing the backspace key in the on-screen keyboard.
 */
public class BackSpaceControl extends StackPane implements Initializable {

    private KeyStroke keyStroke;
    private Consumer<KeyStroke> eventHandler;

    public static BackSpaceControl getInstance(KeyStroke keyStroke, Consumer<KeyStroke> eventHandler) {
        FXMLLoader fxmlLoader = new FXMLLoader(BackSpaceControl.class.getResource("/BackSpaceKey.fxml"));
        BackSpaceControl control = new BackSpaceControl();
        control.keyStroke = keyStroke;
        control.eventHandler = eventHandler;
        fxmlLoader.setRoot(control);
        fxmlLoader.setController(control);

        try {
            fxmlLoader.load();
            return control;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public BackSpaceControl() {
    }

    @FXML
    private void onBackspaceClicked(MouseEvent event) {
        eventHandler.accept(keyStroke);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyStroke.stateProperty().addListener((observable, oldState, newState) -> {
            if (newState != null && !newState.equals(oldState)) {
                if (newState == KeyStroke.State.Typed) {
                    flashKey(this, oldState, newState);
                } else if (newState == KeyStroke.State.No) {
                    getStyleClass().clear();
                    getStyleClass().add(newState.getCssClass());
                }
            }
        });
    }
}
