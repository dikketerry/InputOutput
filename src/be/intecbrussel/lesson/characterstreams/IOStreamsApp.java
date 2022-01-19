package be.intecbrussel.lesson.characterstreams;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOStreamsApp {
    public static void main(String[] args) {
        Path path = Paths.get("files/IOfiles/Notebook.txt");

        createFile(path);
        writeDataToFile2(path);
        readDataFromFile(path);
    }

    private static void createFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path)) { // check if file not already exists
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void writeDataToFile(Path path) {
        FileWriter fileWriter = null; // FileWriter is a IOStream
        try {
            fileWriter = new FileWriter(path.toFile()); // open connection to
            // a IOstream
            fileWriter.write("Hello "); // write data through the stream to
            // the file
            fileWriter.write("world, ");
            fileWriter.write("Jean-Pierre."); // stream still open...
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close(); // required, analogy with 'save'.
                // when we would run again, data is overwritten!
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } // Ooooff, quite long code. Can this be done differently? See method2:

    // use try with resources - create the resource by prioviding it as a
    // parameter to the try block. The try with resources has
    // an automatic closure block included, which ensures FileWriter will
    // be closed: no need to write the closure. This works only with classes
    // that implement AutoClosable interface.

    private static void writeDataToFile2(Path path) {
        try (FileWriter fileWriter = new FileWriter(path.toFile()) ){
            fileWriter.write("Hello ");
            fileWriter.write("world, ");
            fileWriter.write("Jean-Pierre.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readDataFromFile(Path path) {
        try (FileReader fileReader = new FileReader(path.toFile()) ) {
            // FileReader is a IOStream
            int character; // to assign result of .read() to a variable
            while((character = fileReader.read()) != -1) {
                System.out.println((char) character); // as .read() provides
                // unicode (ints), typecast ints to chars
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
