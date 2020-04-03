package com.pfa.microservicecv.service;

import com.pfa.microservicecv.models.Cv;
import com.pfa.microservicecv.repository.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CvServiceImpl implements CvService{

    @Autowired
    private CvRepository cvRepository;


    @Override
    public Optional<Cv> findById(Long id) {
        return cvRepository.findById(id);
    }

    @Override
    public List<Cv> findAll() {
        List<Cv> l= new ArrayList<Cv>();
        this.cvRepository.findAll().forEach(cv -> {
            l.add(cv);
        });
        return l;
    }

    @Override
    public Page<Cv> findAll(Pageable pageable) {
        return this.cvRepository.findAll(pageable);
    }

    @Override
    public Page<Cv> findByIdCandidat(Long id, Pageable pageable) {
        return this.cvRepository.findByIdCandidat(id,pageable);
    }

    @Override
    public List<Cv> findByIdCandidat(Long id) {
        return this.cvRepository.findByIdCandidat(id);
    }

    @Override
    public Page<Cv> findByDateDepot(LocalDateTime dateDepot, Pageable pageable) {
        return this.cvRepository.findByDateDepot(dateDepot,pageable);
    }

    @Override
    public List<Cv> findByDateDepot(LocalDateTime dateDepot) {
        return this.cvRepository.findByDateDepot(dateDepot);
    }

    @Override
    public Page<Cv> findByEtat(boolean etat, Pageable pageable) {
        return this.cvRepository.findByEtat(etat,pageable);
    }

    @Override
    public List<Cv> findByEtat(boolean etat) {
        return this.cvRepository.findByEtat(etat);
    }

    @Override
    public Page<Cv> findByCvParserUsingCustomQuery(String tag, Pageable pageable) {
        return this.cvRepository.findByCvParserUsingCustomQuery(tag,pageable);
    }

    @Override
    public List<Cv> findByCvParserUsingCustomQuery(String tag) {
        return this.cvRepository.findByCvParserUsingCustomQuery(tag);
    }

    @Override
    public Cv create(Cv cv) {
        return this.cvRepository.save(cv);
    }

    @Override
    public Cv update(Cv cv) {
        return this.cvRepository.save(cv);
    }

    @Override
    public void delete(Cv cv) {
        this.cvRepository.delete(cv);
    }
}
