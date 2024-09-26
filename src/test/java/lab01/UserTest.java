package lab01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private final String userName = "Vova";
    private final String email = "thisisemail@mail.com";
    private final String password = "password123";
    private User user;

    @BeforeEach
    public void beforeEach() {
        this.user = new User(userName, email, password);
    }

    @Test
    public void userCreationTest() {
        assertAll(() -> {
            assertNotNull(user.getId());
            assertEquals(userName, user.getUserName());
            assertEquals(email, user.getEmail());
            assertEquals(password, user.getPassword());
        });
    }

    @Test
    public void testAddRemoveHouse() {
        House house = new House("Hen house", "Ne golovna, 1");
        user.addHouse(house);

        assertTrue(user.getHouses().contains(house));
    }

    @Test
    public void testEqualsAndHashCode() {
        User user2 = new User(userName, email, password);

        assertAll(() -> {
            assertNotEquals(user, user2);
            assertNotEquals(user.hashCode(), user2.hashCode());
        });
    }
}
