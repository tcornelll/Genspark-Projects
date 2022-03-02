package com.genspark.tc.SpringDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

@Configuration
//@ComponentScan(basePackages = "com.genspark.tc.SpringDemo")
public class SpringConfig {
    @Bean
    public Student studentJonas() {
        Student jonas = new Student();
        jonas.setStudendId(1);
        jonas.setName("Jonas Weezer");
        jonas.setContactNumbers(Stream.of("8675309").toList());
        jonas.setAdd(addAustin());
        return jonas;
    }

    @Bean
    public Address addAustin() {
        Address austin = new Address();
        austin.setCity("Austin");
        austin.setState("Texas");
        austin.setCountry("US");
        return austin;
    }

    @Bean
    public Student studentFrank() {
        return new Student(2, "Frank Castellano", Stream.of("5555555", "6766699").toList(), addMO());
    }

    @Bean
    public Address addMO() {
        return new Address("St. Louis", "Missouri", "United States");
    }
}
