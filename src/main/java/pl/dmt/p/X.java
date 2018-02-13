package pl.dmt.p;

import static pl.dmt.p.A.x;

class A {
    static String x = "A.x";
}

class B {
    private String x = "B.x"; // changed from "String x = "B.x"" - without private
}

class C {
    String xOld = "C.x"; // changed from "String x = "C.x""

    class D extends B {
        void m() {
            System.out.println(x);
        }
    }
}

public class X {
    public static void main(String[] args) {
        new C().new D().m();
    }
}
