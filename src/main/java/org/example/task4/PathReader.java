package org.example.task4;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.function.Supplier;
@Component
public class PathReader implements Supplier<String> {

    @Override
    //@Bean(name="getPath")
    public String get() {
        return new Scanner(System.in).nextLine();
    }
}

