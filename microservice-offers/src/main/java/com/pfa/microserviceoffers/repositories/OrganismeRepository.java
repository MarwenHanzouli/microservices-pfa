package com.pfa.microserviceoffers.repositories;

import com.pfa.microserviceoffers.models.Organisme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganismeRepository extends JpaRepository<Organisme, Long> {
    Optional<Organisme> findById(Long id);
    Page<Organisme> findAll(Pageable pageable);
    Optional<Organisme> findByNomOrganismeIgnoreCase(String nomOrganisme);
    List<Organisme> findBySecteurIgnoreCase(String secteur);
    List<Organisme> findByNomOrganismeContainingIgnoreCase(String nomOrganisme);
    List<Organisme> findBySecteurContainingIgnoreCase(String secteur);
}
