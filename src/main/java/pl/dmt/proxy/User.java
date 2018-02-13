package pl.dmt.proxy;

public class User implements IUser {
    private final String name;

    public User() {
        this(null);
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
