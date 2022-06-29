package hoogenbj.wordle.lookalike;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KeyControl extends StackPane implements Initializable {

    @FXML
    private Text keyText;
    private KeyStroke keyStroke;

    public static KeyControl getInstance(KeyStroke keyStroke) {
        FXMLLoader fxmlLoader = new FXMLLoader(KeyControl.class.getResource("/Key.fxml"));
        KeyControl control = new KeyControl();
        control.keyStroke = keyStroke;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyText.setText(keyStroke.getKeyStroke());
        keyStroke.stateProperty().addListener((observable, oldState, newState) -> {
            if (newState != null && !newState.equals(oldState)) {
                if (newState == KeyStroke.State.Typed) {
                    flashKey(oldState, newState);
                }
            }
        });
    }

    private void flashKey(KeyStroke.State oldState, KeyStroke.State newState) {
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(100));
        KeyStroke.State saveState = oldState;
        if (saveState == null)
            saveState = KeyStroke.State.Unknown;
        getStyleClass().clear();
        getStyleClass().add(newState.getCssClass());
        KeyStroke.State finalSaveState = saveState;
        pauseTransition.setOnFinished(event -> {
            getStyleClass().clear();
            getStyleClass().add(finalSaveState.getCssClass());
        });
        pauseTransition.play();
    }
}
