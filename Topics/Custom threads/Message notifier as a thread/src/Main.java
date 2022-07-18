class MessageNotifier extends Thread {

    // write fields to store variables here
    String message;
    int repeats;

    public MessageNotifier(String msg, int repeats) {
        // implement the constructor
        this.message = msg;
        this.repeats = repeats;
    }

    @Override
    public void run() {
        // implement the method to print the message stored in a field
        for (int i = 0; i < this.repeats; i++) {
            System.out.println(this.message);
        }
    }
}
