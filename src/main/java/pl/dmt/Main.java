package pl.dmt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;

class User {
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final User user = (User) o;

        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

public class Main {

    private static final String PATH = "z:\\downloads\\pwned-passwords-1.0.txt\\pwned.txt";
    private static final String PATH_2 = "z:\\downloads\\pwned-passwords-1.0.txt\\pwned-passwords-1.0.txt";

    public static void main(String... args) throws IOException {
        System.out.println("Hello...");
        /*final List<String> list = Stream.of("1", "2", "3")
                .peek(e -> System.out.println(e + " was consumed be peek()."))
                .collect(Collectors.toList());
        System.out.println(list);

        final Optional<User> optUser = Optional.of(new User("USER"));
        System.out.println(optUser.orElse(new User("NAME")));*/

        //pq();
        //parallel();
        System.out.println(Animal.DOG.toString());
        System.out.println(Animal.DOG.name());
        // α β γ δ π
        System.out.println("\u03b1");
        System.out.println("\u03b2");
        System.out.println("\u03b3");
        System.out.println("\u03b4");
        System.out.println("\u03c0");

        /*A b = new B();
        b.a();
        List<A> as = new ArrayList<>();
        as.add(new B());
        as.get(0).a();*/
        //System.out.println(0123 + 3210);
        //String encPassword = encryptPassword("123qwe".toUpperCase());
        /*System.out.println(encPassword);
        System.out.println();*/
        //search(encPassword);

        /*double a1 = Math.sqrt(112.0);
        double a2 = Q_rsqrt(112.0);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a1 - a2);*/

        /*double[] l = new double[]{1, 21, 112, 345, 12345};

        Arrays.stream(l).forEach(d -> {
            double _a1 = Math.sqrt(d);
            double _a2 = Q_rsqrt(d);
            //System.out.println(_a1);
            //System.out.println(_a2);
            System.out.println(_a1 - _a2);
            //System.out.println();
        });*/

        System.out.println("END.");
    }

    enum Animal {
        DOG;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    private static void parallel() {
        int result = IntStream.range(0, 3)
                .parallel()
                .peek(it -> System.out.printf("Thread [%s] peek: %d\n", Thread.currentThread().getName(), it))
                .sum();
        System.out.println("sum: " + result);
    }

    /*private static final int MAX_CAP = 0x7fff;

    private static ForkJoinPool makeCommonPool() {
        int parallelism = -1;
        try {
            String pp = System.getProperty
                    ("java.util.concurrent.ForkJoinPool.common.parallelism");
            if (pp != null)
                parallelism = Integer.parseInt(pp);
        } catch (Exception ignore) {
        }
        if (parallelism < 0 && // default 1 less than #cores
                (parallelism = Runtime.getRuntime().availableProcessors() - 1) <= 0)
            parallelism = 1;
        if (parallelism > MAX_CAP)
            parallelism = MAX_CAP;
        return new ForkJoinPool(parallelism, factory, handler, LIFO_QUEUE,
                "ForkJoinPool.commonPool-worker-");
    }*/

    private static void pq() {
        final Queue<String> q = new PriorityQueue<>(comparing(String::length));
        q.add("123");
        q.add("13");
        q.add("15345");
        q.add("1774");
        q.add("89");
        int size = q.size();
        for (String s : q) {
            System.out.println(s);
        }
        System.out.println("====");
        for (int i = 0; i < size; ++i) {
            System.out.println(q.poll());
        }
        System.out.println("====");
        for (String s : q) {
            System.out.println(s);
        }
        System.out.println("====");
    }

    private static double Q_rsqrt(double number) {
        double x = number;
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x = Double.longBitsToDouble(i);
        for (int it = 0; it < 4; it++) {
            x = x * (1.5d - xhalf * x * x);
        }
        x *= number;
        return x;
    }

    public static double invSqrt(double x) {
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x = Double.longBitsToDouble(i);
        x *= (1.5d - xhalf * x * x);
        return x;
    }

    public static float invSqrt(float x) {
        float xhalf = 0.5f * x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i >> 1);
        x = Float.intBitsToFloat(i);
        x *= (1.5f - xhalf * x * x);
        return x;
    }

    /*private static float fastInvSqrt(float x) {
        float xhalf = 0.5f * x;
        int i = *(int*)&x;          // evil floating point bit level hacking
        i = 0x5f3759df - (i >> 1);  // what the fuck?
        x = *(float*)&i;
        x = x*(1.5f-(xhalf*x*x));
        return x;
    }*/

    private static void search(final String needle) throws IOException {
        try (final FileInputStream inputStream = new FileInputStream(PATH_2);
             final Scanner sc = new Scanner(inputStream, "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                //System.out.println(line);
                if (line.equals(needle)) {
                    System.out.println("FOUND");
                    return;
                }
            }
        }
    }

    private static String encryptPassword(final String password) {
        String sha1 = "";
        try {
            final MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        final Formatter formatter = new Formatter();
        for (final byte b : hash) {
            formatter.format("%02x", b);
        }
        final String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String toSHA1(final byte[] convertme) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        return new String(md.digest(convertme));
    }
}

class A {
    public void a() {
        System.out.println("A");
    }
}

class B extends A {
    @Override
    public void a() {
        System.out.println("B");
    }
}
