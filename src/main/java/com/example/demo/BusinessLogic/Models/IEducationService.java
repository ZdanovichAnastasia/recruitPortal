package com.example.demo.BusinessLogic.Models;


import com.example.demo.DataAccess.Entity.EducationDetail;
import java.util.List;

public interface IEducationService {
    List<EducationDetail> getAllEducationDetails();
    void saveEducationDetails(EducationDetail education);
    List<String> getNamesUniversity();
    void deleteEducationDetails(int id);
    EducationDetail getEducationDetailsById(int id);
}
