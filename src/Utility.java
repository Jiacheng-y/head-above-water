import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.function.Supplier;

class Utility {
    public static String RIVER_CANALISATION = String.format("River canalisation\nCost: -%d\nSatisfaction: +%d\nFlood resistance: +%d\n", 100, 7, 4);
    public static String RIVER_NATURALISATION = String.format("River naturalisation\nCost: -%d\nSatisfaction: +%d\nFlood resistance( only increases 3 rounds later): +%d\n", 2000, 2, 10);
    public static String DO_NOTHING = String.format("Do nothing \nCost: -%d\nSatisfaction: +%d\nFlood resistance: +%d\n", 0, 0, 0);

    // Event 1: land subsidence due to excessive groundwater extraction
    public final Event EVENT_ONE = new Event("Excessive groundwater extraction over the past few years have " +
            "led to significant land subsidence. As a result, the recent rainfall caused flooding in the city!", RIVER_CANALISATION,
            RIVER_NATURALISATION, DO_NOTHING);
    public final Event EVENT_TWO = new Event("", "", "", "");
    public final Event EVENT_THREE = new Event("Your colleagues are shunning you for your policies on flood prevention... \"Just normalise the river\", they say! If this goes on, the overall satisfaction of your candidacy will be impacted!", "Sure, river normalisation has been tried and tested... let's just go with the flow... it will cost $2000 dollars but their satisfaction will go up by 10", "The money is better spent on naturalisation though... $3000 dollars for an increase in flood resilience of 3 units per time step over 3 time steps", "I guess I'll just lay low for now...");

    public void initialise() {
        // set effect of the event
        EVENT_ONE.setEventEffect(
                () -> {
                    Satisfaction.decrease(2);
                    FloodResilience.decrease(2);
                    return 0;
                });
        // order of effect methods corresponding to effect of choices
        EVENT_ONE.setEffects(
                () -> {
                    Money.decrease(100);
                    Satisfaction.increase(7);
                    FloodResilience.increase(4);
                    return 0;
                },
                () -> {
                    Money.decrease(2000);
                    Satisfaction.increase(2);
                    GrandFather.queue.add(new Effect(() -> FloodResilience.increase(10), 3));
                    return 0;
                },
                () -> {return 0;}
        );
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

    public Event[] events = new Event[] {EVENT_ONE, EVENT_THREE, EVENT_TWO};
}
