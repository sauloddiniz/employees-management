package br.com.employeesmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class EmployeesManagementApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void main() {
		EmployeesManagementApplication.main(new String[] {});
	}

}
