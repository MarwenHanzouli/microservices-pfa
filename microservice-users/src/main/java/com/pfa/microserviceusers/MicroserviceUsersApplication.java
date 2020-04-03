package com.pfa.microserviceusers;

import com.pfa.microserviceusers.models.*;
import com.pfa.microserviceusers.models.enumuration.RoleName;
import com.pfa.microserviceusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableJpaAuditing
public class MicroserviceUsersApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUsersApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Override
	public void run(String... args) throws Exception {

		//userService.saveUser(new User("marwen","1234","marwenhanzouli@gmail.com", RoleName.USER));
		userService.saveUser(new User("admin","1234","adminadmin@gmail.com",RoleName.ADMIN));
		userService.saveUser(new Manager("nourd","1234","noureddine0arfaoui@gmail.com",RoleName.MANAGER,"Mind Engineering"));
		userService.saveUser(new Candidat("yooo","1234","yoyoyoyoyyo@gmail.com",RoleName.CANDIDAT));
		userService.saveUser(new Candidat("aymen","1234","aymeen@gmail.com",RoleName.CANDIDAT));
		userService.saveUser(new Manager("marween","1234","noureddineou@gmail.com",RoleName.MANAGER,"Poulina"));
//		userService.addRoleToUser("marwen",RoleName.USER);
//		userService.addRoleToUser("admin",RoleName.ADMIN);
//		userService.addRoleToUser("nourd",RoleName.MANAGER);
//		userService.addRoleToUser("yooo",RoleName.MANAGER);
	}
}
