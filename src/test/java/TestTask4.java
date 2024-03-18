import org.example.task4.FilesData;
import org.example.task4.FilesReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestTask4 {
    @Test
    public void testReadFolder(){
        FilesData f = new FilesReader("src/main/resources/log/").get();
        System.out.println(l);
    }
    @Test
    public void testReadFile() throws IOException {
        var files = new FilesReader().getFiles("src/main/resources/log/");
        var data = new DataReader().getData(files);
        System.out.println(data);
    }*/
}
