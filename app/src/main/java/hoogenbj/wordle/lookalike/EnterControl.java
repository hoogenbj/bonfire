package hoogenbj.wordle.lookalike;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A custom control representing the enter key in the on-screen keyboard
 */
public class EnterControl extends StackPane implements Initializable {

    @FXML
    private Text enterKey;

    public static EnterControl getInstance() {
        FXMLLoader fxmlLoader = new FXMLLoader(EnterControl.class.getResource("/EnterKey.fxml"));
        EnterControl control = new EnterControl();
        fxmlLoader.setRoot(control);
        fxmlLoader.setController(control);

        try {
            fxmlLoader.load();
            return control;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public EnterControl() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
