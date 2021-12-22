package com.example.demo.BusinessLogic;


import com.example.demo.BusinessLogic.Models.IJobTypeService;
import com.example.demo.DataAccess.Entity.JobType;
import com.example.demo.DataAccess.Repository.JobTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTypeService implements IJobTypeService {
    @Autowired
    JobTypeRepository jobTypeRepository;

    @Override
    public List<JobType> getAllJobTypes() {
        return jobTypeRepository.findAll();
    }

    @Override
    public JobType getJobTypeById(int id) {
        return jobTypeRepository.findById(id).get();
    }

    @Override
    public void saveJobType(JobType jobTypeDTO) {
        jobTypeRepository.save(jobTypeDTO);
    }

    @Override
    public List<String> getNamesJobTypes() {
        return jobTypeRepository.getJobTypes();
    }
}
