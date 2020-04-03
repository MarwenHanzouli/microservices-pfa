package com.pfa.microserviceoffers.repositories;

import com.pfa.microserviceoffers.models.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {

    Competence findByTitreIgnoreCase(String titre);
}
