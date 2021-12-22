package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Entity.JobPostSkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostSkillSetRepository extends JpaRepository<JobPostSkillSet, Integer> {
}
