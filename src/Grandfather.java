import java.util.PriorityQueue;

class GrandFather {
    public static PriorityQueue<Effect> queue;
    
    public static void update() {
        queue.forEach(e -> e.decreaseCountdown());
    }

    public static void execute() {
        while (queue.peek().getCountdown() == 0) {
            Logic.execute(queue.poll().getSupplier());
        }
    }
}