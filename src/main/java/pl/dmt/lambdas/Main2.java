package pl.dmt.lambdas;

public class Main2 {

    public static void main(String[] args) {
        Calculator<Integer, Integer> c = Integer::sum;
        Integer res = c.calculate(2, 7);
        System.out.println(res);
    }

}

@FunctionalInterface
interface Calculator<T, R> {
    R calculate(T a, T b);
}