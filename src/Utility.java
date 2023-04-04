import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.function.Supplier;

class Utility {
    public static String RIVER_CANALISATION = String.format("River canalisation\nCost: -%d\nSatisfaction: +%d\nFlood resistance: +%d\n", 100, 7, 4);
    public static String RIVER_NATURALISATION = String.format("River canalisation\nCost: -%d\nSatisfaction: +%d\nFlood resistance( only increases 3 rounds later): +%d\n", 2000, 2, 10);
    public static String DO_NOTHING = String.format("River canalisation\nCost: -%d\nSatisfaction: +%d\nFlood resistance: +%d\n", 0, 0, 0);

    // Event 1: land subsidence due to excessive groundwater extraction
    public final Event EVENT_ONE = new Event("Excessive groundwater extraction over the past few years have " +
            "led to significant land subsidence. As a result, the recent rainfall caused flooding in the city!", RIVER_CANALISATION,
            RIVER_NATURALISATION, DO_NOTHING);
    public final Event EVENT_TWO = new Event("", "", "", "");
    public final Event EVENT_THREE = new Event("", "", "", "");

    public void initialise() {
        // set effect of the event
        EVENT_ONE.setEventEffects(
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
    }

    public Event[] events = new Event[] {EVENT_ONE, EVENT_TWO, EVENT_THREE};
}
