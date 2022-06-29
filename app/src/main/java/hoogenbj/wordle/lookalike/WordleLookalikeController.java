package hoogenbj.wordle.lookalike;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WordleLookalikeController {
    @FXML
    private VBox wordsContainer;
    @FXML
    private HBox upperRow;
    @FXML
    private HBox middleRow;
    @FXML
    private HBox lowerRow;
    private List<Word> words = List.of(
            new Word(), new Word(), new Word(), new Word(), new Word(), new Word()
    );
    private int currentWord = 0;

    private List<String> wordList = new ArrayList<>();
    private Keyboard keyboard = new Keyboard();
    private String targetWord;
    private Evaluator evaluator;
    private boolean finished = false;

    public void initialize() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream("/words.txt"))))){
            String line;
            do {
                line = reader.readLine();
                wordList.add(line);
            } while(line != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int x1=0;
        int x2=wordList.size();
        double f = Math.random()/Math.nextDown(1.0);
        double x = x1*(1.0 - f) + x2*f;
        targetWord = wordList.get(Double.valueOf(x).intValue());
        System.out.println(targetWord);
        evaluator = new Evaluator(targetWord);
        Platform.runLater(() -> wordsContainer.getScene().addEventFilter(KeyEvent.KEY_RELEASED, getControlKeyEventEventHandler()));
        Platform.runLater(() -> wordsContainer.getScene().addEventFilter(KeyEvent.KEY_TYPED, getKeyEventEventHandler()));
        words.forEach(w -> wordsContainer.getChildren().add(WordControl.getInstance(w)));
        keyboard.getUpper().forEach(k -> {
            upperRow.getChildren().add(KeyControl.getInstance(k));
        });
        keyboard.getMiddle().forEach(k -> {
            middleRow.getChildren().add(KeyControl.getInstance(k));
        });
        lowerRow.getChildren().add(EnterControl.getInstance());
        keyboard.getLower().forEach(k -> {
            lowerRow.getChildren().add(KeyControl.getInstance(k));
        });
        lowerRow.getChildren().add(BackSpaceControl.getInstance());
    }

    private EventHandler<KeyEvent> getKeyEventEventHandler() {
        return (event) -> {
            if (finished)
                return;
            String typed = event.getCharacter().toUpperCase();
            switch (typed) {
                case "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
                        "T", "U", "V", "W", "X", "Y", "Z" -> {
                    words.get(currentWord).addLetter(typed);
                    Keyboard.lookup(typed).setState(KeyStroke.State.Typed);
                }
            }
        };
    }

    private EventHandler<KeyEvent> getControlKeyEventEventHandler() {
        return (event) -> {
            if (finished)
                return;
            switch (event.getCode()) {
                case BACK_SPACE -> {
                    words.get(currentWord).removeLetter();
                }
                case ENTER -> {
                    if (words.get(currentWord).letterCount() == 5) {
                        Letter.State[] states = evaluator.compare(words.get(currentWord).getWord());
                        for (int i=0; i< states.length; i++) {
                            words.get(currentWord).getLetter(i).stateProperty().set(states[i]);
                        }
                        if (Arrays.stream(states).allMatch(state -> state.equals(Letter.State.Yes))) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                                    String.format("Yay! %d out of 6 tries.", currentWord+1), ButtonType.CLOSE);
                            alert.showAndWait();
                            finished = true;
                        } else {
                            currentWord++;
                        }
                        if (currentWord > 5) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                                    String.format("Aw! Sorry! Bad luck. Target word is %s", targetWord), ButtonType.CLOSE);
                            alert.showAndWait();
                            finished = true;
                        }
                    }
                }
            }
        };
    }
}
