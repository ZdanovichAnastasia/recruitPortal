package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Entity.ExperienceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceDetail, Integer> {
    @Query("select distinct b.companyName from ExperienceDetail  b")
    List<String> getCompanies();
}
