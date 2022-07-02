package hoogenbj.wordle.clone;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluatorTest {
    @Test
    public void testTargetWordSame() throws Exception {
        String target = "round";
        Evaluator evaluator = new Evaluator(target);
        Letter.State[] states = evaluator.compare("round");
        assertTrue(Arrays.stream(states).allMatch(state -> state == Letter.State.Yes));
    }

    @Test
    public void testTargetWordMaybe() throws Exception {
        String target = "droun";
        Evaluator evaluator = new Evaluator(target);
        Letter.State[] states = evaluator.compare("round");
        assertTrue(Arrays.stream(states).allMatch(state -> state == Letter.State.Maybe));
    }

    @Test
    public void testTargetWordDifferent() throws Exception {
        String target = "abcef";
        Evaluator evaluator = new Evaluator(target);
        Letter.State[] states = evaluator.compare("round");
        assertTrue(Arrays.stream(states).allMatch(state -> state == Letter.State.No));
    }

    @Test
    public void testTargetWord1Maybe() throws Exception {
        String target = "abcde";
        Evaluator evaluator = new Evaluator(target);
        Letter.State[] states = evaluator.compare("round");
        assertSame(states[0], Letter.State.No);
        assertSame(states[1], Letter.State.No);
        assertSame(states[2], Letter.State.No);
        assertSame(states[3], Letter.State.No);
        assertSame(states[4], Letter.State.Maybe);
    }
}
