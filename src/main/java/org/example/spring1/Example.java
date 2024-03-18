package org.example.spring1;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
public class Example {
    private String text;
    private LocalDateTime date;

    public Example() {
        this.text = "Hello World!";
        this.date = java.time.LocalDateTime.now();
    }

    public String sayHello() {
        return text;
    }

    public LocalDateTime BeanDate(){
        return date;
    }

    Predicate<Integer> isMark(){
        Predicate<Integer> isMark = i -> (i >=2 && i <=5);
        return isMark;
    }
}
