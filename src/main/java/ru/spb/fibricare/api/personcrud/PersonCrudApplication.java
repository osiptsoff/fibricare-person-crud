package ru.spb.fibricare.api.personcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class PersonCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonCrudApplication.class, args);
	}

}
