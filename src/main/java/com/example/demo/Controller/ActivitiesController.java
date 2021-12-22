package com.example.demo.Controller;

import com.example.demo.BusinessLogic.*;
import com.example.demo.DataAccess.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class ActivitiesController {
    @Autowired
    JobPostActivitiesService jobPostActivitiesService;
    @Autowired
    JobPostService jobPostService;
    @Autowired
    SeekerProfileService seekerProfileService;
    @Autowired
    JobTypeService jobTypeService;
    @Autowired
    EducationService educationService;
    @Autowired
    ExperienceService experienceService;

    ///////////////SHOW////////////////////

    @GetMapping({"/recruiter/showResponses"})
    public String showRecruiterResponses(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user){
        model.addAttribute("jobPostList", jobPostService.getNamesJobPosts(user.getUsername()));
        model.addAttribute("responsesList", jobPostActivitiesService.getResponses(user.getUsername()));
        return "recruiter/showResponses";
    }

    @GetMapping({"recruiter/showSeekerProfile"})
    public String showSeekerProfile(Model model, @RequestParam String idSeekerProfile) {
        model.addAttribute("seekerProfileInfo", seekerProfileService.getSeekerProfileById(Integer.parseInt(idSeekerProfile)));
        return "recruiter/showSeekerProfile";
    }

    ////////////////ADD////////////////////
    @GetMapping("response/{id}/add")
    public String showAddForm(@ModelAttribute("profile") SeekerProfile profile, Model model, @PathVariable("id") int idJobPost) {
        model.addAttribute("idJobPost", idJobPost);
        return "addResponse";
    }

    @PostMapping("response/{id}")
    public String addProfile(@ModelAttribute("profile")@Valid SeekerProfile profile, BindingResult bindingResult,
                             @PathVariable("id") int idJobPost, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("idJobPost", idJobPost);
            return "addResponse";
        }
        List<JobPostActivity> jobPostActivityList = new ArrayList<>();

        JobPostActivity jobPostActivity = new JobPostActivity();
        jobPostActivity.setProfile(profile);
        jobPostActivity.setPost(jobPostService.getJobPostById(idJobPost));
        jobPostActivity.setType("отклик");
        jobPostActivity.setApplyDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        jobPostActivityList.add(jobPostActivity);

        profile.setActivities(jobPostActivityList);

        seekerProfileService.saveSeekerProfile(profile);

        SeekerProfile seekerProfile = seekerProfileService.getAllSeekerProfiles().get(seekerProfileService.getAllSeekerProfiles().size()-1);
        model.addAttribute("seekerProfileInfo", seekerProfile);
        return "addEducations";
    }

    @PostMapping("addEducation/{id}")
    public String addEducation(@ModelAttribute("profile") @Valid SeekerProfile profile, BindingResult bindingResult,
                               @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("seekerProfileInfo", seekerProfileService.getSeekerProfileById(id));
            return "addEducations";
        }
        EducationDetail educationDetail = profile.getEducations().get(0);
        educationDetail.setProfile(seekerProfileService.getSeekerProfileById(id));

        educationService.saveEducationDetails(educationDetail);

        model.addAttribute("seekerProfileInfo", seekerProfileService.getSeekerProfileById(id));
        return "addEducations";
    }

    @GetMapping({"/addExperiences"})
    public String showAddExpForm(@ModelAttribute("profile") @Valid SeekerProfile profile, Model model, @RequestParam String idSeekerProfile){
        model.addAttribute("seekerProfileInfo", seekerProfileService.getSeekerProfileById(Integer.parseInt(idSeekerProfile)));
        return "addExperiences";
    }

    @PostMapping("addExperience/{id}")
    public String addExperience(@ModelAttribute("profile") @Valid SeekerProfile profile, BindingResult bindingResult,
                                @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("seekerProfileInfo", seekerProfileService.getSeekerProfileById(id));
            return "addExperiences";
        }
        ExperienceDetail experienceDetail = profile.getExperiences().get(0);
        experienceDetail.setProfile(seekerProfileService.getSeekerProfileById(id));

        experienceService.saveExperienceDetails(experienceDetail);

        model.addAttribute("seekerProfileInfo", seekerProfileService.getSeekerProfileById(id));
        return "addExperiences";
    }


    ////////////////EDIT///////////////////

    @GetMapping("interview/{id}")
    public String showEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("activity", jobPostActivitiesService.getResponseById(id));
        return "recruiter/editSeekerProfile";
    }

    @PostMapping("profile/{id}")
    public String editProfile(@ModelAttribute("activity") @Valid JobPostActivity activity, BindingResult bindingResult,
                         @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activity", jobPostActivitiesService.getResponseById(id));
            return "recruiter/editSeekerProfile";
        }

       for(EducationDetail education : activity.getProfile().getEducations()){
           education.setProfile(activity.getProfile());
       }
        for(ExperienceDetail experience : activity.getProfile().getExperiences()){
            experience.setProfile(activity.getProfile());
        }
        int i = 0;
        for(SeekerSkillSet skills : activity.getProfile().getSeekerSkills()){
            skills.setProfile(activity.getProfile());
            skills.setSeekerSkillSet(activity.getPost().getSkills().get(i).getSkillSet());
            i++;
        }

        activity.getProfile().setId(id);
        seekerProfileService.saveSeekerProfile(activity.getProfile());

        JobPostActivity jobPostActivity = jobPostActivitiesService.getResponseById(activity.getId());
        jobPostActivity.setType("interview");
        jobPostActivitiesService.saveActivity(jobPostActivity);

        model.addAttribute("seekerProfileInfo", seekerProfileService.getSeekerProfileById(id));
        return "recruiter/showSeekerProfile";
    }

    //////////////DELETE////////////////////

    @PostMapping("deleteResponse")
    public String deleteResponse(Model model, @RequestParam String id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user){
        seekerProfileService.deleteSeekerProfile(jobPostActivitiesService.getResponseById(Integer.parseInt(id)).getProfile().getId());
        model.addAttribute("responsesList", jobPostActivitiesService.getResponses(user.getUsername()));
        return "recruiter/showResponses";
    }

    @PostMapping("deleteEducation")
    public String deleteEducation(@ModelAttribute("profile") SeekerProfile profile, Model model, @RequestParam String id){
        model.addAttribute("seekerProfileInfo", educationService.getEducationDetailsById(Integer.parseInt(id)).getProfile());
        educationService.deleteEducationDetails(Integer.parseInt(id));
        return "addEducations";
    }

    @PostMapping("deleteExperience")
    public String deleteExperience(@ModelAttribute("profile") SeekerProfile profile, Model model, @RequestParam String id){
        model.addAttribute("seekerProfileInfo", experienceService.getExperienceDetailsById(Integer.parseInt(id)).getProfile());
        experienceService.deleteExperienceDetails(Integer.parseInt(id));
        return "addExperiences";
    }

    ///////////////ELSE////////////////////

    @PostMapping("profile/{id}/download")
    public String downloadProfile(Model model, @PathVariable("id") int id) throws Exception {
        seekerProfileService.downloadSeekerProfile(id);
        model.addAttribute("seekerProfileInfo", seekerProfileService.getSeekerProfileById(id));
        return "recruiter/showSeekerProfile";
    }

    @PostMapping("filterResponses")
    public String filter(Model model, @RequestParam(required=false) String jobPost, @RequestParam(required = false) List<String> activityType,
                         @AuthenticationPrincipal org.springframework.security.core.userdetails.User user){
        model.addAttribute("jobPostList", jobPostService.getNamesJobPosts(user.getUsername()));
        if(activityType !=null && activityType.size()==1) {
            model.addAttribute("responsesList", jobPostActivitiesService.getFilterResponses(user.getUsername(), jobPost, activityType.get(0)));
        }
        else {
            model.addAttribute("responsesList", jobPostActivitiesService.getFilterResponses(user.getUsername(), jobPost, null));
        }
        return "recruiter/showResponses";
    }

}
