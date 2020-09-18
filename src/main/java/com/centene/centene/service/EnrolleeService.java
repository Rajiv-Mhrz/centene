package com.centene.centene.service;

import com.centene.centene.domain.Enrollee;

import java.util.List;
import java.util.Optional;

public interface EnrolleeService {
    Optional<Enrollee> getEnrollee(Long id);

    Enrollee addEnrollee(Enrollee enrollee);

    Optional<List<Enrollee>> getAllEnrollee();

    Enrollee addDependent(Enrollee enrollee);

    Enrollee updateEnrollee(Enrollee enrollee);

    void deleteEnrollee(Long id);

}
