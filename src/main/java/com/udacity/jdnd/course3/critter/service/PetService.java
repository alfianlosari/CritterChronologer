package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    public PetRepository petRepository;

    @Autowired
    public CustomerRepository customerRepository;

    public Pet save(Pet pet, Long ownerId) {
        Customer customer = customerRepository.getOne(ownerId);
        pet.setOwner(customer);

        pet = petRepository.save(pet);
        customer.getPets().add(pet);
        return pet;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.petsFromOwnerId(ownerId);
    }

    public Pet find(Long id) {
        return petRepository.findById(id).orElse(null);
    }

}
