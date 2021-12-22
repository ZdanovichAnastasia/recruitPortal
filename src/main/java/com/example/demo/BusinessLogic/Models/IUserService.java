package com.example.demo.BusinessLogic.Models;


import com.example.demo.DataAccess.Entity.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    List<User> getUsersByRole(List<String> role);
    List<String> getNamesUser();
    User getUser(int id);
    User getUserByUserName(String userName);
    //List<String> getUserSeekerProfilesNames(String login);
   // List<String> getUserJobPostsNames(String login);

    void saveUser(User user);
    void deleteUser(int id);
    //void joinToCompany(CompanyDTO company, int id);
    //void leaveCompany(int id);

    //List<JobPostActivityDTO> getUserActivity(int id);
}
