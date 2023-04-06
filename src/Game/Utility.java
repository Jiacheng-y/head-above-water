package Game;

import Game.GameStocks.Approval;
import Game.GameStocks.FloodInfrastructureProtection;
import Game.GameStocks.LandSubsidence;
import Game.GameStocks.Money;
import Game.GameStocks.RiverCapacity;

class Utility {
    //EVENT STRINGS
    public static String EVENT_DESC_POLITICAL = "The opposing party denounces you regarding flood policy. Your approval rating is currently at risk!\n";

    // DECISION STRINGS
    public static String RIVER_CANALISATION =
            String.format("Invest in a big river canalisation project! It's time we show the opposing party who is the boss\n" +
                    "Cost: -%d\nApproval: +%d\nFlood resistance: +%d\n", 100, 7, 4);
    public static String RIVER_NATURALISATION = String.format("River naturalisation\nCost: -%d\nApproval: +%d\nFlood resistance( only increases 3 rounds later): +%d\n", 2000, 2, 10);
    public static String DO_NOTHING = String.format("Do nothing\nCost: -%d\nApproval: +%d\nFlood resistance: +%d\n", 0, 0, 0);
    public static String DREDGING = String.format("Dredge the river! Dig up those sediments!\nCost: -%d\nApproval: +%d\nFlood resistance: +%d\n", 200, 5, 10);
    public static String SEA_WALL = String.format("Build a sea wall!\nCost: -%d\nApproval: +%d\nFlood resistance: +%d\n", 300, 5, 5);
    public static String PARLIAMENT_DEBATE = String.format("Debate this in the parliament! School the opposing party!\n" +
            "Your approval rating may or may not increase.\nCost: -%d\n", 0);


    //DECISIONS
    public Decision dredging = new Decision(DREDGING,
            () -> {
                Money.decrease(200);
                RiverCapacity.increase(5);
                Approval.increase(10);
                return 0;
    });
    public Decision parliamentDebate = new Decision(PARLIAMENT_DEBATE,
            () -> Approval.decrease(5)
    );

    public Decision canalisation = new Decision(RIVER_CANALISATION,
            () -> {
                Money.decrease(500);
                Approval.increase(7);
                FloodInfrastructureProtection.increase(4);
                GrandFather.queue.add(new Effect(() -> LandSubsidence.increase(7), 5));
                return 0;
    });

    //EVENTS
    public Event EVENT_POLITICS = new Event(EVENT_DESC_POLITICAL,
            () -> Approval.decrease(5), dredging, parliamentDebate, canalisation);


    //EVENT ARRAY
    public Event[] events = new Event[] {EVENT_POLITICS};
}
