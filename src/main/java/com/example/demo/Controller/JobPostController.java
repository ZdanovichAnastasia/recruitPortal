package com.example.demo.Controller;

import com.example.demo.BusinessLogic.JobPostService;
import com.example.demo.BusinessLogic.JobTypeService;
import com.example.demo.BusinessLogic.UserService;
import com.example.demo.DataAccess.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;


@Controller
public class JobPostController{
    @Autowired
    JobPostService jobPostService;
    @Autowired
    JobTypeService jobTypeService;
    @Autowired
    UserService userService;

    private void fill(Model model){
        model.addAttribute("nul", null);
        model.addAttribute("types", jobTypeService.getAllJobTypes());
        List<User> users = userService.getAllUsers();
        users.remove(0);
        model.addAttribute("recruiters", users);
    }

    @GetMapping({"/"})
    public String showAllJobPosts(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        model.addAttribute("jobTypeList", jobTypeService.getNamesJobTypes());
        model.addAttribute("jobPostsList", jobPostService.getAllJobPosts());
        return "showAllJobPosts";
    }
    ///////////////SHOW////////////////////

    @GetMapping({"/showJobPost"})
    public String showJobPost(@ModelAttribute("post") @Valid JobPost post, Model model, @RequestParam String idJobPost) {
        model.addAttribute("jobPostInfo", jobPostService.getJobPostById(Integer.parseInt(idJobPost)));
        return "showJobPost";
    }

    ////////////ADD//EDIT//////////////////

    @GetMapping("/admin/addJobPost")
    public String showAddForm(@ModelAttribute("post") JobPost post, Model model){
        fill(model);
        return "admin/addJobPost";
    }

    @PostMapping("/newPost")
    public String addJobPost(@ModelAttribute("post")@Valid JobPost post, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            fill(model);
            return "admin/addJobPost";
        }
        post.setCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        post.setJobType(jobTypeService.getJobTypeById(post.getJobType().getId()));
        post.setUser(userService.getUser(post.getUser().getId()));
        jobPostService.saveJobPost(post);

        JobPost jobPost = jobPostService.getAllJobPosts().get(jobPostService.getAllJobPosts().size()-1);
        model.addAttribute("jobPostInfo", jobPost);
        return "admin/addJobPostSkills";
    }

    @PostMapping("addSkill/{id}")
    public String addSkill(@ModelAttribute("post") @Valid JobPost post, BindingResult bindingResult,
                           @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("jobPostInfo", jobPostService.getJobPostById(id));
            return "admin/addJobPostSkills";
        }
        JobPostSkillSet jobPostSkillSet = new JobPostSkillSet();
        jobPostSkillSet.setPost(jobPostService.getJobPostById(id));

        jobPostSkillSet.setSkillLevel(post.getSkills().get(0).getSkillLevel());
        jobPostSkillSet.setSkillSet(post.getSkills().get(0).getSkillSet());
        jobPostService.saveJobPostSkills(jobPostSkillSet);

        model.addAttribute("jobPostInfo", jobPostService.getJobPostById(id));
        return "admin/addJobPostSkills";
    }


    @GetMapping("post/{id}/edit")
    public String showEditForm(Model model, @PathVariable("id") int id) {
        fill(model);
        model.addAttribute("post", jobPostService.getJobPostById(id));
        return "admin/editJobPost";
    }

    @PostMapping("post/{id}")
    public String editJobPost(@ModelAttribute("post") @Valid JobPost post, BindingResult bindingResult,
                         @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            fill(model);
            model.addAttribute("post", jobPostService.getJobPostById(id));
            return "admin/editJobPost";
        }
        post.setId(id);
        post.setSkills(jobPostService.getJobPostById(id).getSkills());
        post.setJobType(jobTypeService.getJobTypeById(post.getJobType().getId()));
        post.setUser(userService.getUser(post.getUser().getId()));
        jobPostService.saveJobPost(post);

        model.addAttribute("jobPostInfo", jobPostService.getJobPostById(id));
        return "admin/addJobPostSkills";
    }

    //////////////DELETE////////////////////

    @PostMapping("deleteJobPost")
    public String deleteJobPost(Model model, @RequestParam String id){
        jobPostService.deleteJobPost(Integer.parseInt(id));
        model.addAttribute("jobTypeList", jobTypeService.getNamesJobTypes());
        model.addAttribute("jobPostsList", jobPostService.getAllJobPosts());
        return "showAllJobPosts";
    }

    @PostMapping("deleteJobPostSkill")
    public String deleteJobPostSkill(@ModelAttribute("post") @Valid JobPost post, Model model, @RequestParam String id){
        JobPostSkillSet jobPostSkillSet = jobPostService.getJobPostSkillSetById(Integer.parseInt(id));
        model.addAttribute("jobPostInfo", jobPostSkillSet.getPost());

        jobPostService.deleteJobPostSkill(Integer.parseInt(id));
        return "admin/addJobPostSkills";
    }

    ///////////////ELSE////////////////////

    @PostMapping("filterJobPosts")
    public String filter(Model model, @RequestParam(required=false) String position, @RequestParam(required=false) String jobType){
        model.addAttribute("jobTypeList", jobTypeService.getNamesJobTypes());
        model.addAttribute("jobPostsList", jobPostService.getFilterJobPosts(position, jobType));
        return "showAllJobPosts";
    }

    @PostMapping("sortJobPosts")
    public String sort(Model model, @RequestParam(required = false)List<String> sortOption){
        if(sortOption==null || sortOption.isEmpty())
            model.addAttribute("jobPostsList", jobPostService.getAllJobPosts());
        else if(sortOption.get(0).equals("дате окончания набора"))
            model.addAttribute("jobPostsList", jobPostService.getSortedByDateJobPosts());
        else
            model.addAttribute("jobPostsList", jobPostService.getSortedByResponsesJobPosts());
        model.addAttribute("jobTypeList", jobTypeService.getNamesJobTypes());
        return "showAllJobPosts";
    }

}
