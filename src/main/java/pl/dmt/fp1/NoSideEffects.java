package pl.dmt.fp1;

public class NoSideEffects {

    private static final int MAX_DIFFERENCE = 10;

    private Level determineLevel(int current) {
        if (current < MAX_DIFFERENCE)
            return Level.NORMAL;
        else
            return Level.TOO_HIGH;
    }

    public Tuple<Integer, String> makeSafe(int currentLevel, int decrementBy, String log) {
        // recursively decrement by val until we're back to normal levels
        if (determineLevel(currentLevel).equals(Level.TOO_HIGH)) {
            log += String.format("Warning! Level of %d is too high, attempting to settle...\n", currentLevel);
            return makeSafe(currentLevel - decrementBy, decrementBy, log);
        }
        // return the level at which we're safe again
        log += String.format("The safe level is now %d", currentLevel);
        return new Tuple<>(currentLevel, log);
    }

    public static void main(String[] args) {
        final NoSideEffects noseff = new NoSideEffects();
        final Tuple<Integer, String> start = new Tuple<>(42, "");
        final Tuple<Integer, String> result = noseff.makeSafe(start.getFirst(), 5, start.getSecond());
        System.out.println(result.getSecond());
    }
}

enum Level {
    NORMAL, TOO_HIGH
}

class Tuple<F, S> {
    private final F first;
    private final S second;

    public Tuple(F f, S s) {
        this.first = f;
        this.second = s;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}