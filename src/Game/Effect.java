package Game;

import java.util.function.Supplier;

class Effect implements Comparable<Effect> {
    private Supplier<Integer> function;
    private int countdown;

    public Effect(Supplier<Integer> function) {
        this.function = function;
    }

    public Effect(Supplier<Integer> function, int countdown) {
        this.function = function;
        this.countdown = countdown;
    }

    public int getCountdown() {
        return this.countdown;
    }

    public Supplier<Integer> getSupplier() {
        return this.function;
    }

    public void decreaseCountdown() {
        this.countdown = this.countdown - 1;
    }

    @Override 
    public int compareTo(Effect e) {
        return this.countdown - e.countdown;
    }
}
