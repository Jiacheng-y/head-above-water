package Game;

import java.lang.Thread;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

import Game.GameStocks.Approval;
import Game.GameStocks.FloodInfrastructureProtection;
import Game.GameStocks.LandSubsidence;
import Game.GameStocks.Money;
import Game.GameStocks.Population;
import Game.GameStocks.RiverCapacity;

class Logic {
    private Scanner scanner =  new Scanner(System.in); 
    private Random random = new Random();

    //game stocks : accumulates across the entire simulation

    private Event[] events;

    public void start() {

        Utility utility = new Utility();
        events = utility.events;

        for (int i = 0; i < events.length; i++) {
            Event event = events[i];
            UI.printLine();
//            if (simulateDisaster()) {
//                UI.display("You can't do anything this round because of the DISASTER. >:D");
//                continue;
//            };

//            UI.printStocks();

            //in each round, effects related to that round from the pq will be executed
            GrandFather.execute(i);

            //simulate disaster for the round
            simulateDisaster();

            UI.display(event.toString());
            UI.printLine();
            UI.printStocks();
            while (scanner.hasNextLine()) {
                String response = scanner.nextLine().toUpperCase();
                if (isInvalidResponse(response)) {
                    UI.display("Please enter only the option you wish to select.");
                    continue;
                } else {
                    UI.display(String.format("User selected: %s", event.getChoice(convertResponse(response)))); 
                    execute(event.getEffect(convertResponse(response)));
                    break;
                }
            }
            sleep();
        }
    }

    public static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted...");
        }
    }

    private int convertResponse(String response) {
        char choice = response.charAt(0);
        return choice % 'A';
    }

    private boolean isInvalidResponse(String response) {
        return response.length() > 1 || response.charAt(0) < 65 || response.charAt(0) > 67; 
    }

    private boolean simulateDisaster() {
        double floodProbability = Double.min(1,
                random.nextDouble() * LandSubsidence.getMultiplier() * RiverCapacity.getMultiplier());
        if (floodProbability > 0.7) {
            UI.display("BOOMZ! Big floods all around!");
            Money.decrease((int) floodProbability * 1000);
//            System.out.println(Money.level);
            Population.decrease((int) floodProbability * 1000);
            FloodInfrastructureProtection.decrease((int) floodProbability * 10);
            Approval.decrease((int) floodProbability * 10);
            return true;
        }
        return false;
    }

    public static Integer execute(Supplier<Integer> s) {
        return s.get();
    }
}
