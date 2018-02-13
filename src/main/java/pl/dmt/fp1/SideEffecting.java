package pl.dmt.fp1;

public class SideEffecting {

    private int level = 0;
    private String state = "normal";

    public int increase(int val) {
        return level += val;
    }

    private String determineLevel() {
        if (level < 10)
            state = "normal";
        else
            state = "too high";
        return state;
    }

    public int makeSafe(int decrementBy) {
        // loop by the decrement by val until we're back to normal levels
        while (determineLevel().equals("too high")) {
            System.out.println(String.format("Warning! Level of %d is too high, attempting to settle...", level));
            level -= decrementBy;
            state = determineLevel();
        }
        // return the level at which we're safe again
        System.out.println("System is back to normal levels now");
        return level;
    }

    public static void main(String[] args) {
        final SideEffecting seff = new SideEffecting();
        seff.increase(42);
        seff.makeSafe(5);
        System.out.println(String.format("The safe level is now %d", seff.level));
    }
}
