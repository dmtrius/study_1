package pl.dmt;

public class MyClass {
    private int y = 3;

    private MyClass(int i) {
        y += i;
    }

    private MyClass(int i, int i2) {
        y += (i + i2);
        System.out.print(y);
    }

    private int method(int i) {
        y += i;
        return y;
    }

    public static void main(String[] args) {
        new MyClass(new MyClass(5).method(2), 4);
    }
}
