package hoogenbj.wordle.clone;

import javafx.beans.property.SimpleObjectProperty;

/**
 * Data binding is used to react when a key is pressed on the keyboard.
 */
public class KeyStroke {
    enum State {
        Unknown("my-key-unknown"), No("my-key-no"), Typed("my-key-flash");
        private String cssClass;
        State(String cssClass) {
            this.cssClass = cssClass;
        }

        public String getCssClass() {
            return cssClass;
        }
    }

    public KeyStroke(String key) {
        setKey(key);
        setState(State.Unknown);
    }

    private SimpleObjectProperty<State> stateProperty;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
