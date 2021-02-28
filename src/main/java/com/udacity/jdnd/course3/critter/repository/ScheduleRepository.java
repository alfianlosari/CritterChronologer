package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s " +
            "join s.pets p " +
            "where p.id = :petId"
    )
    List<Schedule> schedulesForPet(Long petId);

    @Query("select s from Schedule s " +
            "join s.employees e " +
            "where e.id = :employeeId"
    )
    List<Schedule> schedulesForEmployee(Long employeeId);

    @Query(
            "select s from Schedule s " +
                    "join s.pets p " +
                    "where p.owner.id = :ownerId"
    )
    List<Schedule> schedulesForOwner(Long ownerId);

}
