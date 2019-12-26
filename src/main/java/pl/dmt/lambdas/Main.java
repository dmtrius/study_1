package pl.dmt.lambdas;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String... args) throws Throwable {

        /*Arrays.asList("http://localhost/", "https://github.com")
                .stream()
                .map(URL::new)
                .collect(Collectors.toList());*/

        /*List<URL> list = Stream.of("http://localhost/", "https://dzone.com")
                .map(url -> {
                    try {
                        return callUnchecked(() -> new URL(url));
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
        System.out.println(list);*/
    }

    public static <V> V callUnchecked(final Callable<V> callable) throws Throwable /*no throws*/ {
        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        final CallSite site = LambdaMetafactory.metafactory(lookup,
                "invoke",
                MethodType.methodType(SilentInvoker.class),
                SilentInvoker.SIGNATURE,
                lookup.findVirtual(Callable.class, "call", MethodType.methodType(Object.class)),
                SilentInvoker.SIGNATURE);
        SilentInvoker SILENT_INVOKER = (SilentInvoker) site.getTarget().invokeExact();
        return SILENT_INVOKER.invoke(callable);
    }

    @FunctionalInterface
    interface SilentInvoker {
        MethodType SIGNATURE = MethodType.methodType(Object.class, Callable.class);
        <V> V invoke(final Callable<V> callable);
    }
}
