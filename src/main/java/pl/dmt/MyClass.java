package pl.dmt;

public class MyClass {
    int y = 3;

    public MyClass(int i) {
        y += i;
    }

    public MyClass(int i, int i2) {
        y += (i + i2);
        System.out.print(y);
    }

    public int method(int i) {
        y += i;
        return y;
    }

    public static void main(String[] args) {
        new MyClass(new MyClass(5).method(2), 4);
    }
}
