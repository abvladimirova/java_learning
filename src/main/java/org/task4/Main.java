package org.task4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

//@SpringBootApplication(scanBasePackages = "org.example.task4")
public class Main {
    public static void main(String[] args) {

        //ApplicationContext ctx = SpringApplication.run(Main.class);
        new AnnotationConfigApplicationContext("org.task4")
                .getBean("fileLogsToDB", FileLogsToDB.class)
                .make();


    }
}
