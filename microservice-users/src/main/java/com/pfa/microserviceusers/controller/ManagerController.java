package com.pfa.microserviceusers.controller;

import com.pfa.microserviceusers.exceptions.BadRequestException;
import com.pfa.microserviceusers.exceptions.ResourceNotFoundException;
import com.pfa.microserviceusers.models.Manager;
import com.pfa.microserviceusers.models.User;
import com.pfa.microserviceusers.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @PutMapping("/update/{id}")
    public Manager updateManager(@RequestBody Manager manager, @PathVariable Long id)
    {
        return managerRepository.findById(id).map(m -> {
            m.setLastName(manager.getLastName());
            m.setName(manager.getName());
            m.setTelephone(manager.getTelephone());
            m.setUsername(manager.getUsername());
            m.setLoacked(manager.isLoacked());
            m.setNameEntreprise(manager.getNameEntreprise());
            return managerRepository.save(m);
        }).orElseThrow(()-> new BadRequestException("Ce manager n'existe pas!!!"));
    }

    @GetMapping("/findById/{id}")
    public Manager findById(@Valid @PathVariable Long id)
    {
        return this.managerRepository.findById(id)
                .orElseThrow(()->new BadRequestException("Ce manager : "+id+" n'existe pas"));
    }
}
