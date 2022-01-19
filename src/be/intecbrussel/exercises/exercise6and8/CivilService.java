package be.intecbrussel.exercises.exercise6and8;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

// not working! throws classNotFoundException... ):

public class CivilService {
    public static void main(String[] args) throws IOException {
//        try (FileInputStream file = new FileInputStream("files/exercises" +
//                                                                "/exercise6" +
//                                                                "/AlisonApple" +
//                                                                ".txt");
//        ObjectInputStream in = new ObjectInputStream(file) ) {
//            Persona person = (Persona) in.readObject();
//            System.out.println(person.getFirstname());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        Path path = Paths.get("files/exercises/exercise6/AlisonApple.txt");
        readPersonFromFile(path);

        System.in.read(); // 'at the end of the program...' where is that? here?
    }

    private static void readPersonFromFile(Path path) {
        try (FileInputStream file = new FileInputStream(path.toFile());
             ObjectInputStream in = new ObjectInputStream(file)) {
            Person person = (Person) in.readObject();

            System.out.println(person.getFirstname());
            System.out.println(person.getLastName());
            System.out.println(person.getBirthDay());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
