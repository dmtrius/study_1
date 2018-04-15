package pl.dmt.proxy;

public class MyInterceptor {
    private User user;

    MyInterceptor(User user) {
        this.user = user;
    }

    public String getName() {
        return user.getName().toUpperCase();
    }
}
