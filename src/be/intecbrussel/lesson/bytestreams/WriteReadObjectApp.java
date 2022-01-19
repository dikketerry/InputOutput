package be.intecbrussel.lesson.bytestreams;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class WriteReadObjectApp {

    public static void main(String[] args) {
        // objects
        String text = new String("text text text");
        LocalDateTime date = LocalDateTime.now();

        // location
        Path path = Paths.get("files/bytestreams/textfile.ser");

        // methods
        writeObjects(path, text, date);
        readObjects(path);
    }

    // method to write objects to file located at path variable
    private static void writeObjects(Path path, String text,
                                     LocalDateTime date) {
        try (FileOutputStream file = new FileOutputStream(path.toFile());
             ObjectOutputStream out = new ObjectOutputStream(file)) {
            // ObjectOutputStream is specifically for handling objects:
            // it facilitates streaming objects, in order to write or
            // read these, to / from files, locations, whatever.
            // Objects handled by the ObjectOutputStream need to be
            // Serializable, meaning, objects need to have the interface
            // Serializable implemented. Serializable picks up the instance
            // variables of the object in the stream.
            out.writeObject(text);
            out.writeObject(date);
        }
        catch (IOException e) {
            e.printStackTrace(); // TODO: find alternative for printStackTrace
        }
    }

    // method to read objects from file located at path variable
    private static void readObjects(Path path) {
        try (FileInputStream file = new FileInputStream(path.toFile());
             ObjectInputStream in = new ObjectInputStream(file)) {
            String text = (String) in.readObject(); // how does this
            // recognize the String object in the file?? (same for below)
            LocalDateTime date = (LocalDateTime) in.readObject();
            System.out.println(text);
            System.out.println(date);
            System.out.format("%td/%<tm/%<tY %<tH:%<tM:%<tS%n",date);
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
    }

}
