package com.example.demo.BusinessLogic.Models;

import com.example.demo.DataAccess.Entity.JobType;

import java.util.List;

public interface IJobTypeService {
    List<JobType> getAllJobTypes();
    JobType getJobTypeById(int id);
    void saveJobType(JobType jobTypeDTO);
    List<String> getNamesJobTypes();
}
