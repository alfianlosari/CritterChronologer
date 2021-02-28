package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    public Schedule save(Schedule schedule, List<Long> petIds, List<Long> employeeIds) {
        ArrayList<Pet> pets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            petIds.forEach(petId -> {
                Pet pet = petRepository.findById(petId).orElse(null);
                if (pet != null) {
                    pet.getSchedules().add(schedule);
                    pets.add(pet);
                }
            });
        }
        schedule.setPets(pets);

        ArrayList<Employee> employees = new ArrayList<>();
        if (employeeIds != null && !employeeIds.isEmpty()) {
            employeeIds.forEach(employeeId -> {
                Employee employee = employeeRepository.findById(employeeId).orElse(null);
                if (employee != null) {
                    employee.getSchedules().add(schedule);
                    employees.add(employee);
                }
            });
        }
        schedule.setEmployees(employees);

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesForPet(Long petId) {
        return scheduleRepository.schedulesForPet(petId);
    }

    public List<Schedule> getSchedulesForEmployee(Long employeeId) {
        return scheduleRepository.schedulesForEmployee(employeeId);
    }

    public List<Schedule> getSchedulesForCustomers(Long customerId) {
        return scheduleRepository.schedulesForOwner(customerId);
    }

}
