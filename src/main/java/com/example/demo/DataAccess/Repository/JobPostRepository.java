package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Entity.JobPost;
import com.example.demo.DataAccess.Entity.JobPostActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
    @Query("select b from JobPost b where (:position is null  OR b.jobTitle=:position)" +
            "AND (:jobType is null  OR b.jobType.jobTypeName=:jobType )")
    List<JobPost> getFilteredJobPosts(String position, String jobType);

    @Query("select distinct b.jobTitle from JobPost b where b.user.login=?1")
    List<String> getJobPostsNames(String login);

    @Query("select b from JobPost b where b.user.id=:id")
    List<JobPost> getPostByUserId(Integer id);

}
