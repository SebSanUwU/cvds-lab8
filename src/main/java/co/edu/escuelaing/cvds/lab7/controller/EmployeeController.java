package co.edu.escuelaing.cvds.lab7.controller;


import co.edu.escuelaing.cvds.lab7.model.Employee;
import co.edu.escuelaing.cvds.lab7.service.EmployeeService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @GetMapping("/list")
    public String employees(Model model) {
        model.addAttribute("empleados",employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregarEmpleado(Model model) {
        model.addAttribute("empleado", new Employee());
        return "createEmployee";
    }
    // Procesar el formulario y agregar el empleado
    @PostMapping("/agregar")
    public String agregarEmpleado(Employee empleado) {
        employeeService.addEmployee(empleado);
        return "redirect:/employees/list"; // Redirigir a la lista de empleados
    }

    @GetMapping("/modificar/{employeeId}")
    public String mostrarFormularioModificarEmpleado(@PathVariable Long employeeId, Model model) {
        model.addAttribute("empleado",employeeService.getEmployee(employeeId));
        return "updateEmployee";
    }

    @PostMapping("/modificar/{employeeId}")
    public String ModificarEmpleado(@PathVariable Long employeeId,@ModelAttribute Employee updatedEmployee) {
        System.out.println(updatedEmployee);
        employeeService.updateEmployee(updatedEmployee);
        return "redirect:/employees/list";
    }

    @PostMapping("/eliminar/{employeeId}")
    public String eliminarEmployee(@PathVariable Long employeeId) {
        employeeService.deleteUser(employeeId);
        return "redirect:/employees/list";
    }

    @GetMapping("/analitica")
    public String getCharts(Model model) {
        List<Employee> empleados = employeeService.getAllEmployees();

        Map<String, ArrayList<Employee>> companyData = new TreeMap<>();
        for(Employee empleado:empleados){
            if(!companyData.containsKey(empleado.getCompany())){
                ArrayList<Employee> empleadosList = new ArrayList<>();
                empleadosList.add(empleado);
                companyData.put(empleado.getCompany(),empleadosList);
            }else{
                companyData.get(empleado.getCompany()).add(empleado);
            }
        }

        //Persona por sexo en la base de datos
        int female=0;
        int male=0;

        for(Employee empleado:empleados){
            if (empleado.getSex().equals("Female")){
                female++;
            }else {
                male++;
            }
        }

        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("Male", male);
        graphData.put("Female", female);
        model.addAttribute("chartData", graphData);

        //Número de empleados por empresa
        Map<String, Integer> graphDataCompanyEmployeesCount = new TreeMap<>();
        companyData.forEach((key, value) -> {

            //System.out.println("Clave: " + key + ", Valor: " + value.size());
            Integer theInt= value.size();
            graphDataCompanyEmployeesCount.put(key, theInt);

        });
        model.addAttribute("pieCountData", graphDataCompanyEmployeesCount);

        //Salario promedio por empresa
        Map<String, Float> graphDataCompanySalaryProm = new TreeMap<>();
        companyData.forEach((key, value) -> {
            final float[] aux = {0};
            value.forEach(empleado -> {
                aux[0] +=empleado.getSalary();
            });

            //System.out.println("Clave: " + key + ", Salario Prom: " + aux[0]/ value.size());
            graphDataCompanySalaryProm.put(key,aux[0]/ value.size());
        });
        model.addAttribute("salaryPromData", graphDataCompanySalaryProm);

        //Salario inversion por empresa
        Map<String, Float> graphDataCompanySalary = new TreeMap<>();
        companyData.forEach((key, value) -> {
            final float[] aux = {0};
            value.forEach(empleado -> {
                aux[0] +=empleado.getSalary();
            });

            graphDataCompanySalaryProm.put(key,aux[0]);
        });
        model.addAttribute("salaryData", graphDataCompanySalary);

        return "analitica";
    }


}
