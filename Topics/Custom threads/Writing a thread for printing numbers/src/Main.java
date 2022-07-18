
class NumbersThread extends Thread {

    int from;
    int to;

    public NumbersThread(int from, int to) {
        // implement the constructor
        super();
        this.from = from;
        this.to = to;
    }

    // you should override some method here
    public void run() {
        for (int i = from; i <= to; i++) {
            System.out.println(i);
        }
    }

}
