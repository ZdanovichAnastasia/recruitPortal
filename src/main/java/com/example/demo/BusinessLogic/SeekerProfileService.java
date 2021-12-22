package com.example.demo.BusinessLogic;

import com.example.demo.BusinessLogic.Models.ISeekerProfileService;
import com.example.demo.BusinessLogic.Models.Resume;
import com.example.demo.DataAccess.Entity.SeekerProfile;
import com.example.demo.DataAccess.Entity.SeekerSkillSet;
import com.example.demo.DataAccess.Repository.SeekerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeekerProfileService implements ISeekerProfileService {
    @Autowired
    SeekerProfileRepository seekerProfileRepository;

    @Override
    public List<SeekerProfile> getAllSeekerProfiles() {
        return seekerProfileRepository.findAll();
    }

    @Override
    public SeekerProfile getSeekerProfileById(int id) {
         return seekerProfileRepository.findById(id).get();
    }

    @Override
    public void saveSeekerProfile(SeekerProfile seekerProfile) {
        seekerProfileRepository.save(seekerProfile);
    }

    @Override
    public void deleteSeekerProfile(int id) {
        seekerProfileRepository.delete(getSeekerProfileById(id));
    }

    @Override
    public void downloadSeekerProfile(int id) throws Exception {
        SeekerProfile profile = seekerProfileRepository.findById(id).get();
        List<String> skills = new ArrayList<>();
        int count = 0;
        for (SeekerSkillSet skill : profile.getSeekerSkills()) {
            if (count > 5) {
                break;
            }
            skills.add(skill.getSeekerSkillSet().getSkillSetName());
            count++;
        }

        Resume resume = new Resume(
                profile.getFirstName(),
                profile.getLastName(),
                profile.getPhone(),
                profile.getEmail(),
                skills);
        ByteArrayOutputStream stream = DocumentService.generate(resume);
        stream.writeTo(new FileOutputStream(profile.getLastName() + "Resume" + ".docx"));
    }
}
