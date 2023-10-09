package firstTry;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class ReadFile {

    /**
     * Read the content of a file and return it as a string.
     *
     * @param filePath The path to the file.
     * @return The content of the file as a string.
     * @throws IOException If an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read.
     */
    public static String readFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static void main(String[] args) {
        // Test
        try {
            String content = readFileAsString("ue01/src/firstTry/crsto12.html");
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
