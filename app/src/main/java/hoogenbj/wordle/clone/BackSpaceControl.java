package hoogenbj.wordle.clone;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A custom control representing the backspace key in the on-screen keyboard.
 */
public class BackSpaceControl extends StackPane implements Initializable {

    public static BackSpaceControl getInstance() {
        FXMLLoader fxmlLoader = new FXMLLoader(BackSpaceControl.class.getResource("/BackSpaceKey.fxml"));
        BackSpaceControl control = new BackSpaceControl();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
