package be.intecbrussel.exercises.exercise6and8;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Maternity {
    public static void main(String[] args) throws IOException {
        Person person1 = new Person("Alison", "Apple", LocalDate.of(2002, 2,
                                                                    2));
        String fileName = person1.getFirstname() + person1.getLastName();
        Path path = Paths.get("files/exercises/exercise6/" + fileName + ".txt");

        createFile(path);
        writePersonToFile(path, person1);


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

    private static void writePersonToFile(Path path, Person person) {
        try (FileOutputStream fos = new FileOutputStream(path.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos) ) {
            oos.writeObject(person);
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


