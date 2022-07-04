package hoogenbj.wordle.clone;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static hoogenbj.wordle.clone.Animations.flashKey;

/**
 * A custom control representing a key in the on-screen keyboard. Responsible for providing the user
 * with the means to type a key and to get feedback
 */
public class KeyControl extends StackPane implements Initializable {

    @FXML
    private Text keyText;
    private KeyStroke keyStroke;
    private Consumer<KeyStroke> eventHandler;

    public static KeyControl getInstance(KeyStroke keyStroke, Consumer<KeyStroke> eventHandler) {
        FXMLLoader fxmlLoader = new FXMLLoader(KeyControl.class.getResource("/Key.fxml"));
        KeyControl control = new KeyControl();
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

    public KeyControl() {
    }

    @FXML
    private void onKeyClick(MouseEvent event) {
        eventHandler.accept(keyStroke);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyText.setText(keyStroke.getKey());
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
