package com.pfa.microserviceoffers.controllers;

import com.pfa.microserviceoffers.exceptions.ResourceExistsException;
import com.pfa.microserviceoffers.models.Competence;
import com.pfa.microserviceoffers.repositories.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/competences")
public class CompetenceController {

    @Autowired
    private CompetenceRepository competenceRepository;

    @PostMapping("add")
    public Competence create(@Valid @RequestBody Competence competence)
    {
        Competence c=this.competenceRepository.findByTitreIgnoreCase(competence.getTitre());
        if(c==null)
        {
            return this.competenceRepository.save(competence);
        }
        else
        {
            throw new ResourceExistsException("Tu ne peux pas ajouter cette compétence, elle existe déjà");
        }
    }
    @GetMapping("/getAll")
    public List<Competence> getAll()
    {
        return this.competenceRepository.findAll();
    }
}
