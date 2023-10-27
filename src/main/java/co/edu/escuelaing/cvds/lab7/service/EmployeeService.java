package co.edu.escuelaing.cvds.lab7.service;

import co.edu.escuelaing.cvds.lab7.model.Configuration;
import co.edu.escuelaing.cvds.lab7.model.Employee;
import co.edu.escuelaing.cvds.lab7.repository.ConfigurationRepository;
import co.edu.escuelaing.cvds.lab7.repository.EmployeeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long employeeId){
        return employeeRepository.getReferenceById(employeeId);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteUser(Long userId) {
        employeeRepository.deleteById(Long.valueOf(userId));
    }

    public void cargarDatosDesdeJSON(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Employee> employees=objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<Employee>>() {});
        // Guardar los empleados en la base de datos
        employeeRepository.saveAll(employees);
    }

}
