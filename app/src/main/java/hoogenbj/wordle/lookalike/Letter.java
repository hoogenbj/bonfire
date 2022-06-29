package hoogenbj.wordle.lookalike;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Letter {
    public enum State {
        Unknown("my-letter-box-unknown"),
        Yes("my-letter-box-yes"),
        No("my-letter-box-no"),
        Maybe("my-letter-box-maybe");
        private final String cssClass;

        State(String cssClass) {
            this.cssClass = cssClass;
        }

        public String getCssClass() {
            return cssClass;
        }

        @Override
        public String toString() {
            return "State{" +
                    this.name() +
                    '}';
        }
    }
    private SimpleObjectProperty<State> stateProperty;
    private SimpleStringProperty letter;

    public String getLetter() {
        return letter.get();
    }

    public SimpleStringProperty letterProperty() {
        if (letter == null) {
            letter = new SimpleStringProperty(this, "letter");
        }
        return letter;
    }

    public void setLetter(String letter) {
        this.letterProperty().set(letter);
    }

    public State getState() {
        return stateProperty.get();
    }

    public SimpleObjectProperty<State> stateProperty() {
        if (stateProperty == null) {
            stateProperty = new SimpleObjectProperty<>(this, "state");
        }
        return stateProperty;
    }

    public void setState(State state) {
        this.stateProperty().set(state);
    }
}
