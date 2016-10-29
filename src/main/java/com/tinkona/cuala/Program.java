package com.tinkona.cuala;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(value = {"com.tinkona.cuala"})
//@EnableWebMvc
public class Program {

    public static void main(String... args) {
        SpringApplication.run(Program.class, args);
    }
}
