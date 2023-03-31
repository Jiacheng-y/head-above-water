import java.lang.Thread;
import java.util.Random;
import java.util.Scanner;

class Logic {
    private Scanner scanner =  new Scanner(System.in); 
    private Random random = new Random();

    private Event[] events = new Event[] {
        new Event("Flood strikes..."), 
        new Event("People have overthrown you..."), 
        new Event("You have bankrupted yourself...")
    };

    public void start() {
        for (Event event : events) {
            UI.printLine();
            if (simulateDisaster()) {
                UI.display("You can't do anything this round because of the DISASTER. >:D");
                continue;
            };
            UI.display(event.toString());
            while (scanner.hasNextLine()) {
                String response = scanner.nextLine().toUpperCase();
                if (isInvalidResponse(response)) {
                    UI.display("Please enter only the option you wish to select.");
                    continue;
                } else {
                    UI.display(String.format("User selected: %s", event.getChoice(convertResponse(response)))); 
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
}