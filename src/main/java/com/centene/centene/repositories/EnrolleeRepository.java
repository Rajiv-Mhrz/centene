package com.centene.centene.repositories;

import com.centene.centene.domain.Enrollee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolleeRepository extends JpaRepository<Enrollee, Long> {
}
