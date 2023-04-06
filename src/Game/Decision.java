package Game;

import java.util.function.Supplier;

class Decision {
    private String description;
    private Supplier<Integer> decisionEffect;

    public Decision(String description, Supplier<Integer> decisionEffect) {
        this.description = description;
        this.decisionEffect = decisionEffect;
    }

    public Supplier<Integer> getDecisionEffect() {
        return decisionEffect;
    }

    public void execute() {
        //TODO: decide on string format to print
        UI.display(String.format(""));
    };

    @Override
    public String toString() {
        return description;
    }
}
