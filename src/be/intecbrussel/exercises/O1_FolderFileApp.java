package be.intecbrussel.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.StandardCopyOption.*;

// exercise 1 FileIO - .readAttributes() is not working:
// UnsupportedOperationException
public class O1_FolderFileApp {
    public static void main(String[] args) {
//        Files.createDirectories(Paths.get("files/exercises/exercise1"));

        Path path = Paths.get("files/exercises/exercise1/exercise1.txt");

        createFile(path);
        writeDataToFile(path);
//        readAttributesOfFile(path);
        readDataFromFile(path);
        printOwnerFile(path);
        renameFile(path);
    }

    // create directory and file
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

    // write data to file
    private static void writeDataToFile(Path path) {
        List<String> lines = new ArrayList<>();
        lines.add("hello");
        lines.add("world");

        try {
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read attributes of file
    private static void readAttributesOfFile(Path path) {
        try {
            DosFileAttributes attrbs = Files.readAttributes(path,
                                                            DosFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read data from file
    private static void readDataFromFile(Path path) {
        try {
            Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // print owner of file
    private static void printOwnerFile(Path path) {
        try {
            System.out.println("owner of file: " + Files.getOwner(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // rename file (with help of Files.move() method)
    private static void renameFile(Path path) {
        try {
            Files.move(path, path.resolveSibling("exerciseNewName.txt"),
                       REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
