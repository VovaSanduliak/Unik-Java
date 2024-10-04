package lab03.Serializers;

import lab03.HeatDevice;
import lab03.House;
import lab03.LightDevice;
import lab03.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YAMLSerializerTest {
    private YAMLSerializer<User> yamlSerializer;
    private User user;

    @BeforeEach
    void setUp() {
        yamlSerializer = new YAMLSerializer<>(User.class);
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
        String yamlString = yamlSerializer.serialize(user);

        assertAll(() -> {
            assertNotNull(yamlString);
            assertTrue(yamlString.contains("""
                    userName: "Barack Obama\""""));
            assertTrue(yamlString.contains("""
                    email: "elpresidente@mail.com\""""));
            assertTrue(yamlString.contains("""
                    password: "1234\""""));
        });
    }

    @Test
    void serializeList() throws IOException {
        List<User> users = List.of(
                new User("User2", "user2@mail.com", "12345"),
                new User("Jane Doe", "jane.doe@mail.com", "12345"),
                user
        );

        String yamlString = yamlSerializer.serialize(users);

        assertAll(() -> {
            assertNotNull(yamlString);
            assertTrue(yamlString.contains("""
                    userName: "Barack Obama\""""));
            assertTrue(yamlString.contains("""
                    email: "elpresidente@mail.com\""""));
            assertTrue(yamlString.contains("""
                    password: "1234\""""));
        });
    }

    @Test
    void deserializeSingleObject() throws IOException {
        String yamlString = """
                ---
                userName: "Barack Obama"
                email: "elpresidente@mail.com"
                password: "1234"
                """;
        User deserializedUser = yamlSerializer.deserialize(yamlString);

        assertAll(() -> {
            assertNotNull(deserializedUser);
            assertEquals("Barack Obama", deserializedUser.getUserName());
            assertEquals("elpresidente@mail.com", deserializedUser.getEmail());
        });
    }

    @Test
    void deserializeList() throws IOException {
        String yamlString = """
                ---
                - userName: "User2"
                  email: "user2@mail.com"
                  password: "12345"
                  id: "d1459093-263b-4a08-bba9-bd342e2b7628"
                  houses: []
                - userName: "Jane Doe"
                  email: "jane.doe@mail.com"
                  password: "12345"
                  id: "82bec681-abda-42fd-ac1c-fde9abc457df"
                  houses: []
                - userName: "Barack Obama"
                  email: "elpresidente@mail.com"
                  password: "1234"
                  id: "647b5de4-5b95-4e97-89bd-07d698d5a02e"
                  houses: []
                """;
        List<User> deserializedUsers = yamlSerializer.deserializeToList(yamlString);

        assertAll(() -> {
            assertNotNull(deserializedUsers);
            assertEquals(3, deserializedUsers.size());

            assertTrue(deserializedUsers.stream().anyMatch(
                            user -> "Barack Obama".equals(user.getUserName())
                                    && "elpresidente@mail.com".equals(user.getEmail())
                                    && "1234".equals(user.getPassword())),
                    "Expected user not found in the list");
        });
    }

    @Test
    void writeToFile() throws IOException {
        String fileName = "src/test/resources/Serialization-write-user.yaml";
        yamlSerializer.writeToFile(user, fileName);

        File file = new File(fileName);
        assertTrue(file.exists());

        String fileContent = Files.readString(Paths.get(fileName));

        assertAll(() -> {
            assertNotNull(fileContent);
            assertTrue(fileContent.contains("""
                    userName: "Barack Obama\""""));
            assertTrue(fileContent.contains("""
                    email: "elpresidente@mail.com\""""));
            assertTrue(fileContent.contains("""
                    password: "1234\""""));
        });
    }

    @Test
    void WriteListToFile() throws IOException {
        List<User> users = List.of(
                new User("User2", "user2@mail.com", "12345"),
                new User("Jane Doe", "jane.doe@mail.com", "12345"),
                user
        );
        String fileName = "src/test/resources/Serialization-write-user-list.yaml";
        yamlSerializer.writeToFile(users, fileName);

        File file = new File(fileName);
        assertTrue(file.exists(), "File doesn't exist");

        String fileContent = Files.readString(Paths.get(fileName));

        assertAll(() -> {
            assertNotNull(fileContent);
            assertTrue(fileContent.contains("""
                    userName: "Barack Obama\""""));
            assertTrue(fileContent.contains("""
                    email: "elpresidente@mail.com\""""));
            assertTrue(fileContent.contains("""
                    password: "1234\""""));
        });
    }

    @Test
    void readFromFileMultipleUsers() throws IOException {
        String fileName = "src/test/resources/Serialization-read-user-list.yaml";

        List<User> deserializedUsers = yamlSerializer.readFromFile(fileName);

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
