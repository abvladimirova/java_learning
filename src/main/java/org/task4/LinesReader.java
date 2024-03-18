package org.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.*;

@Component
public class LinesReader implements Supplier<List<String>> {
    List<Path> files;
    @Value("${files.charset}") String fileCharset;

    public LinesReader(@Autowired List<Path> files) {
        this.files = files;
    }
    @Override
    @Bean(name="getLines") @DependsOn("getFiles")
    public List<String> get() {
        var linesData = new ArrayList<String>();
        for (Path f:files) {
            try {
                Stream<String> lines = Files.lines(Path.of(f.toString()), Charset.forName(fileCharset));
                linesData.addAll(lines.collect(Collectors.toList()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return linesData;
    }
}
