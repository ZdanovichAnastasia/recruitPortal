package com.example.demo.BusinessLogic.Models;


import com.example.demo.DataAccess.Entity.JobPost;
import com.example.demo.DataAccess.Entity.JobPostActivity;

import java.util.List;

public interface IJobPostActivitiesService {
    List<JobPostActivity> getResponses(String login);
    JobPostActivity getResponseById(Integer id);

    List<JobPostActivity> getFilterResponses(String login, String jobPost, String activityType);

    void saveActivity(JobPostActivity activity);
    void deleteActivity(int id);
}
