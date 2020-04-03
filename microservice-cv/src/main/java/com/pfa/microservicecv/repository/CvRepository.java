package com.pfa.microservicecv.repository;

import com.pfa.microservicecv.models.Cv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CvRepository  extends ElasticsearchRepository<Cv, Long> {

    Page<Cv> findByIdCandidat(Long id, Pageable pageable);
    List<Cv> findByIdCandidat(Long id);

    Page<Cv> findByDateDepot(LocalDateTime dateDepot, Pageable pageable);
    List<Cv> findByDateDepot(LocalDateTime dateDepot);

    Page<Cv> findByEtat(boolean etat, Pageable pageable);
    List<Cv> findByEtat(boolean etat);

    @Query("{\"bool\": {\"should\": [{\"match\": {\"cvParser\": \"?0\"}}]}}")
    Page<Cv> findByCvParserUsingCustomQuery(String tag,Pageable pageable);

    @Query("{\"bool\": {\"should\": [{\"match\": {\"cvParser\": \"?0\"}}]}}")
    List<Cv> findByCvParserUsingCustomQuery(String tag);
}
