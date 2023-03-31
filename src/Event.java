class Event {
    private String description;
    private String[] choices = {"Add green spaces!", "Make the river wider and deeper!", "Snooze..."};

    public Event(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String result = this.description;
        for (int i = 0; i < choices.length; i++) {
            result = result + "\n" + (char) (i + 65) + " => " + choices[i];
        }
        return result + "\n" + "Good sir... What will you do?\n";
    }

    public String getChoice(int n) {
        return choices[n];
    }
}