package org.example.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;


@Component
public class FileLogsToDB {

    @Autowired Supplier<String>         pathReader;
    @Autowired FilesData                filesData;
    @Autowired LinesData                linesData;
    @Autowired Supplier<List<Data>>     logObjects;
    @Autowired Consumer<List<Data>>     writer;
    //@Autowired Supplier<String>         pathReader;
    //@Autowired Supplier<LogData>        filesReader;

    //@Autowired ConvertData              converter;

    public void make() throws IOException {
        //this.path            = "";
        //this.path            = pathReader.get();
        //logData         = filesReader.get();
        //logData.rawData = dataReader.get();
        //logObjects      = converter.convert();
        //writer.accept(logObjects);

        //writerDataBase.loadindatabase();
    }
}
