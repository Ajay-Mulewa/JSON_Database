
public class Main {

    public static void main(String[] args) {

        Thread worker1 = new Thread(new RunnableWorker(), "worker-alpha");
        Thread worker2 = new Thread(new RunnableWorker(), "worker-beta");
        Thread worker3 = new Thread(new RunnableWorker(), "worker-gama");
        worker1.start();
        worker2.start();
        worker3.start();
    }
}

// Don't change the code below       
class RunnableWorker implements Runnable {

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();

        if (name.startsWith("worker-")) {
            System.out.println("too hard calculations...");
        } else {
            return;
        }
    }
}