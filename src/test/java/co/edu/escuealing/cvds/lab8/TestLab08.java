package co.edu.escuealing.cvds.lab8;

import co.edu.escuelaing.cvds.lab7.model.Employee;
import co.edu.escuelaing.cvds.lab7.repository.EmployeeRepository;
import co.edu.escuelaing.cvds.lab7.service.EmployeeService;
import com.fasterxml.jackson.databind.JsonSerializer;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TestLab08 {
    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    private Employee employee1;

    private Employee employee2;

    private Employee employee3;

    @BeforeEach
    public void setup(){
        employee1 = Employee.builder()
                .employeeId(1L)
                .firstName("Juan")
                .lastName("Camargo")
                .role("Dev")
                .salary(1234D)
                .build();

        employee2 = Employee.builder()
                .employeeId(2L)
                .firstName("Juan")
                .lastName("Daza")
                .role("Dev")
                .salary(1234D)
                .build();

        employee3 = Employee.builder()
                .employeeId(3L)
                .firstName("Gabriela")
                .lastName("Díaz")
                .role("Front")
                .salary(1234D)
                .build();
    }

    @DisplayName("Consulta a nivel de servicio de un empleado")
    @Test
    public void consultaServicioPorId_thenReturnEmpleadoObject(){
        // given - precondition or setup
        given(employeeRepository.save(employee1)).willReturn(employee1);
        // when -  action or the behaviour that we are going test
        when(employeeService.getEmployee(employee1.getEmployeeId())).thenReturn(employee1);
        // then - verify the output
        Employee savedEmployee = employeeService.addEmployee(employee1);
        Long employeeId=savedEmployee.getEmployeeId();
        Employee queryEmployee = employeeService.getEmployee(employeeId);

        assertEquals("",savedEmployee.getEmployeeId(),queryEmployee.getEmployeeId());
    }

    @DisplayName("Consulta a nivel de servicio donde un empleado no esta registrado")
    @Test
    public void consultaServicioPorIdConUnEmpleadoNoRegistrado_thenReturnEmpleadoObject(){
        // given - precondition or setup

        // when -  action or the behaviour that we are going test
        when(employeeService.getEmployee(employee1.getEmployeeId())).thenReturn(null);
        // then - verify the output
        Long employeeId=employee1.getEmployeeId();
        Employee queryEmployee = employeeService.getEmployee(employeeId);
        assertThat(queryEmployee).isNull();
    }

    @DisplayName("Creacion de un empleado al repositorio")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        // given - precondition or setup
        given(employeeRepository.save(employee1)).willReturn(employee1);
        // when -  action or the behaviour that we are going test
        when(employeeService.addEmployee(employee1)).thenReturn(employee1);
        // then - verify the output
        Employee savedEmployee = employeeService.addEmployee(employee1);
        assertThat(savedEmployee).isNotNull();
    }
    @DisplayName("Eliminación de un empleado al repositorio")
    @Test
    public void givenEmployeeObject_whenDeleteEmployee_thenReturnEmployeeObject(){
        // given - precondition or setup
        willDoNothing().given(employeeRepository).deleteById(employee1.getEmployeeId());
        // when -  action or the behaviour that we are going test
        employeeService.deleteUser(employee1.getEmployeeId());
        // then - verify the output
        verify(employeeRepository,times(1)).deleteById(employee1.getEmployeeId());
    }

    @DisplayName("Eliminación de un empleado al repositorio y su la consulta es ningun resutlado")
    @Test
    public void givenEmployeeObject_whenDeleteEmployeeAndQueryEmployee_thenReturnNull(){
        // given - precondition or setup
        willDoNothing().given(employeeRepository).deleteById(employee1.getEmployeeId());
        // when -  action or the behaviour that we are going test
        employeeService.deleteUser(employee1.getEmployeeId());
        when(employeeService.getEmployee(employee1.getEmployeeId())).thenReturn(null);
        // then - verify the output
        Employee queryEmployee = employeeService.getEmployee(employee1.getEmployeeId());
        assertThat(queryEmployee).isNull();
    }
}
