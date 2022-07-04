package hoogenbj.wordle.clone;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static hoogenbj.wordle.clone.Animations.flip;

public class WordControl extends HBox implements Initializable {
    @FXML
    private Text letter1;
    @FXML
    private StackPane letterBox1;
    @FXML
    private Text letter2;
    @FXML
    private StackPane letterBox2;
    @FXML
    private Text letter3;
    @FXML
    private StackPane letterBox3;
    @FXML
    private Text letter4;
    @FXML
    private StackPane letterBox4;
    @FXML
    private Text letter5;
    @FXML
    private StackPane letterBox5;
    private Word word;

    public static WordControl getInstance(Word word) {
        FXMLLoader fxmlLoader = new FXMLLoader(WordControl.class.getResource("/Word.fxml"));
        WordControl control = new WordControl();
        control.word = word;
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
        addListeners(word.getLetter1(), letter1, letterBox1);
        addListeners(word.getLetter2(), letter2, letterBox2);
        addListeners(word.getLetter3(), letter3, letterBox3);
        addListeners(word.getLetter4(), letter4, letterBox4);
        addListeners(word.getLetter5(), letter5, letterBox5);
    }

    private void addListeners(Letter letter, Text text, StackPane box) {
        letter.letterProperty().addListener((observable, oldLetter, newLetter) -> {
            if (newLetter != null && !newLetter.equals(oldLetter)) {
                text.setText(newLetter);
            }
        });
        letter.stateProperty().addListener((observable, oldState, newState) -> {
            if (newState != null && !newState.equals(oldState)) {
                flip(box, newState);
            }
        });
    }
}
