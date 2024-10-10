package lab05.Comparators;

import lab05.User;

import java.util.Comparator;

public class UserComparator {
    public static Comparator<User> byEmail() {
        return Comparator.comparing(User::getEmail);
    }
}
