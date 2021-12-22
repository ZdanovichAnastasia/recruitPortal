package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Entity.EducationDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<EducationDetail, Integer> {
    @Query("select distinct b.university from EducationDetail  b")
    List<String> getUniversities();

    @Query("select b, count(b.profile) as univSize from EducationDetail  b  group by b.university order by univSize desc ")
    List<String> getTop10Universities(Pageable pageable);
}
