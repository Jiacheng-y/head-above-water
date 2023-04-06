package Game.GameStocks;

public class LandSubsidence extends Stock {
    public static int level = 0;

    public static int increase(int val) {
        level += val;
        return val;
    }

    public static double getMultiplier() {
        return 1.0 + (double) level /100.0;
    }
}
