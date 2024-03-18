package org.example.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;

@Component
public class FilesReader implements Supplier<FilesData> {
    private String path;

    public FilesReader(@Autowired String path) {
        this.path = path;
    }

    @Bean(name="getFiles") @DependsOn("getPath")
    public FilesData get() {
        FilesData filesData = new FilesData();
        try {
            Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        filesData.files.add(file);
                    });
        } catch (IOException e) {
            throw new RuntimeException("Get file Error " + e);
        }
        return filesData;
    }

}
