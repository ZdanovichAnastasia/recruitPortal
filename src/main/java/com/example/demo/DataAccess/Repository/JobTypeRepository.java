package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Entity.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobTypeRepository extends JpaRepository<JobType, Integer> {
    @Query("select b.jobTypeName from JobType  b")
    List<String> getJobTypes();
}
