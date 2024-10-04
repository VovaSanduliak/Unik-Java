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

public class XMLSerializerTest {
    private XMLSerializer<User> xmlSerializer;
    private User user;

    @BeforeEach
    void setUp() {
        xmlSerializer = new XMLSerializer<>(User.class);
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
        String xmlString = xmlSerializer.serialize(user);

        assertAll(() -> {
            assertNotNull(xmlString);
            assertTrue(xmlString.contains("<userName>Barack Obama</userName>"));
            assertTrue(xmlString.contains("<email>elpresidente@mail.com</email>"));
            assertTrue(xmlString.contains("<password>1234</password>"));
        });
    }

    @Test
    void serializeList() throws IOException {
        List<User> users = List.of(
                new User("User2", "user2@mail.com", "12345"),
                new User("Jane Doe", "jane.doe@mail.com", "12345"),
                user
        );

        String xmlString = xmlSerializer.serialize(users);

        assertAll(() -> {
            assertNotNull(xmlString);
            assertTrue(xmlString.contains("<userName>User2</userName><email>user2@mail.com</email><password>12345</password>"));
            assertTrue(xmlString.contains("<userName>Jane Doe</userName><email>jane.doe@mail.com</email><password>12345</password>"));
            assertTrue(xmlString.contains("<userName>Barack Obama</userName><email>elpresidente@mail.com</email><password>1234</password>"));
        });
    }

    @Test
    void deserializeSingleObject() throws IOException {
        String xmlString = """
                <User>
                    <userName>Barack Obama</userName>
                    <email>elpresidente@mail.com</email>
                    <password>1234</password>
                </User>""";
        User deserializedUser = xmlSerializer.deserialize(xmlString);

        assertAll(() -> {
            assertNotNull(deserializedUser);
            assertEquals("Barack Obama", deserializedUser.getUserName());
            assertEquals("elpresidente@mail.com", deserializedUser.getEmail());
        });
    }

    @Test
    void deserializeList() throws IOException {
        String xmlString = "<ListN>" +
                "    <item>" +
                "        <userName>User2</userName>" +
                "        <email>user2@mail.com</email>" +
                "        <password>12345</password>" +
                "        <id>d54c8d0d-d641-4ef6-b281-22f9ab301084</id>" +
                "        <houses/>" +
                "    </item>" +
                "    <item>" +
                "        <userName>Jane Doe</userName>" +
                "        <email>jane.doe@mail.com</email>" +
                "        <password>12345</password>" +
                "        <id>bd0a5a3a-02e8-48ca-a8ad-3c507723ac4a</id>" +
                "        <houses/>" +
                "    </item>" +
                "    <item>" +
                "        <userName>Barack Obama</userName>" +
                "        <email>elpresidente@mail.com</email>" +
                "        <password>1234</password>" +
                "        <id>9f3784fb-a590-40f7-b504-a80e0fb9e846</id>" +
                "        <houses/>" +
                "    </item>" +
                "</ListN>";
        List<User> deserializedUsers = xmlSerializer.deserializeToList(xmlString);

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
        String fileName = "src/test/resources/Serialization-write-user.json.xml";
        xmlSerializer.writeToFile(user, fileName);

        File file = new File(fileName);
        assertTrue(file.exists());

        String fileContent = Files.readString(Paths.get(fileName));

        assertAll(() -> {
            assertNotNull(fileContent);
            assertTrue(fileContent.contains("<userName>Barack Obama</userName>"));
            assertTrue(fileContent.contains("<email>elpresidente@mail.com</email>"));
            assertTrue(fileContent.contains("<password>1234</password>"));
        });
    }

    @Test
    void WriteListToFile() throws IOException {
        List<User> users = List.of(
                new User("User2", "user2@mail.com", "12345"),
                new User("Jane Doe", "jane.doe@mail.com", "12345"),
                user
        );
        String fileName = "src/test/resources/Serialization-write-user-list.xml";
        xmlSerializer.writeToFile(users, fileName);

        File file = new File(fileName);
        assertTrue(file.exists(), "File doesn't exist");

        String fileContent = Files.readString(Paths.get(fileName));

        assertAll(() -> {
            assertNotNull(fileContent);
            assertTrue(fileContent.contains("<userName>Barack Obama</userName>"));
            assertTrue(fileContent.contains("<email>elpresidente@mail.com</email>"));
            assertTrue(fileContent.contains("<password>1234</password>"));
        });
    }

    @Test
    void readFromFileMultipleUsers() throws IOException {
        String fileName = "src/test/resources/Serialization-read-user-list.xml";

        List<User> deserializedUsers = xmlSerializer.readFromFile(fileName);

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
