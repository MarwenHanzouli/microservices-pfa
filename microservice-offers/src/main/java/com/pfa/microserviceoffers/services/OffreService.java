package com.pfa.microserviceoffers.services;

import com.pfa.microserviceoffers.models.Offre;
import com.pfa.microserviceoffers.models.enumuration.Niveau;
import com.pfa.microserviceoffers.models.enumuration.TypeOffre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

public interface OffreService  {

    Optional<Offre> findById(Long id);
    Page<Offre> findByOrganismeId(Long organismeId, Pageable pageable);
    Optional<Offre> findByIdAndOrganismeId(Long id, Long organismeId);
    Offre save(Offre offre);
    Offre update(Offre offre);
    void delete(Offre offre);
    boolean existsById(Long id);
    List<Offre> findAll();
    Page<Offre> paginateListOffres(Pageable pageable);
    List<Offre> findByIdManager(Long id);
    List<Offre> findByOrganismeNomOrganismeContainingIgnoreCase(String nomOrganisme);
    List<Offre> findByOrganismeSecteurContainingIgnoreCase(String secteur);
    List<Offre> findAllByTitreContainingIgnoreCase(String titre);
    List<Offre> findAllByPosteContainingIgnoreCase(String poste);
    List<Offre> findAllByLieuContainingIgnoreCase(String lieu);
    List<Offre> findAllByTypeOffre(TypeOffre typeOffre);
    List<Offre> findAllByNiveau(Niveau niveau);
    List<Offre> findAllByDescriptionContainingIgnoreCase(String description);
    List<Offre> findAllByDateOffre(String date) throws DateTimeParseException;
    List<Offre> findAllByDateFin(String date) throws DateTimeParseException;
    List<Offre> findAllByDateOffreBetween(String date1, String date2) throws DateTimeParseException;
    List<Offre> findAllOffresNotEndedBefore(String dateFin) throws DateTimeParseException;
    List<Offre> findAllOffresNotEnded();
    List<Offre> findAllOffresEnded();
    Page<Offre> findAllByTag(String tag,Pageable pageable);
    List<Offre> findAllByTag(String tag);

}
