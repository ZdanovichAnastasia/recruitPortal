package com.example.demo.BusinessLogic;

import com.example.demo.BusinessLogic.Models.IEducationService;
import com.example.demo.DataAccess.Entity.EducationDetail;
import com.example.demo.DataAccess.Repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService implements IEducationService {
    @Autowired
    EducationRepository educationRepository;

    @Override
    public List<EducationDetail> getAllEducationDetails() {
        return educationRepository.findAll();
    }

    @Override
    public EducationDetail getEducationDetailsById(int id) {
        return educationRepository.findById(id).get();
    }

    @Override
    public void saveEducationDetails(EducationDetail education) {
        educationRepository.save(education);
    }

    @Override
    public List<String> getNamesUniversity() {
        return educationRepository.getUniversities();
    }

    @Override
    public void deleteEducationDetails(int id) {
        educationRepository.delete(educationRepository.findById(id).get());
    }
}
