package com.design_builder.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
//Реализуйте unit test-ы для всего функционала, используя фреймворк.
//Реализуйте fluent builder
//Создайте билдер под сохранение данных в базу данных. Его вывод могут быть или запросы SQL необходимые для сохранения объектов, или модели объектов для базы данных, с сохранением через ORM (типа EF Core).
