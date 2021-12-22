package com.example.demo.Controller;


import com.example.demo.BusinessLogic.JobPostActivitiesService;
import com.example.demo.BusinessLogic.UserService;
import com.example.demo.DataAccess.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UsersController {
    @Autowired
    UserService userService;
    @Autowired
    JobPostActivitiesService jobPostActivitiesService;

    ///////////////SHOW////////////////////

    @GetMapping({"/admin/showAllUsers"})
    public String showAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        userList.remove(0);
        model.addAttribute("usersList", userList);
        return "admin/showAllUsers";
    }

    @GetMapping({"/admin/userProfile"})
    public String showUserProfile(Model model, @RequestParam String idUser){
        model.addAttribute("userInfo", userService.getUser(Integer.parseInt(idUser)));
        return "admin/userProfile";
    }

    @GetMapping({"/recruiter/recruiterProfile"})
    public String showRecruiterProfile(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user){//}, @RequestParam String idUser){
        model.addAttribute("userInfo", userService.getUserByUserName(user.getUsername()));
        return "recruiter/recruiterProfile";
    }

    ////////////ADD//EDIT//////////////////

    @GetMapping("user/{id}/edit")
    public String showEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/editUser";
    }

    @PostMapping("user/{id}")
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors())
            return "admin/editUser";
        user.setId(id);
        userService.saveUser(user);
        User userS = userService.getUser(id);
        model.addAttribute("userInfo", userS);
        return "admin/userProfile";
    }

    @GetMapping("/admin/addUser")
    public String showAddForm(@ModelAttribute("user") User user){
        return "admin/addUser";
    }

    @PostMapping("/newUser")
    public String addUser(@ModelAttribute("user")@Valid User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "admin/addUser";
        }
        user.setUserType("рекрутер");
        userService.saveUser(user);
        List<User> userList = userService.getAllUsers();
        userList.remove(0);
        model.addAttribute("usersList", userList);
        return "admin/showAllUsers";
    }

    //////////////DELTE////////////////////

    @PostMapping("deleteUser")
    public String deleteUser(Model model, @RequestParam String id){
        userService.deleteUser(Integer.parseInt(id));
        List<User> userList = userService.getAllUsers();
        userList.remove(0);
        model.addAttribute("usersList", userList);
        return "admin/showAllUsers";
    }
    ///////////////ELSE////////////////////

    @GetMapping({"/recruiter/showRecruiterJobPosts"})
    public String showRecruiterJobPosts(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user){
        model.addAttribute("userInfo", userService.getUserByUserName(user.getUsername()));
        return "recruiter/showRecruiterJobPosts";
    }
}
