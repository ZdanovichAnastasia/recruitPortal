package com.example.demo.BusinessLogic;

import com.example.demo.BusinessLogic.Models.IExperienceService;
import com.example.demo.DataAccess.Entity.ExperienceDetail;
import com.example.demo.DataAccess.Repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService implements IExperienceService {
    @Autowired
    ExperienceRepository experienceRepository;

    @Override
    public List<ExperienceDetail> getAllExperienceDetails() {
        return experienceRepository.findAll();
    }

    @Override
    public ExperienceDetail getExperienceDetailsById(int id) {
        return experienceRepository.findById(id).get();
    }

    @Override
    public void saveExperienceDetails(ExperienceDetail experience) {
        experienceRepository.save(experience);
    }

    @Override
    public void deleteExperienceDetails(int id) {
        experienceRepository.delete(experienceRepository.findById(id).get());
    }
}
