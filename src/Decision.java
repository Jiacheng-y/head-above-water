class Decision {
    private String description;
    private int extent;

    public Decision(String description, int extent) {
        this.description = description;
        this.extent = extent;
    }

    public void execute() {
        UI.display(String.format("Executing with extent: %s", extent));
    };

    @Override
    public String toString() {
        return description;
    }
}