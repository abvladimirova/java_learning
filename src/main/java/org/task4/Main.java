package org.task4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.task4.repository.LoginsRepo;
import org.task4.repository.UsersRepo;

//@SpringBootApplication(scanBasePackages = "org.example.task4")
public class Main {
    public static void main(String[] args) {

        //ApplicationContext ctx = SpringApplication.run(Main.class);
        ApplicationContext ctx = new AnnotationConfigApplicationContext("org.task4");


        ctx.getBean("fileLogsToDB", FileLogsToDB.class)
                .make();

    }
}
