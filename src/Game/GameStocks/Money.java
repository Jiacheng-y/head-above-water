package Game.GameStocks;

public class Money extends Stock {
    public static int level = 10000;

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
