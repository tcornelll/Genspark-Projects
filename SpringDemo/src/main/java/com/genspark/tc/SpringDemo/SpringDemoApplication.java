package com.genspark.tc.SpringDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringDemoApplication.class, args);
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		ApplicationContext jContext = new AnnotationConfigApplicationContext(SpringConfig.class);

		//xml student
		Student student = (Student) context.getBean("student");
		System.out.println(student.toString());

		//Java context student
		Student frank = (Student) jContext.getBean("studentFrank");
		System.out.println(frank.toString());
	}

}
