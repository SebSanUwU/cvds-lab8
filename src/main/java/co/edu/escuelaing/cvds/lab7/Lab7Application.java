package co.edu.escuelaing.cvds.lab7;

import co.edu.escuelaing.cvds.lab7.model.Configuration;
import co.edu.escuelaing.cvds.lab7.model.Employee;
import co.edu.escuelaing.cvds.lab7.service.ConfigurationService;
import co.edu.escuelaing.cvds.lab7.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class Lab7Application {
	@Autowired
	ConfigurationService configurationService;
	@Autowired
	EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(Lab7Application.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return (args) -> {

			System.out.println("Adding Configurations....");
			configurationService.addConfiguration(new Configuration("premio", "800000"));

			System.out.println("\nGetting all configurations....");
			configurationService.getAllConfigurations().forEach(configuration -> System.out.println(configuration));

			System.out.println("Adding Employees....");
			try {
				employeeService.cargarDatosDesdeJSON("src/main/resources/data/MOCK_DATA.json");
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}

}
