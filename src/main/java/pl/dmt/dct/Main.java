package pl.dmt.dct;

import java.util.Random;

public class Main {
    private static final int N = 4;
    private static double[][] f = new double[4][4];
    private static Random generator = new Random();

    public static void main(String[] args) {
        // Generate random integers between 0 and 255
        int value;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                value = generator.nextInt(255);
                f[x][y] = value;
                System.out.print(f[x][y] + " ");
            }
            System.out.println();
        }


        DCT dctApplied = new DCT();
        double[][] F = dctApplied.applyDCT(f);
        System.out.println("From f to F");
        System.out.println("-----------");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {

                try {
                    System.out.print(F[i][j] + " ");
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            System.out.println(" ");
        }

    }
}
