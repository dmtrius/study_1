package pl.dmt.reorder;

public class ReorderedProgram2 {
    private volatile int x = 0, y = 0;
    private volatile int a = 0, b = 0;

    public synchronized void setX() {
        a = 1;
        x = b;
    }

    public synchronized void setY() {
        b = 1;
        y = a;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized int getY() {
        return y;
    }

    public static void main(String[] args) throws InterruptedException {
        ReorderedProgram2 program = new ReorderedProgram2();
        Thread one = new Thread(program::setX);
        Thread other = new Thread(program::setY);
        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("(" + program.getX() + "," + program.getY() + ")");
    }
}
