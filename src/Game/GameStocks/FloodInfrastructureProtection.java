package Game.GameStocks;

public class FloodInfrastructureProtection extends Stock {
    public static int level = 10;

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
}
