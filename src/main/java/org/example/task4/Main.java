package org.example.task4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import java.io.IOException;

//@SpringBootApplication(scanBasePackages = "org.example.task4")
public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("This application reads all *.log files from directory and puts it to database");
        System.out.println("enter valid directory path:");

        //ApplicationContext ctx = SpringApplication.run(Main.class);
        new AnnotationConfigApplicationContext("org.example.task4")
                .getBean("fileLogsToDB", FileLogsToDB.class)
                .make();


    }
}
