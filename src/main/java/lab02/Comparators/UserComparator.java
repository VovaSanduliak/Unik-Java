package lab02.Comparators;

import lab02.User;

import java.util.Comparator;

/**
 * The {@code UserComparator} class provides a comparator for comparing {@link User} objects
 * based on their email addresses.
 */
public class UserComparator {
    /**
     * Returns a comparator that compares {@link User} objects by their email addresses.
     *
     * @return a comparator that compares {@link User} objects by email
     */
    public static Comparator<User> byEmail() {
        return Comparator.comparing(User::getEmail);
    }
}
