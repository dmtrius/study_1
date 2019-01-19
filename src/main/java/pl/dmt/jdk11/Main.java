package pl.dmt.jdk11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        /*List<String> list = Arrays.asList("RED", "WHITE", "PINK", "BLUE", "GOLD")
                .stream()
                .filter((e) -> !e.contains("E"))
                .collect(Collectors.toList());
        list.forEach((e) -> System.out.println("We grabbed: " + e));*/
        int i = 5;
        i = ++i + ++i;
        System.out.println(i);
    }
}
