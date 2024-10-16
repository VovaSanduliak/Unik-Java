package lab03.Serializers;

import lab03.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONSerializerTest {

    private JSONSerializer<User> jsonSerializer;
    private User user;

    @BeforeEach
    void setUp() {
        jsonSerializer = new JSONSerializer<User>(User.class);
        user = new User("Barack Obama", "elpresidente@mail.com", "1234");

        House house = new House("Hen house", "Ne golovna, 1");
        house.addDevices(List.of(
                new LightDevice("Lights"),
                new HeatDevice("Electrical heater"),
                new LightDevice("Diode lighting")
        ));

        user.addHouse(house);
    }

    @Test
    void serializeSingleObject() throws IOException {
        String jsonString = jsonSerializer.serialize(user);

        assertAll(() -> {
            assertNotNull(jsonString);
            assertTrue(jsonString.contains("""
                    "userName":"Barack Obama\""""));
            assertTrue(jsonString.contains("""
                    "email":"elpresidente@mail.com\""""));
            assertTrue(jsonString.contains("""
                    "password":"1234"""));
        });
    }

    @Test
    void serializeList() throws IOException {
        List<User> users = List.of(
                new User("User2", "user2@mail.com", "12345"),
                new User("Jane Doe", "jane.doe@mail.com", "12345"),
                user
        );

        String jsonString = jsonSerializer.serialize(users);

        assertAll(() -> {
            assertNotNull(jsonString);
            assertTrue(jsonString.contains("""
                    "userName":"Barack Obama\""""));
            assertTrue(jsonString.contains("""
                    "userName":"Jane Doe\""""));
            assertTrue(jsonString.contains("""
                    "email":"elpresidente@mail.com\""""));
            assertTrue(jsonString.contains("""
                    "password":"1234"""));
        });
    }

    @Test
    void deserializeSingleObject() throws IOException {
        String jsonString = """
                {
                    "userName":"Barack Obama",
                    "email":"elpresidente@mail.com"
                }""";
        User deserializedUser = jsonSerializer.deserialize(jsonString);

        assertAll(() -> {
            assertNotNull(deserializedUser);
            assertEquals("Barack Obama", deserializedUser.getUserName());
            assertEquals("elpresidente@mail.com", deserializedUser.getEmail());
        });
    }

    @Test
    void deserializeList() throws IOException {
        String jsonString = """
                [
                    {
                        "userName":"Barack Obama",
                        "email":"elpresidente@mail.com",
                        "password":"1234"
                    }
                ]""";
        List<User> deserializedUsers = jsonSerializer.deserializeToList(jsonString);

        assertAll(() -> {
            assertNotNull(deserializedUsers);
            assertEquals(1, deserializedUsers.size());

            assertTrue(deserializedUsers.stream().anyMatch(
                            user -> "Barack Obama".equals(user.getUserName())
                                    && "elpresidente@mail.com".equals(user.getEmail())
                                    && "1234".equals(user.getPassword())),
                    "Expected user not found in the list");
        });
    }

    @Test
    void writeToFile() throws IOException {
        String fileName = "src/test/resources/Serialization-write-user.json";
        jsonSerializer.writeToFile(user, fileName);

        File file = new File(fileName);
        assertTrue(file.exists());

        String fileContent = Files.readString(Paths.get(fileName));

        assertAll(() -> {
            assertNotNull(fileContent);
            assertTrue(fileContent.contains("""
                    "userName":"Barack Obama\""""));
        });
    }

    @Test
    void writeListToFile() throws IOException {
        List<User> users = List.of(
                new User("User444444", "user2@mail.com", "12345"),
                new User("Jane Doe", "jane.doe@mail.com", "12345"),
                user
        );
        String fileName = "src/test/resources/Serialization-write-user-list.json";
        jsonSerializer.writeToFile(users, fileName);

        File file = new File(fileName);
        assertTrue(file.exists(), "File doesn't exist");

        String fileContent = Files.readString(Paths.get(fileName));

        assertAll(() -> {
            assertNotNull(fileContent);
            assertTrue(fileContent.contains("""
                    "userName" : "Barack Obama\""""));
            assertTrue(fileContent.contains("""
                    "userName" : "Jane Doe\""""));
            assertTrue(fileContent.contains("""
                    "userName" : "User2\""""));
        });
    }

    @Test
    void readFromFileMultipleUsers() throws IOException {
        String fileName = "src/test/resources/Serialization-read-user-list.json";

        List<User> deserializedUsers = jsonSerializer.readFromFile(fileName);

        assertAll(() -> {
            assertNotNull(deserializedUsers);
            assertEquals(3, deserializedUsers.size());

            assertTrue(deserializedUsers.stream().anyMatch(user ->
                    "Barack Obama".equals(user.getUserName()) &&
                            "elpresidente@mail.com".equals(user.getEmail()) &&
                            "1234".equals(user.getPassword())
            ), "Expected user 'Barack Obama' with these properties not found in the list");

            assertTrue(deserializedUsers.stream().anyMatch(user ->
                    "Jane Doe".equals(user.getUserName()) &&
                            "jane.doe@mail.com".equals(user.getEmail()) &&
                            "12345".equals(user.getPassword())
            ), "Expected user 'Jane Doe' with these properties not found in the list");
        });
    }
}