package be.intecbrussel.exercises;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DeflaterOutputStream;

public class O5_ByteStreamCompressor {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("files/exercises/exercise5/textCompressed.txt");

        createFile(path);
        writeAsCompressedDataToFile(path);
        readCompressedFile(path); // TODO
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

    private static void writeAsCompressedDataToFile(Path path) {
        try (FileOutputStream fos = new FileOutputStream(path.toFile());
             DeflaterOutputStream dos = new DeflaterOutputStream(fos);
             PrintStream ps = new PrintStream(dos)) {
            ps.write(4);
            ps.write(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readCompressedFile(Path path) {
        // TODO
    }
}
