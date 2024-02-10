package com.tool.RecruitXpert.Repository;

import com.tool.RecruitXpert.Entities.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {

    boolean existsByEmail(String email);

    Recruiter findByEmail(String email);
//    Optional<Recruiter> findByEmail(String email);

    boolean existsByOrganisation(String organisation);

//    List<Recruiter> findByStatus(String status);
}

