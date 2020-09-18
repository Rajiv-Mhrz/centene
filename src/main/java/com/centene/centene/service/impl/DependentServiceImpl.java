package com.centene.centene.service.impl;


import com.centene.centene.domain.Dependent;
import com.centene.centene.repositories.DependentRepository;
import com.centene.centene.service.DependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DependentServiceImpl implements DependentService {

    @Autowired
    DependentRepository dependentRepository;

    @Override
    public Optional<Dependent> getDependent(Long id) {
        return dependentRepository.findById(id);
    }

    @Override
    public Dependent addDependent(Dependent dependent) {
        return dependentRepository.save(dependent);
    }

    @Override
    public Optional<List<Dependent>> getAllDependents() {
        return Optional.of(dependentRepository.findAll());
    }

    @Override
    public Dependent updateDependent(Dependent dependent) {
        return dependentRepository.save(dependent);
    }

    @Override
    public void deleteDependent(Long id) {
        dependentRepository.deleteById(id);
    }
}
