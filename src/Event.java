import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

class Event {
    private String description;
    private List<String> choices; 
    private List<Supplier<Integer>> effects; 
    private Supplier<Integer> eventEffect;

    public Event(String description, String first, String second, String third) {
        this.description = description;
        this.choices = new ArrayList<>();
        this.effects = new ArrayList<>();
        this.choices.add(first); 
        this.choices.add(second);
        this.choices.add(third);
    }

    public void setEffects(Supplier<Integer> first, Supplier<Integer> second, Supplier<Integer> third) {
        this.effects.add(first);
        this.effects.add(second);
        this.effects.add(third);
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
        return choices.get(n);
    }

    public Supplier<Integer> getEffect(int n) {
        return effects.get(n);
    }

    public Supplier<Integer> getEffect() {
        return eventEffect;
    }
}