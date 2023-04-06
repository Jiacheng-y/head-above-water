package Game.GameStocks;

public class RiverCapacity extends Stock {
    public static int level = 100;

    public static int increase(int val) {
        level += val;
        return val;
    }

    public static int decrease(int val) {
        if (level >= 0) {
            level -= val;
        }
        return val;
    }

    public static double getMultiplier() {
        return 1.0 + (double) level /100.0;
    }
}
