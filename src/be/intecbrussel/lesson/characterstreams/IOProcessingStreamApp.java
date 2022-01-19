package be.intecbrussel.lesson.characterstreams;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOProcessingStreamApp {
    public static void main(String[] args) {
        Path path = Paths.get("files/IOfiles/ProcessedStreams.txt");

        createFile(path);
        writeDataToFile(path);
        readDataFromFile(path);


    }

    private static void createFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void writeDataToFile(Path path) {
        try (FileWriter fileWriter = new FileWriter(path.toFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("Gutentag ");
            bufferedWriter.write("zum Welt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readDataFromFile(Path path) {
        try (FileReader fileReader = new FileReader(path.toFile());
             BufferedReader bufferedReader = new BufferedReader(fileReader) ) {
            String line;
            while ( (line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
