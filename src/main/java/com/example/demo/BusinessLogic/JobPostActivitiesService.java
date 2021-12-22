package com.example.demo.BusinessLogic;

import com.example.demo.BusinessLogic.Models.IJobPostActivitiesService;
import com.example.demo.DataAccess.Entity.JobPostActivity;
import com.example.demo.DataAccess.Repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostActivitiesService implements IJobPostActivitiesService {
    @Autowired
    JobPostActivityRepository jobPostActivityRepository;

    @Override
    public List<JobPostActivity> getResponses(String login) {
        return jobPostActivityRepository.getResponses(login);
    }

    @Override
    public JobPostActivity getResponseById(Integer id) {
        return jobPostActivityRepository.findById(id).get();
    }

    @Override
    public void saveActivity(JobPostActivity activity) {
        jobPostActivityRepository.save(activity);
    }

    @Override
    public void deleteActivity(int id) {
        jobPostActivityRepository.delete(jobPostActivityRepository.findById(id).get());
    }

    @Override
    public List<JobPostActivity> getFilterResponses(String login, String position, String activityType) {
        String pos = "", type = "";
        if(position!=null) {
            pos = position.equals("") ? null : position;
        }
        else pos=null;
        if(activityType!=null) {
            type = activityType.equals("") ? null : activityType;
        }
        else type=null;
        return jobPostActivityRepository.getFilteredResponses(login, pos, type);
    }
}
