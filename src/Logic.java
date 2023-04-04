import java.lang.Thread;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

class Logic {
    private Scanner scanner =  new Scanner(System.in); 
    private Random random = new Random();

    //game stocks : accumulates across the entire simulation

    private Event[] events;

    public void start() {
        Utility utility = new Utility();
        utility.initialise();
        events = utility.events;

        for (Event event : events) {
            UI.printLine();
            if (simulateDisaster()) {
                UI.display("You can't do anything this round because of the DISASTER. >:D");
                continue;
            };
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
            Thread.sleep(800);
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
        if (random.nextDouble() < 0.05) {
            UI.display("BOOMZ! Big floods all around!");
            return true;
        }
        return false;
    }

    public static Integer execute(Supplier<Integer> s) {
        return s.get();
    }
}
