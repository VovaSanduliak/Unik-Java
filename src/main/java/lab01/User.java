package lab01;

import java.util.UUID;

public class User {
    private UUID id;
    private String userName;

    public User(String userName) {
        this.id = UUID.randomUUID();
        this.userName = userName;
    }

    public UUID getId() {
        return this.id;
    }

    //public String getUserName() {}

    //public void setUserName() {}

    // TODO: toString
    // TODO: equals
    // TODO: hashCode
}
