package pl.dmt.dct;

public class DCT {
    private static final int N = 2;
    private static final int M = 2;

    private double[] c = new double[N];

    public DCT() {
        this.initializeCoefficients();
    }

    private void initializeCoefficients() {
        for (int i = 1; i < N; i++) {
            c[i] = 1;
        }
        c[0] = 1 / Math.sqrt(2.0);
    }

    public double[][] applyDCT(double[][] f) {
        double[][] F = new double[4][4];

        for (int row = 0; row < (f.length); row += 2) {
            for (int column = 0; column < f[0].length; column += 2) {
                for (int u = 0; u < N; u++) {
                    for (int v = 0; v < N; v++) {
                        double sum = 0.0;
                        for (int i = 0; i < N; i++) {
                            for (int j = 0; j < N; j++) {
                                f[i][j] = f[row + i][column + j];
                                sum += Math.cos(((2 * i + 1) / (2.0 * N)) * u * Math.PI) * Math.cos(((2 * j + 1) / (2.0 * N)) * v * Math.PI) * f[i][j];
                            }
                        }
                        sum *= ((2 * c[u] * c[v]) / Math.sqrt(M * N));
                        F[u][v] = sum;
                    }
                }
            }
        }
        return F;
    }
}
