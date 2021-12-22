package com.example.demo.DataAccess.Repository;


import com.example.demo.DataAccess.Entity.SeekerSkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeekerSkillSetRepository extends JpaRepository<SeekerSkillSet,Integer> {
}
