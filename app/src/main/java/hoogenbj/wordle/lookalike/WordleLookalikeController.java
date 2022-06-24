package hoogenbj.wordle.lookalike;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class WordleLookalikeController {
    @FXML
    private VBox words;
    @FXML
    private HBox upperRow;
    @FXML
    private HBox middleRow;
    @FXML
    private HBox lowerRow;

    public void initialize() {
        words.getChildren().add(WordControl.getInstance());
        words.getChildren().add(WordControl.getInstance());
        words.getChildren().add(WordControl.getInstance());
        words.getChildren().add(WordControl.getInstance());
        words.getChildren().add(WordControl.getInstance());
        words.getChildren().add(WordControl.getInstance());
        List.of("Q","W","E","R","T","Y","U","I","O","P").forEach(k -> {
            upperRow.getChildren().add(KeyControl.getInstance(k));
        });
        List.of("A","S","D","F","G","H","J","K","L").forEach(k -> {
            middleRow.getChildren().add(KeyControl.getInstance(k));
        });
        lowerRow.getChildren().add(EnterControl.getInstance());
        List.of("Z","X","C","V","B","N","M").forEach(k -> {
            lowerRow.getChildren().add(KeyControl.getInstance(k));
        });
        lowerRow.getChildren().add(BackSpaceControl.getInstance());
    }
}
