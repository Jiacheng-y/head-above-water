class Event {
    private String description;

    public Event(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}