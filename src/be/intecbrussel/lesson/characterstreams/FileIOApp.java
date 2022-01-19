package be.intecbrussel.lesson.characterstreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileIOApp {
    public static void main(String[] args) {

        Path path = Paths.get("files/awesomefiles/MyAwesomeFile.txt");
        // class Path is an interface
        // class Paths is a helper class for Path
        System.out.println(path.toAbsolutePath());
        // absolutePath points at the file and its location - we can notice
        // here that the default location of the file we created with file
        // .txt will be the project location
        System.out.println(path);
        // above line prints the relative path, meaning the path relative to
        // its location (which is the default folder >> the project folder)

        Path path2 = Paths.get("files");
        path2 = path2.resolve("awesomefiles");
        path2 = path2.resolve("MyAwesomeFile.txt");
        // with .resolve() we can go deeper for the purpose of navigation
        // opposite of .getParent()
        System.out.println(path2);

        // path = files/awesomefiles/MyAwesomeFile.txt
        // path.getParent() = files/awesomefiles
        // path.getParent().resolve(...) = files/awesomefiles/MyAwesomeCopy.txt

        Path copiedPath = path.getParent().resolve("MyAwesomeCopy.txt");

        createFile(path);
        writeDataToFile(path);
        readFile(path);
        copyFile(path, copiedPath);
        deleteFile(path);

    }

    private static void createFile(Path path) {
        try {
            // Files.createDirectory(path.getParent()); // for creating 1
            // directory; 1 level higher than the file
            Files.createDirectories(path.getParent()); // ...directories makes
            // all directories required
            if (!Files.exists(path)) { // really needed, see below comment
                Files.createFile(path); // rights are needed to create files
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        // IMPORTANT: if a file already exists, an exception will be
        // thrown. How to overcome that? Note that the createDirectories does
        // NOT throw the exception; it is only the createFile method which
        // throws exception. This means we do not need to pay attention to
        // the createDirectories. We do need to perform a check to see if a
        // file already exists. See above line if (!Files.exists(path)) {
    }

    private static void writeDataToFile(Path path) {
        // how are we going to write data to our newly created file? We need
        // data for that:
        List<String> lines = new ArrayList<>();
        lines.add("Today we are Monday");
        lines.add("We are not money");
        // above we can see sort of our memory

        // realize here we have to always create a collection (List) to add
        // data to our file. An alternative is IO-streams (chapter 21.3 in
        // Java Fundamentals). IO-streams is NOT the same as Streams,
        // although it does follow a similar concept: open a stream to a
        // file, write data to it, write again, write again, when done, close
        // the stream

        // there are 2 types of io streams:
        // character streams
        // byte streams
        // within these, there are 2 other types (so character or byte stream
        // can be):
        // Data sink stream: from source to app, or from app to source
        // Processing stream: manipulate / process the data. Similar to the
        // intermediate methods in Streams
        // A data sink stream is always needed to send the data through

        // Now we can write the above data:
        try {
            Files.write(path, lines);
        } catch(IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void readFile(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(System.out::println);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // general note: when working with 'external' files or databases, most of
    // the code / methods will have to be in try catch blocks and throw
    // exceptions, due to the fact that there is no guarantee communication
    // with the 'externals' will work properly.

    // another general note: mac / linux uses forward slashes for paths,
    // Windows uses backward slashes (backslash). Compiler will accept both
    // when you want to create a path. Of course, as backslash is ALSO an
    // escape character, when using backslashes you will need to use 2

    private static void copyFile(Path path, Path copiedPath) {
        try {
            Files.copy(path, copiedPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFile(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
