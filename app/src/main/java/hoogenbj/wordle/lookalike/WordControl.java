package hoogenbj.wordle.lookalike;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WordControl extends HBox implements Initializable {
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Text text3;
    @FXML
    private Text text4;
    @FXML
    private Text text5;

    public static WordControl getInstance() {
        FXMLLoader fxmlLoader = new FXMLLoader(WordControl.class.getResource("/Word.fxml"));
        WordControl control = new WordControl();
        fxmlLoader.setRoot(control);
        fxmlLoader.setController(control);

        try {
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public WordControl() {
    }

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
    }
}
