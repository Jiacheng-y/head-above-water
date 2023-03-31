class Logic {
    private Event[] events = new Event[] {new Event("Flood strikes...")};

    public void start() {
        for (Event event : events) {
            System.out.println(event);
        }
    }
}