package com.tool.RecruitXpert.Repository;

import com.tool.RecruitXpert.Entities.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter,Long> {
}
