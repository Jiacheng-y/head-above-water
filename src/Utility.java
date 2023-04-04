import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.function.Supplier;

class Utility {
    public Event EVENT_ONE = new Event("", "", "", "");
    public Event EVENT_TWO = new Event("", "", "", "");
    public Event EVENT_THREE = new Event("Your colleagues are shunning you for your policies on flood prevention... \"Just normalise the river\", they say! If this goes on, the overall satisfaction of your candidacy will be impacted!", "Sure, river normalisation has been tried and tested... let's just go with the flow... it will cost $2000 dollars but their satisfaction will go up by 10", "The money is better spent on naturalisation though... $3000 dollars for an increase in flood resilience of 3 units per time step over 3 time steps", "I guess I'll just lay low for now...");
    
    public void initialise() {
        EVENT_THREE.setEffects((Supplier<Integer>) () -> {
            Money.decrease(3000);
            Satisfaction.increase(10);
            return 0;
        }, () -> {
            Money.decrease(3000);
            GrandFather.queue.add(new Effect(() -> {
                FloodResilience.increase(3);
                return 0;
            }, 1));
            GrandFather.queue.add(new Effect(() -> {
                FloodResilience.increase(3);
                return 0;
            }, 1));
            GrandFather.queue.add(new Effect(() -> {
                FloodResilience.increase(3);
                return 0;
            }, 1));
            return 0;
        }, () -> {
            Satisfaction.decrease(20);
            return 0;
        });
    }   

    public Event[] events = new Event[] {EVENT_ONE, EVENT_TWO, EVENT_THREE};
}