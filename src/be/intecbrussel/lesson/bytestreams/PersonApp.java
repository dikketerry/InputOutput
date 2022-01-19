package be.intecbrussel.lesson.bytestreams;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonApp {
    public static void main(String[] args) {

        Path path = Paths.get("files/bytestreams/people.txt");
        Person person = new Person("Jean-Pierre", 55, "happy");
        createFile(path);

        // WRITE PERSON TO FILE
        try (FileOutputStream fos = new FileOutputStream(path.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(person); // serialization means conversion to
            // bytes - this needs to be arranged in the object class subject
            // to placing in output stream

        } catch (IOException e) {
            e.printStackTrace();
        }

        // READ PERSON FROM FILE
        try (FileInputStream fis = new FileInputStream(path.toFile());
        ObjectInputStream ois = new ObjectInputStream(fis)) {

            Person person1 = (Person) ois.readObject();

        } catch (IOException | ClassNotFoundException e) { //
            // ClassNotFoundException is another type of exception that needs
            // to be caught here: the class, in this case Person, could
            // potentially not exist, and for this an exception needs
            // to be caught
            e.printStackTrace();
        }
    }

    private static void createFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
