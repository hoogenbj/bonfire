package hoogenbj.wordle.lookalike;

public class Evaluator {
    private final String targetWord;

    public Evaluator(String targetWord) {
        this.targetWord = targetWord.toUpperCase();
    }

    public Letter.State[] compare(String candidate) {
        Letter.State[] states = new Letter.State[candidate.length()];
        for (int i=0; i<candidate.length(); i++) {
            if (candidate.charAt(i) == targetWord.charAt(i))
                states[i] = Letter.State.Yes;
            else if (targetWord.indexOf(candidate.charAt(i)) != -1) {
                states[i] = Letter.State.Maybe;
            }
            else
                states[i] = Letter.State.No;
        }
        return states;
    }

}
