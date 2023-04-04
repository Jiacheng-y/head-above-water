public class Stock {
    public static int level;

    public static void increase(int val) {
        level += val;
    }

    public static void decrease(int val) {
        if (level >= 0) {
            level -= val;
        }
    }
}
