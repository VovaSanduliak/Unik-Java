package lab03.Comparators;

import lab03.User;

import java.util.Comparator;

public class UserComparator {
    public static Comparator<User> byEmail() {
        return Comparator.comparing(User::getEmail);
    }
}
