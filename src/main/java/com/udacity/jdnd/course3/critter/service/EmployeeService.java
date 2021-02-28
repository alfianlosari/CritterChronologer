package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee, Set<EmployeeSkill> employeeSkills) { ;
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee find(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getAvailableEmployees(LocalDate date, Set<EmployeeSkill> employeeSkills) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        List<Employee> employees = employeeRepository.employeesAvailable(dayOfWeek);
        return employees.stream()
                .filter(employee -> employee.getSkills().containsAll(employeeSkills))
                .collect(Collectors.toList());
    }

    public void setEmployeeAvailableDays(Long employeeId, Set<DayOfWeek> daysAvailable) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

}
