package be.intecbrussel.exercises;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class O2_WriteDataApp {
    public static void main(String[] args) {
        Path path = Paths.get("files/exercises/exercise2/exercise.txt");
        // note: starting with a '/', results in 2 exceptions... why?

        createFile(path);
        writeDataToFile(path);
//        setFileReadOnly(path); // not working
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

    // write data with help of FileWriter
    private static void writeDataToFile(Path path) {
        try (FileWriter fileWriter = new FileWriter(path.toFile(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("hello" + "\n");
            bufferedWriter.write("world" + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // set attribute setReadOnly to true
//    private static void setFileReadOnly(Path path) {
//        try {
//            Files.setAttribute(path, "dos:isReadOnly", true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
