package com.example.demo.BusinessLogic.Models;


import com.example.demo.DataAccess.Entity.SeekerProfile;

import java.util.List;

public interface ISeekerProfileService {
    List<SeekerProfile> getAllSeekerProfiles();
    SeekerProfile getSeekerProfileById(int id);
    //List<SeekerProfile> getFilteredSeekerProfiles(String position, String university, String jobType);

    void saveSeekerProfile(SeekerProfile seekerProfileDTO);
    void downloadSeekerProfile(int id) throws Exception;
    void deleteSeekerProfile(int id);
}
