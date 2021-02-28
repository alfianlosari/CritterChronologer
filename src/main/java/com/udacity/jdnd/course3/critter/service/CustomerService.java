package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Customer save(Customer customer, List<Long> petIds) {
        ArrayList<Pet> pets = new ArrayList<>();

        if (petIds != null && !petIds.isEmpty()) {
            petIds.forEach(petId -> {
                Pet pet = petRepository.findById(petId).orElse(null);
                if (pet != null) {
                    pet.setOwner(customer);
                    pets.add(pet);
                }
            });
        }

        customer.setPets(pets);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
