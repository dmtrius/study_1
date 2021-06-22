package pl.dmt;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class Main3 {
    public static void main(String... args) throws Exception {
        //System.out.print("Java\n".repeat(10));
        //demoSerCeExample();

        String s = "\u00a0";
        // trim all whitespaces + unicode ones
//        s = s.replaceAll("(^\\h*)|(\\h*$)", "");
//        System.out.print(">");
//        System.out.print(s);
//        System.out.println("<");
//        System.out.println(StringUtils.isBlank(s));
//        System.out.println(StringUtils.isEmpty(s));
//        Pattern p = Pattern.compile("^\\s+$");
//        Matcher m = p.matcher(s);
//        boolean b = m.matches();
//        System.out.println(b);
//        Set<String> set = Stream.of("a", "b")
//                .collect(
//                        collectingAndThen(toCollection(HashSet::new), Collections::unmodifiableSet)
//                        /*Collectors.toSet()*/
//                );
//        System.out.println(set);
    }

    public static void demoSerCeExample() {
        try {
            final double doubleValue = false ? 1.0 : new HashMap<String, Double>().get("1");
            System.out.println("Double Value: " + doubleValue);
        } catch (Exception exception) {
            System.out.println("ERROR in 'demoSerCeExample': " + exception);
        }
    }
}
