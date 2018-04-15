package pl.dmt;

import java.util.Optional;

public class Main5 {
    public static void main(String... args) {
        //System.out.println(R.A);
        //System.out.println(R.C);
        Thermometer t = new Thermometer();
        System.out.println(t.getT().isPresent() ? t.getT() : "N/A");
        System.out.println(t.reading());
        t.setTemperature(10.1f);
        System.out.println(t.reading());
        t.setTemperature(-12.4f);
        System.out.println(t.reading());
    }
}

enum R {
    A, B
}

class MyClass2 {
    private static void myMethod(StringBuilder s1, StringBuilder s2) {
        s1.append("r");
        s2.append("s");
        s1 = s2;
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("p");
        StringBuilder sb2 = new StringBuilder("q");
        myMethod(sb, sb2);
        System.out.print(sb + ", " + sb2);// pr qs
    }
}

class Thermometer {
    private Float temperature;

    Float reading() {
        return temperature;
    }

    Optional<Float> getT() {
        return Optional.ofNullable(temperature);
    }

    void setTemperature(Float temperature) {
        this.temperature = temperature;
    }
}