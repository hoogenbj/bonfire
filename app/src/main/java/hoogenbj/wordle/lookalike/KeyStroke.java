package hoogenbj.wordle.lookalike;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class KeyStroke {
    enum State {
        Unknown("my-key-unknown"), Yes("my-key-yes"), No("my-key-no"), Typed("my-key-flash");
        private String cssClass;
        State(String cssClass) {
            this.cssClass = cssClass;
        }

        public String getCssClass() {
            return cssClass;
        }
    }

    public KeyStroke(String key) {
        setKeyStroke(key);
        setState(State.Unknown);
    }

    private SimpleObjectProperty<State> stateProperty;
    private SimpleStringProperty keyStroke;

    public String getKeyStroke() {
        return keyStroke.get();
    }

    public SimpleStringProperty keyStrokeProperty() {
        if (keyStroke == null) {
            keyStroke = new SimpleStringProperty(this, "keyStroke");
        }
        return keyStroke;
    }

    public void setKeyStroke(String keyStroke) {
        this.keyStrokeProperty().set(keyStroke);
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
