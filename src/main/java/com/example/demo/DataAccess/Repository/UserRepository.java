package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User  u where u.userType='рекрутер'")
    List<User> getRecruiterUsers();

    @Query("select u from User  u where u.login=?1")
    User getUserByName(String login);

    @Query("select distinct b.login from User b")
    List<String> getUserNames();
}
