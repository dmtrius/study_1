package pl.dmt.proxy;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

public class JavassistProxy {

    public static void main(String... args) throws IllegalAccessException, InstantiationException {
        User user = new User("Вася");

        MethodHandler handler = (self, overridden, forwarder, argss) -> {
            if (overridden.getName().equals("getName")) {
                return ((String) overridden.invoke(user, argss)).toUpperCase();
            }
            return overridden.invoke(user, argss);
        };

        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(User.class);
        Object instance = factory.createClass().newInstance();
        ((ProxyObject) instance).setHandler(handler);

        User userProxy = (User) instance;
        System.out.println(userProxy.getName());
    }
}
