package Game;

import java.util.PriorityQueue;

class GrandFather {
    public static PriorityQueue<Effect> queue = new PriorityQueue<>();

    public static void update() {
        queue.forEach(e -> e.decreaseCountdown());
    }

    public static void execute() {
        while (queue.peek() != null && queue.peek().getCountdown() == 0) {
            Logic.execute(queue.poll().getSupplier());
        }
    }
}
