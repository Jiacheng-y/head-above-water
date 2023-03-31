class UI {
    public static final String WELCOME_USER = "Hi there! Congratulations on being elected as a policymaker in Jakarta!\nYou have been tasked to manage the issue of flooding... but hey! It can't be that hard... right?\nJust widen the rivers and everything will be hunky dory!\n";

    public static void display(String message) {
        for (String row : message.split("\n")) {
            System.out.println(row);
            Logic.sleep();
        }
    }
}