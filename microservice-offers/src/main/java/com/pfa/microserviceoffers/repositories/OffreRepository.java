package com.pfa.microserviceoffers.repositories;


import com.pfa.microserviceoffers.models.Offre;
import com.pfa.microserviceoffers.models.enumuration.Niveau;
import com.pfa.microserviceoffers.models.enumuration.TypeOffre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OffreRepository extends JpaRepository<Offre,Long> {

    Optional<Offre> findById(Long id);
    Page<Offre> findByOrganismeId(Long organismeId, Pageable pageable);
    Optional<Offre> findByIdAndOrganismeId(Long id, Long organismeId);
    List<Offre> findByIdManager(Long id);
    List<Offre> findByOrganismeNomOrganismeContainingIgnoreCase(String nomOrganisme);
    List<Offre> findByOrganismeSecteurContainingIgnoreCase(String secteur);
    List<Offre> findAllByTitreContainingIgnoreCase(String titre);
    List<Offre> findAllByPosteContainingIgnoreCase(String poste);
    List<Offre> findAllByLieuContainingIgnoreCase(String lieu);
    List<Offre> findAllByTypeOffre(TypeOffre typeOffre);
    List<Offre> findAllByNiveauDemande(Niveau niveau);
    List<Offre> findAllByDescriptionContainingIgnoreCase(String description);
    List<Offre> findAllByDateOffre(LocalDateTime date);
    List<Offre> findAllByDateFin(LocalDateTime date);
    List<Offre> findAllByDateFinBefore(LocalDateTime date);
    List<Offre> findAllByDateFinAfter(LocalDateTime date);
    List<Offre> findAllByDateOffreBetween(LocalDateTime date1, LocalDateTime date2);
    Page<Offre> findAllByTitreOrDescriptionOrPosteOrLieuContainingIgnoreCase(String titre, String description, String poste, String lieu,Pageable pageable);
    List<Offre> findAllByTitreOrDescriptionOrPosteOrLieuContainingIgnoreCase(String titre, String description, String poste, String lieu);
}
