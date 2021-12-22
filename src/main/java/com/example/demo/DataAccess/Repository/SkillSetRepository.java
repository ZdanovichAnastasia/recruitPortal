package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Entity.SkillSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillSetRepository extends JpaRepository<SkillSet, Integer> {
}
