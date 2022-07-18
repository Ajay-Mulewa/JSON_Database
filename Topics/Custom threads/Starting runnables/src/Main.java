class Starter {

    public static void startRunnables(Runnable[] runnables) {
        // implement the method
        for (Runnable thread : runnables) {
            new Thread(thread).start();
        }
    }
}
