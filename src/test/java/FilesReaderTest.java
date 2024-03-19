import org.junit.jupiter.api.Test;
import org.task4.Data;
import org.task4.FilesReader;
import org.task4.StringToData;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilesReaderTest {
    @Test
    public void testReadingExistingFile(){
        File f = new File("src/test/java/resources/log/test.log");
        boolean fileCreated = false;
        try {
            fileCreated = f.createNewFile();
        }
        catch (IOException e) {
            throw (new RuntimeException(e));
        }
        //FilesReader fr = new FilesReader().get()
        String testString = "username;name lastname;2024-01-01;app";
        assertTrue(fileCreated);
    }
}
