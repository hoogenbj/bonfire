package hoogenbj.wordle.lookalike;

import java.util.Arrays;

/**
 * A Word comprises exactly 5 letters.
 */
public class Word {
    private final Letter[] letters = new Letter[]{
            new Letter(), new Letter(), new Letter(), new Letter(), new Letter()
    };
    private int cursor = -1;

    public void addLetter(String character) {
        if (cursor < letters.length-1)
            cursor++;
        letters[cursor].letterProperty().set(character);
    }

    public void removeLetter() {
        if (cursor >= 0) {
            letters[cursor].letterProperty().set("");
            cursor--;
        }
    }

    public Letter getLetter(int index) {
        return letters[index];
    }
    public Letter getLetter1() {
        return letters[0];
    }
    public Letter getLetter2() {
        return letters[1];
    }
    public Letter getLetter3() {
        return letters[2];
    }
    public Letter getLetter4() {
        return letters[3];
    }
    public Letter getLetter5() {
        return letters[4];
    }
    public int letterCount() {
        return cursor+1;
    }

    public String getWord() {
        return String.join("", Arrays.stream(letters).map(Letter::getLetter).toList().toArray(new String[0]));
    }
}
