import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.function.Supplier;

class Utility {
    public static String RIVER_CANALISATION = String.format("River canalisation\nCost: -%d\nSatisfaction: +%d\nFlood resistance: +%d\n", 100, 7, 4);
    public static String RIVER_NATURALISATION = String.format("River naturalisation\nCost: -%d\nSatisfaction: +%d\nFlood resistance( only increases 3 rounds later): +%d\n", 2000, 2, 10);
    public static String DO_NOTHING = String.format("Do nothing\nCost: -%d\nSatisfaction: +%d\nFlood resistance: +%d\n", 0, 0, 0);
    public static String SEA_WALL = String.format("Build a sea wall!\nCost: -%d\nSatisfaction: +%d\nFlood resistance: +%d\n", 300, 5, 5);

    // Event 1: land subsidence due to excessive groundwater extraction
    public final Event EVENT_ONE = new Event("Excessive groundwater extraction over the past few years have " +
            "led to significant land subsidence. As a result, the recent rainfall caused flooding in the city!", RIVER_CANALISATION,
            RIVER_NATURALISATION, DO_NOTHING);
    public final Event EVENT_TWO = new Event("Sea level is rising! As a result, your city has flooded! What will you do?", SEA_WALL, RIVER_NATURALISATION, DO_NOTHING);
    public final Event EVENT_THREE = new Event("Immense political pressure has been mounting among your colleagues to", "", "", "");

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
        EVENT_TWO.setEventEffect(
                () -> {
                    Satisfaction.decrease(3);
                    FloodResilience.decrease(3);
                    return 0;
                });
        // order of effect methods corresponding to effect of choices
        EVENT_TWO.setEffects(
                () -> {
                    Money.decrease(300);
                    Satisfaction.increase(5);
                    FloodResilience.increase(5);
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
    }

    public Event[] events = new Event[] {EVENT_ONE, EVENT_TWO, EVENT_THREE};
}
