package org.task4.fileshandler;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@NoArgsConstructor
@Component
public class FilesReader implements Supplier<List<Path>> {
    @Value("${files.path}") String path;

    @Bean(name="getFiles")
    public List<Path> get() {
        List<Path> files = new ArrayList<>();
        try {
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .forEach(files::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return files;
    }

}
