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
            if (Files.notExists(path)) { // really needed, see below comment
                Files.createFile(path); // rights are needed to create files
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
    } // Ooooff, quite ugly code. Can we do this differently? See method2:

    // in below, we use a try with resources - we create the resource by
    // giving it as a parameter to the try block. The try with resources has
    // an automatic closure block included, which ensures the FileWriter will
    // be closed - no need to write it in code. This works only with classes
    // which implement the AutoClosable interface.

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
            int character; // we assign our .read() to a variable
            while((character = fileReader.read()) != -1) { // while(true) is
                // handy to see
                // what this
                // method does
                System.out.println((char) character); // as .read provides
                // unicode (ints) we typecast our ints to chars
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
