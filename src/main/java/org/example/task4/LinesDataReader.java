package org.example.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;
import java.util.stream.*;
import java.util.List;

@Component
public class LinesDataReader implements Supplier<LinesData> {
    Exception readExc = new RuntimeException("Ошибка чтения файлов");
    FilesData filesData;
    public final String fileCharset = "windows-1251"; // TODO в настройки

    public LinesDataReader(@Autowired FilesData filesData) {
        this.filesData = filesData;
    }
    @Override
    @Bean(name="getLines") @DependsOn("getFiles")
    public LinesData get() {
        LinesData linesData = new LinesData();
        for (Path f:this.filesData.files) {
            try {
                Stream<String> lines = Files.lines(Path.of(f.toString()), Charset.forName(fileCharset));
                linesData.rawData.addAll(lines.collect(Collectors.toList()));
            } catch (IOException e) {
                throw new IllegalArgumentException("Невозможно прочитать файл");//TODO
            }
        }
        return linesData;
    }
}
