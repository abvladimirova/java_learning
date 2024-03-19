import org.junit.jupiter.api.Test;
import org.task4.Data;
import org.task4.StringToData;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StringToDataTest {
   @Test
    public void testStringToData(){
       Data example = new Data("username","Name Lastname", Date.valueOf("2024-01-01"),"app");
       String testString = "username;name lastname;2024-01-01;app";
       assertEquals(example,new StringToData().convert(testString));
    }
    @Test
    public void testEmptyUsername(){
        Data example = new Data("","Name Lastname", Date.valueOf("2024-01-01"),"app");
        String testString = ";;2024-01-01;app";
        assertEquals(example,new StringToData().convert(testString));
    }
    @Test
    public void testWrongData(){
        String testString = "username;name lastname;str;app";
        assertThrows(RuntimeException.class, () -> new StringToData().convert(testString));
    }
    @Test
    public void testEmptyString(){
        String testString = "";
        assertThrows(RuntimeException.class, () -> new StringToData().convert(testString));
    }
    @Test
    public void testWrongString(){
        String testString = "username;name lastname";
        assertThrows(RuntimeException.class, () -> new StringToData().convert(testString));
    }
    @Test
    public void testEmptyFIO(){
        Data example = new Data("username","", Date.valueOf("2024-01-01"),"app");
        String testString = "username;;2024-01-01;app";
        assertEquals(example,new StringToData().convert(testString));
    }

}
