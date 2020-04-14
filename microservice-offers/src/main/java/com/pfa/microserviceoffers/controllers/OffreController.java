package com.pfa.microserviceoffers.controllers;

import com.pfa.microserviceoffers.exceptions.BadRequestException;
import com.pfa.microserviceoffers.exceptions.ResourceNotFoundException;
import com.pfa.microserviceoffers.models.Competence;
import com.pfa.microserviceoffers.models.Offre;
import com.pfa.microserviceoffers.models.enumuration.Niveau;
import com.pfa.microserviceoffers.models.enumuration.TypeOffre;
import com.pfa.microserviceoffers.proxies.UsersProxy;
import com.pfa.microserviceoffers.proxies.beans.UserBean;
import com.pfa.microserviceoffers.repositories.CompetenceRepository;
import com.pfa.microserviceoffers.repositories.OrganismeRepository;
import com.pfa.microserviceoffers.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/offres")
public class OffreController {

    @Autowired
    private OffreService offreService;

    @Autowired
    private OrganismeRepository organismeRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private UsersProxy usersProxy;

    @PostMapping("/add/{idManager}")
    public Offre create(@PathVariable(value = "idManager") Long idManager,
                                    @Valid @RequestBody Offre offre)
    {
        UserBean userBean=this.usersProxy.findManagerById(idManager);
        if(userBean==null)
        {
            throw new ResourceNotFoundException("Le manager avec id :"+idManager+" n'existe pas, tu ne peux pas ajouter cette offre!!");
        }
        offre.setIdManager(idManager);
        return organismeRepository.findByNomOrganismeIgnoreCase(offre.getOrganisme().getNomOrganisme()).map(organisme->{
            //System.out.println(organisme);
            HashSet<Competence> competences=new HashSet<>();
            for (Competence c: offre.getCompetences()) {
                Competence competence=this.competenceRepository.findByTitreIgnoreCase(c.getTitre());
                if(competence==null){
                    competences.add(this.competenceRepository.save(c));
                }
                else{
                    competences.add(competence);
                }
            }
            offre.setEtat(true);
            offre.setCompetences(competences);
            offre.setOrganisme(organisme);
            return this.offreService.save(offre);
        }).orElseThrow(()-> new ResourceNotFoundException("Organisme : "+offre.getOrganisme().getNomOrganisme()+" n'existe pas, tu ne peux pas ajouter cette offre!!"));
    }
    @GetMapping("/getOffreById/{idOffre}")
    public Offre getOffreByID(@PathVariable(value = "idOffre") Long id)
    {
        Offre o=this.offreService.findById(id).get();
        if(o==null)
        {
            throw new ResourceNotFoundException("Cette offre n'existe pas!");
        }
        return o;
    }
    @GetMapping("/getAll")
    public List<Offre> getAllOffres()
    {
        return this.offreService.findAll();
    }
    @GetMapping("/getAllPaginate")
    public Page<Offre> getAllOffresPaginated(Pageable pageable)
    {
        return this.offreService.paginateListOffres(pageable);
    }
    @GetMapping("/getAll/{idManager}")
    public List<Offre> getAllOffresByManager(@PathVariable(value = "idManager") Long id)
    {
        UserBean userBean=this.usersProxy.findManagerById(id);
        if(userBean==null)
        {
            throw new ResourceNotFoundException("L'utilisateur avec id :"+id+" n'existe pas, tu ne peux pas ajouter cette offre!!");
        }
        return this.offreService.findByIdManager(id);
    }

    @GetMapping("/getAllByTag/{tag}")
    public List<Offre> getAllByTag(@PathVariable(value = "tag") String tag)
    {
        return this.offreService.findAllByTag(tag);
    }
    @GetMapping("/getAllByTagPaginate/{tag}")
    public Page<Offre> getAllByTagPaginate(@PathVariable(value = "tag") String tag, Pageable pageable)
    {
        return this.offreService.findAllByTag(tag,pageable);
    }


    @PutMapping("/update/{idOffre}")
    public Offre updateOffre(@PathVariable(value = "idOffre") Long idOffre,@Valid @RequestBody Offre offre)
    {
        return this.offreService.findById(idOffre).map(o->{
            o.setAnneeExperience(offre.getAnneeExperience());
            o.setDateFin(offre.getDateFin());
            o.setDateOffre(offre.getDateOffre());
            o.setDescription(offre.getDescription());
            o.setEtat(offre.isEtat());
            o.setLieu(offre.getLieu());
            o.setPoste(offre.getPoste());
            o.setNiveauDemande(offre.getNiveauDemande());
            o.setTitre(offre.getTitre());
            return offreService.update(o);
        }).orElseThrow(()->new ResourceNotFoundException("Cette offre "+idOffre+" n'existe pas !"));
    }

    @DeleteMapping("/delete/{idOffre}")
    public ResponseEntity<?> deleteOffre(@PathVariable(value = "idOffre") Long id)
    {
        return this.offreService.findById(id).map(offre->{
            this.offreService.delete(offre);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Cette offre "+id+" n'existe pas !"));
    }

    @GetMapping("/getAllByOrganisme/{nomOrganisme}")
    List<Offre> getByOrganismeNomOrganismeContainingIgnoreCase(@PathVariable(value = "nomOrganisme") String nomOrganisme)
    {
        return this.offreService.findByOrganismeNomOrganismeContainingIgnoreCase(nomOrganisme);
    }

    @GetMapping("/getAllBySecteurOrganisme/{secteur}")
    List<Offre> getByOrganismeSecteurContainingIgnoreCase(@PathVariable(value = "secteur") String secteur)
    {
        return this.offreService.findByOrganismeSecteurContainingIgnoreCase(secteur);
    }
    @GetMapping("/getAllByTitre/{titre}")
    List<Offre> getAllByTitre(@PathVariable(value = "titre")String titre){
        return this.offreService.findAllByTitreContainingIgnoreCase(titre);
    }
    @GetMapping("/getAllByPoste/{poste}")
    List<Offre> findAllByPoste(@PathVariable(value = "poste")String poste){
        return this.offreService.findAllByPosteContainingIgnoreCase(poste);
    }
    @GetMapping("/getAllByLieu/{lieu}")
    List<Offre> getAllByLieu(@PathVariable(value = "lieu")String lieu){
        return this.offreService.findAllByLieuContainingIgnoreCase(lieu);
    }
    @GetMapping("/getAllByTypeOffre/{type}")
    List<Offre> getAllByTypeOffre(@PathVariable(value = "type") TypeOffre type){
        return this.offreService.findAllByTypeOffre(type);
    }
    @GetMapping("/getAllByNiveau/{niveau}")
    List<Offre> getAllByNiveau(@PathVariable(value = "niveau") Niveau niveau){
        return this.offreService.findAllByNiveau(niveau);
    }
    @GetMapping("/getAllByDescription/{description}")
    List<Offre> getAllByDescription(@PathVariable(value = "description") String description){
        return this.offreService.findAllByDescriptionContainingIgnoreCase(description);
    }
    @GetMapping("/getAllByDateOffre/{date}")
    List<Offre> getAllByDateOffre(@PathVariable(value = "date") String date){
        try{
            return this.offreService.findAllByDateOffre(date);
        }catch (DateTimeParseException de)
        {
            throw new BadRequestException("Requête rejeté, verifie la date envoyé, (yyyy-mm-dd HH:mm:ss)");
        }
    }

    @GetMapping("/getAllByDateFin/{date}")
    List<Offre> getAllByDateFin(@PathVariable(value = "date") String date){
        try{
            return this.offreService.findAllByDateFin(date);
        }catch (DateTimeParseException de)
        {
            throw new BadRequestException("Requête rejeté, verifie la date envoyé, (yyyy-mm-dd HH:mm:ss)");
        }
    }

    @GetMapping("/getAllByDateOffreBetween/{date1}/{date2}")
    List<Offre> getAllByDateOffreBetween(@PathVariable(value = "date1") String date1,
                                         @PathVariable(value = "date2") String date2){
        try{
            return this.offreService.findAllByDateOffreBetween(date1,date2);
        }catch (DateTimeParseException de)
        {
            throw new BadRequestException("Requête rejeté, verifie la date envoyé, (yyyy-mm-dd HH:mm:ss)");
        }
    }
    @GetMapping("/ /{date}")
    List<Offre> getAllOffresNotEndedBefore(@PathVariable(value = "date") String dateFin){
        try{
            return this.offreService.findAllOffresNotEndedBefore(dateFin);
        }catch (DateTimeParseException de)
        {
            throw new BadRequestException("Requête rejeté, verifie la date envoyé, (yyyy-mm-dd HH:mm:ss)");
        }
    }
    @GetMapping("/getAllNotEnded")
    List<Offre> getAllOffresNotEnded(){
        try{
            return this.offreService.findAllOffresNotEnded();
        }catch (DateTimeParseException de)
        {
            throw new BadRequestException("Requête rejeté, verifie la date envoyé, (yyyy-mm-dd HH:mm:ss)");
        }
    }
    @GetMapping("/getAllEnded")
    public List<Offre> getAllOffresEnded() {
        return this.offreService.findAllOffresEnded();
    }
    @GetMapping("/getAllTypes")
    public List<String> getAllTypes()
    {
        List<String> l=new ArrayList<>();
        for(TypeOffre typeOffre:TypeOffre.values())
        {
            l.add(typeOffre.getType());
        }
        return l;
    }
    @GetMapping("/getAllNiveau")
    public List<String> getAllNiveau()
    {
        List<String> l=new ArrayList<>();
        for(Niveau niveau:Niveau.values())
        {
            l.add(niveau.getNiveau());
        }
        return l;
    }
}
