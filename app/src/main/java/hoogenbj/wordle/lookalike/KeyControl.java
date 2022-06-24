package hoogenbj.wordle.lookalike;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KeyControl extends StackPane implements Initializable {

    @FXML
    private Text keyText;
    private String key;

    public static KeyControl getInstance(String key) {
        FXMLLoader fxmlLoader = new FXMLLoader(KeyControl.class.getResource("/Key.fxml"));
        KeyControl control = new KeyControl();
        control.key = key;
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
        keyText.setText(key);
    }
}
