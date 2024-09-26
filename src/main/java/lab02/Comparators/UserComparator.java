package lab02.Comparators;

import lab02.User;

import java.util.Comparator;

public class UserComparator {
    public static Comparator<User> byEmail() {
        return Comparator.comparing(User::getEmail);
    }
}
