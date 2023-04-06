package Game;

import java.util.PriorityQueue;

class GrandFather {
    public static PriorityQueue<Effect> queue = new PriorityQueue<>();
    
    public static void update() {
        queue.forEach(e -> e.decreaseCountdown());
    }

    public static void execute(int i) {
        while (queue.peek() != null && queue.peek().getCountdown() == i) {
            Logic.execute(queue.poll().getSupplier());
        }
    }
}
