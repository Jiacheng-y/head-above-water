package Game;

class Main {
    public static void main(String[] args) {
        UI.display(UI.WELCOME_USER);
        Logic logic = new Logic();
        Storage storage = new Storage();
        logic.start();
        storage.closeWriter();
    }
}
