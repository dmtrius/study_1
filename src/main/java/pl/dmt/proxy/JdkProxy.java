package pl.dmt.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JdkProxy {
    public static void main(String... args) {
        User user = new User("Вася");

        InvocationHandler handler = (proxy, method, argss) -> {
            if (method.getName().equals("getName")) {
                return ((String) method.invoke(user, argss)).toUpperCase();
            }
            return method.invoke(user, argss);
        };

        IUser userProxy = (IUser) Proxy.newProxyInstance(user.getClass().getClassLoader(), User.class.getInterfaces(), handler);
        System.out.println(userProxy.getName());
    }
}
