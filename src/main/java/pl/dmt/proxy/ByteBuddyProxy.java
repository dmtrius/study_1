package pl.dmt.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteBuddyProxy {

    public static void main(String... args) throws IllegalAccessException, InstantiationException {
        User user = new User("Вася");

        User userProxy = new ByteBuddy()
                .subclass(User.class)
                .method(named("getName"))
                .intercept(MethodDelegation.to(new MyInterceptor(user)))
                .make()
                .load(User.class.getClassLoader())
                .getLoaded()
                .newInstance();

        System.out.println(userProxy.getName());
    }
}
