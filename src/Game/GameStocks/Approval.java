package Game.GameStocks;

public class Approval extends Stock {
    public static int level = 70;

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
