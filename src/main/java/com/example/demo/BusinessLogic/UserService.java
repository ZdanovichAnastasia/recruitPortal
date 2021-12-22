package com.example.demo.BusinessLogic;

import com.example.demo.BusinessLogic.Models.IUserService;
import com.example.demo.DataAccess.Entity.User;
import com.example.demo.DataAccess.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRole(List<String> role){
        if(role.size()==1) {
            return userRepository.getRecruiterUsers();
        }else
            return getAllUsers();
    }

    public User getUser(int id){
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByUserName(String userName){
        return userRepository.getUserByName(userName);
    }


    @Override
    public void deleteUser(int id) {
        userRepository.delete(getUser(id));
    }

    @Override
    public List<String> getNamesUser() {
        return userRepository.getUserNames();
    }
}
