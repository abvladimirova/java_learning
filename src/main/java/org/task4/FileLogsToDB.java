package org.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.task4.fileshandler.Data;
import org.task4.repository.LoginsRepo;
import org.task4.repository.UsersRepo;
import org.task4.writer.MyConsumer;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Supplier;


@Component
@PropertySource("classpath:application.properties")
public class FileLogsToDB {

    @Autowired Supplier<List<Path>>     logData;
    @Autowired Supplier<List<String>>   linesReader;
    @Autowired Supplier<List<Data>>     logObjects;
    @Autowired MyConsumer<List<Data>>   writer;
    @Autowired UsersRepo                usersRepo;
    @Autowired LoginsRepo               loginsRepo;

    public void make() {

    }
}
