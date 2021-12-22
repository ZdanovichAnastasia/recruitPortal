package com.example.demo.DataAccess.Repository;


import com.example.demo.DataAccess.Entity.SeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeekerProfileRepository extends JpaRepository<SeekerProfile,Integer> {
    //@Query("select b from SeekerProfile b INNER JOIN EducationDetail c ON b.id = c.profile.id  where  (:university is null  OR c.university=:university )")
    //List<SeekerProfile> getFilteredProfiles(String university, String jobType, String profileType);
}
