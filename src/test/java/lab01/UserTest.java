package lab01;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private String userName = "Vova Sanduliak";
    private String email = "thisisemail@mail.com";
    private String password = "password123";
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
        House house = new House("Henhouse", "Ne golovna, 1");
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
