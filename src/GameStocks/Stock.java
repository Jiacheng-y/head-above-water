package GameStocks;

public class Stock {
    public static int level;

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
