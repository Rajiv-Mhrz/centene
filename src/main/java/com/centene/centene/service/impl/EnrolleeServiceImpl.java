package com.centene.centene.service.impl;

import com.centene.centene.domain.Enrollee;
import com.centene.centene.repositories.EnrolleeRepository;
import com.centene.centene.service.EnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrolleeServiceImpl implements EnrolleeService {

    @Autowired
    EnrolleeRepository enrolleeRepository;

    @Override
    public Optional<Enrollee> getEnrollee(Long id) {
        return enrolleeRepository.findById(id);
    }

    @Override
    public Enrollee addEnrollee(Enrollee enrollee) {
        return enrolleeRepository.save(enrollee);
    }

    @Override
    public Optional<List<Enrollee>> getAllEnrollee() {
        return Optional.of(enrolleeRepository.findAll());
    }

    @Override
    public Enrollee addDependent(Enrollee enrollee) {
        return enrolleeRepository.save(enrollee);
    }

    @Override
    public Enrollee updateEnrollee(Enrollee enrollee) {
        return enrolleeRepository.save(enrollee);
    }

    @Override
    public void deleteEnrollee(Long id) {

        enrolleeRepository.deleteById(id);

    }
}
