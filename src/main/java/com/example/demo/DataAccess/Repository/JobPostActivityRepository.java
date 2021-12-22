package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Entity.JobPost;
import com.example.demo.DataAccess.Entity.JobPostActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostActivityRepository extends JpaRepository<JobPostActivity,Integer> {
    @Query("select u from JobPostActivity u where u.post.user.login=?1")
    List<JobPostActivity> getResponses(String login);

    @Query("select u from JobPostActivity u where (:position is null  OR u.post.jobTitle=:position)" +
            "AND (:activityType is null  OR u.type=:activityType ) AND u.post.user.login=:login")
    List<JobPostActivity> getFilteredResponses(String login, String position, String activityType);

}
