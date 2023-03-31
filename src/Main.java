class Main {
    public static void main(String[] args) {
        UI ui = new UI();
        ui.display(UI.WELCOME_USER);
        Logic logic = new Logic();
        logic.start();
    }
}