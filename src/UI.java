class UI {
    public static final String WELCOME_USER = "Hi there! Congratulations on being elected as a policymaker in Jakarta!\nYou have been tasked to manage the issue of flooding... but hey! It can't be that hard... right?\nJust widen the rivers and you'll keep your head above water!\n";

    public static void display(String message) {
        for (String row : message.split("\n")) {
            Logic.sleep();
            System.out.println(row);          
        }
        Logic.sleep();
    }

    public static void printLine() {
        System.out.println();
    }   

    public static void printStocks() {
        System.out.println("Here are the values of your stocks!");
        System.out.println(String.format("Money: %d dollars", Money.level));
        System.out.println(String.format("Approval: %d units", Approval.level));
        System.out.println(String.format("Flood resilience: %d units", FloodResilience.level));
        System.out.println(String.format("Population: %d people", Population.level));
    }
}
