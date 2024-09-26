package lab02.Comparators;

import lab02.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserComparatorTest {
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User("Barack Obama", "elpresidente@mail.com", "1234");
        user2 = new User("Oleksandr Oleksandrovich", "happyhenhouse@mail.com", "1234");
    }

    @Test
    void byEmail() {
        assertTrue(UserComparator.byEmail().compare(user1, user2) < 0);
    }
}