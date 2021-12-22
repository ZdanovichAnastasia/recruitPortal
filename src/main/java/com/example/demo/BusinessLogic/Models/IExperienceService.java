package com.example.demo.BusinessLogic.Models;

import com.example.demo.DataAccess.Entity.ExperienceDetail;

import java.util.List;

public interface IExperienceService {
    List<ExperienceDetail> getAllExperienceDetails();
    void saveExperienceDetails(ExperienceDetail education);
    void deleteExperienceDetails(int id);
    ExperienceDetail getExperienceDetailsById(int id);
}
