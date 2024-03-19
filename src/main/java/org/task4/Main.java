package org.task4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(scanBasePackages = "org.task4")
public class Main {
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        //ApplicationContext ctx = new AnnotationConfigApplicationContext("org.task4");
        ctx.getBean("fileLogsToDB", FileLogsToDB.class)
                .make();

    }
}
