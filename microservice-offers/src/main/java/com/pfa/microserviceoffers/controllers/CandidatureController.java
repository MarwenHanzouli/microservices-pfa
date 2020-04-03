package com.pfa.microserviceoffers.controllers;


import com.pfa.microserviceoffers.exceptions.BadRequestException;
import com.pfa.microserviceoffers.exceptions.ResourceExistsException;
import com.pfa.microserviceoffers.models.Candidature;
import com.pfa.microserviceoffers.proxies.CvsProxy;
import com.pfa.microserviceoffers.proxies.UsersProxy;
import com.pfa.microserviceoffers.proxies.beans.CvBean;
import com.pfa.microserviceoffers.proxies.beans.UserBean;
import com.pfa.microserviceoffers.repositories.CandidatureRepository;
import com.pfa.microserviceoffers.responses.ApiResponse;
import com.pfa.microserviceoffers.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidatures")
public class CandidatureController {

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private UsersProxy usersProxy;

    @Autowired
    private OffreService offreService;

    @Autowired
    private CvsProxy cvsProxy;

    @PostMapping("/add/candidat/{idCandidat}/offre/{idOffre}/cv/{idCv}")
    public Candidature create(@Valid @RequestBody Candidature candidature,
                              @PathVariable("idCandidat") Long idCandidat,
                              @PathVariable("idOffre") Long idOffre,
                              @PathVariable("idCv") Long idCv)
    {
        UserBean candidat=this.usersProxy.findCandidatById(idCandidat);
        if(!offreService.existsById(idOffre))
        {
            throw new BadRequestException("Cette offre : "+idOffre+" n'existe pas, tu ne peux pas ajouter cette candidature");
        }
        //Il manque le test avec sur le idCv
        CvBean cvBean=this.cvsProxy.getCv(idCv);
        Candidature candidature1=this.candidatureRepository.findByIdCandidatAndOffreId(idCandidat,idOffre);
        if(candidature1!=null)
        {
            throw new ResourceExistsException("Tu as déjà postulé sur cette offre : "+idOffre);
        }
        candidature.setIdCandidat(idCandidat);
        candidature.setIdCv(idCv);
        candidature.setOffre(this.offreService.findById(idOffre).get());
        return this.candidatureRepository.save(candidature);
    }

    @GetMapping("/getAll")
    public List<Candidature> getAll()
    {
        return this.candidatureRepository.findAll();
    }

    @GetMapping("/getAllByCandidat/{id}")
    public List<Candidature> getAllByCandidat(@PathVariable(value = "id") Long id)
    {
        return this.candidatureRepository.findByIdCandidat(id);
    }

    @GetMapping("/getAllByOffre/{id}")
    public List<Candidature> getAllByOffre(@PathVariable(value = "id") Long id)
    {
        return this.candidatureRepository.findByOffreId(id);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteCandidatureByManager(@PathVariable(value = "id") Long id)
    {
        return this.candidatureRepository.findById(id).map((candidature -> {
            this.candidatureRepository.delete(candidature);
            return new ResponseEntity<Object>(new ApiResponse("La candidature a été supprimée avec succès!!",true), HttpStatus.OK);
        })).orElseThrow(() -> new BadRequestException("Cette candidature : "+id+" n'existe pas!!"));
    }
    @DeleteMapping("delete/{idCandidature}/{idCandidat}")
    public ResponseEntity<?> deleteCandidature(@PathVariable(value = "idCandidature") Long idCandidature,
                                               @PathVariable(value = "idCandidat") Long idCandidat)
    {
        return this.candidatureRepository.findByIdAndIdCandidat(idCandidature,idCandidat).map((candidature -> {
            this.candidatureRepository.delete(candidature);
            return new ResponseEntity<Object>(new ApiResponse("La candidature a été supprimée",true), HttpStatus.OK);
        })).orElseThrow(() -> new BadRequestException("Tu n'as pas cette candidature : "+idCandidature));
    }
}
