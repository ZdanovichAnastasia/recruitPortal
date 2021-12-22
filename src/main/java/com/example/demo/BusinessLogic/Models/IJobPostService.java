package com.example.demo.BusinessLogic.Models;

import com.example.demo.DataAccess.Entity.JobPost;
import com.example.demo.DataAccess.Entity.JobPostSkillSet;

import java.util.List;

public interface IJobPostService {
    List<JobPost> getAllJobPosts();
    JobPost getJobPostById(int id);
    List<String> getNamesJobPosts(String login);

    List<JobPost> getFilterJobPosts(String position, String jobType);
    List<JobPost> getSortedByDateJobPosts();
    List<JobPost> getSortedByResponsesJobPosts();

    JobPostSkillSet getJobPostSkillSetById(int id);
    void saveJobPostSkills(JobPostSkillSet jobPostSkillSet);
    void deleteJobPostSkill(int id);

    void saveJobPost(JobPost jobPost);
    void deleteJobPost(int id);
}
