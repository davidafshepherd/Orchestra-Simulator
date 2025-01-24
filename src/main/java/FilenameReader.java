import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class used to represent a file reader
 */
public class FilenameReader {

    //Reader used by this file reader
    BufferedReader reader;

    /**
     * Instantiates a file reader
     * @param fileName file that the file reader is being used to read
     */
    public FilenameReader(String fileName) {
        try {
            reader = new BufferedReader(new FileReader(fileName));
        }
        catch (Exception FileNotFoundException) {
            throw new RuntimeException("The file was not found");
        }
    }

    /**
     * Gets the next line to be read from the file
     * @return next line
     */
    public String getLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("There is no line to read");
        }
    }

    /**
     * Checks if the reader is ready to read the next line from the file
     * @return true if ready and false if not
     */
    public Boolean fileIsReady() {
        try {
            return reader.ready();
        }
        catch (Exception RuntimeException) {
            return false;
        }
    }
}
