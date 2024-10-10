package lab04.Comparators;

import lab04.User;

import java.util.Comparator;

public class UserComparator {
    public static Comparator<User> byEmail() {
        return Comparator.comparing(User::getEmail);
    }
}
