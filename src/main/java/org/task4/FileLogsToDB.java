package org.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;


@Component
@PropertySource("classpath:myApp.properties")
public class FileLogsToDB {

    @Autowired Supplier<List<Path>>     logData;
    @Autowired Supplier<List<String>>   linesReader;
    @Autowired Supplier<List<Data>>     logObjects;
    @Autowired Writer                   writer;

    public void make() {
    }
}
