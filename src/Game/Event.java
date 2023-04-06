package Game;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

class Event {
    private String description;
    private List<Decision> choices;
    private Supplier<Integer> eventEffect;

    public Event(String description, Supplier<Integer> eventEffect, Decision first, Decision second, Decision third) {
        this.description = description;
        this.eventEffect = eventEffect;

        this.choices = new ArrayList<>();
        this.choices.add(first); 
        this.choices.add(second);
        this.choices.add(third);
    }

    public void setEventEffect(Supplier<Integer> eventEffect) {
        this.eventEffect = eventEffect;
    }

    @Override
    public String toString() {
        String result = this.description;
        for (int i = 0; i < choices.size(); i++) {
            result = result + "\n" + (char) (i + 65) + " => " + choices.get(i);
        }
        return result + "\n" + "What will you do?\n";
    }

    public String getChoice(int n) {
        return choices.get(n).toString();
    }

    public Supplier<Integer> getEffect(int n) {
        return choices.get(n).getDecisionEffect();
    }

    public Supplier<Integer> getEventEffect() {
        return eventEffect;
    }
}
