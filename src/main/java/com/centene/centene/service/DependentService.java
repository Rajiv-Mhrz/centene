package com.centene.centene.service;


import com.centene.centene.domain.Dependent;

import java.util.List;
import java.util.Optional;

public interface DependentService {
    Optional<Dependent> getDependent(Long id);

    Dependent addDependent(Dependent dependent);

    Optional<List<Dependent>> getAllDependents();

    Dependent updateDependent(Dependent dependent);

    void deleteDependent(Long id);
}
