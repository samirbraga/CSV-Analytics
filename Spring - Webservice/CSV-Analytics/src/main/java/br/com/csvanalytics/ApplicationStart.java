package br.com.csvanalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.csvanalytics.endpoint")
public class ApplicationStart {
    public static void main(String args []){
        SpringApplication.run(ApplicationStart.class, args);
    }
}
