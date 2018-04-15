package pl.dmt.reorder;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.print(i + ": ");
            ReorderedProgram2.main(null);
            Thread.sleep(100);
        }
    }
}
