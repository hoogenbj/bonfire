package hoogenbj.wordle.lookalike;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Keyboard {
    private final List<KeyStroke> upper;
    private final List<KeyStroke> middle;
    private final List<KeyStroke> lower;

    private static Map<String, KeyStroke> lookup = new HashMap<>();

    public Keyboard() {
        upper = Stream.of("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P").map(this::makeKeyStroke).toList();
        middle = Stream.of("A", "S", "D", "F", "G", "H", "J", "K", "L").map(this::makeKeyStroke).toList();
        lower = Stream.of("Z", "X", "C", "V", "B", "N", "M").map(this::makeKeyStroke).toList();
    }

    private KeyStroke makeKeyStroke(String s) {
        KeyStroke keyStroke = new KeyStroke(s);
        lookup.put(s, keyStroke);
        return keyStroke;
    }

    public List<KeyStroke> getUpper() {
        return upper;
    }

    public List<KeyStroke> getMiddle() {
        return middle;
    }

    public List<KeyStroke> getLower() {
        return lower;
    }

    public static KeyStroke lookup(String s) {
        return lookup.get(s);
    }
}
