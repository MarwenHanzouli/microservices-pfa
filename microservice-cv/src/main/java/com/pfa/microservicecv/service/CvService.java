package com.pfa.microservicecv.service;

import com.pfa.microservicecv.models.Cv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CvService {

    Optional<Cv> findById(Long id);
    List<Cv> findAll();
    Page<Cv> findAll(Pageable pageable);

    Page<Cv> findByIdCandidat(Long id, Pageable pageable);
    List<Cv> findByIdCandidat(Long id);

    Page<Cv> findByDateDepot(LocalDateTime dateDepot, Pageable pageable);
    List<Cv> findByDateDepot(LocalDateTime dateDepot);

    Page<Cv> findByEtat(boolean etat, Pageable pageable);
    List<Cv> findByEtat(boolean etat);

    Page<Cv> findByCvParserUsingCustomQuery(String tag, Pageable pageable);
    List<Cv> findByCvParserUsingCustomQuery(String tag);

    Cv create(Cv cv);
    Cv update(Cv cv);
    void delete(Cv cv);
}

