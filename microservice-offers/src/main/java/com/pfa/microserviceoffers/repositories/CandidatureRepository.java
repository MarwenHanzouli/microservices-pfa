package com.pfa.microserviceoffers.repositories;

import com.pfa.microserviceoffers.models.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {

    Candidature findByIdCandidatAndOffreId(Long idCandidat, Long idOffre);
    Optional<Candidature> findByIdAndIdCandidat(Long id, Long idCandidat);
    List<Candidature> findByIdCandidat(Long idCandidat);
    List<Candidature> findByOffreId(Long idOffre);
    List<Candidature> findByIdCv(Long idCandidat);
    List<Candidature> findByEtat(boolean etat);
    List<Candidature> findByDateCandidature(LocalDate date);
}
