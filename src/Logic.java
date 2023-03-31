import java.lang.Thread;

class Logic {
    private Event[] events = new Event[] {
        new Event("Flood strikes..."), 
        new Event("People have overthrown you..."), 
        new Event("You have bankrupted yourself...")
    };

    public void start() {
        for (Event event : events) {
            System.out.println(event);
            sleep();
        }
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted...");
        }
    }
}