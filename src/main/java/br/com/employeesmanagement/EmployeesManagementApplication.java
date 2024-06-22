package br.com.employeesmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "br.com.employeesmanagement.infraestructure.client")
public class EmployeesManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesManagementApplication.class, args);
	}

}
