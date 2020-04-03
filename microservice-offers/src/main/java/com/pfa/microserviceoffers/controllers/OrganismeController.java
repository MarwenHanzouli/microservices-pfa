package com.pfa.microserviceoffers.controllers;

import com.pfa.microserviceoffers.exceptions.ResourceExistsException;
import com.pfa.microserviceoffers.models.Organisme;
import com.pfa.microserviceoffers.repositories.OrganismeRepository;
import com.pfa.microserviceoffers.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/organismes")
public class OrganismeController {

    @Autowired
    private OrganismeRepository organismeRepository;

    @PostMapping("/add")
    public Organisme add(@Valid @RequestBody Organisme organisme)
    {
        Optional<Organisme> o = this.organismeRepository.findByNomOrganismeIgnoreCase(organisme.getNomOrganisme());
        if(!o.isPresent())
        {
            return this.organismeRepository.save(organisme);
        }
        else{
            throw new ResourceExistsException("Cet organisme existe déjà, tu ne peux pas le rajouter!");
        }
    }
    @GetMapping("getAll")
    List<Organisme> getAll()
    {
        return this.organismeRepository.findAll();
    }

}
