package pl.dmt.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class CglibProxy {
    public static void main(String... args) {
        User user = new User("Вася");

        MethodInterceptor handler = (obj, method, argss, proxy) -> {
            if (method.getName().equals("getName")) {
                return ((String) proxy.invoke(user, argss)).toUpperCase();
            }
            return proxy.invoke(user, argss);
        };

        User userProxy = (User) Enhancer.create(User.class, handler);
        System.out.println(userProxy.getName());
    }
}
