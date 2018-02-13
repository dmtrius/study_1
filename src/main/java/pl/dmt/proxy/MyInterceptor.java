package pl.dmt.proxy;

public class MyInterceptor {
    User user;

    public MyInterceptor(User user) {
        this.user = user;
    }

    public String getName() {
        return user.getName().toUpperCase();
    }
}
