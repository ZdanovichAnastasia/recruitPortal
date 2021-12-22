package com.example.demo.DataAccess.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skill_set")
public class SkillSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skill_set_name")
    private String skillSetName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillSet")
    @JsonIgnoreProperties("skillSet")
    private List<JobPostSkillSet> skills=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seekerSkillSet")
    @JsonIgnoreProperties("seekerSkillSet")
    private List<SeekerSkillSet> seekerSkills=new ArrayList<>();

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillSetName() {
        return this.skillSetName;
    }

    public void setSkillSetName(String skillSetName) {
        this.skillSetName = skillSetName;
    }

    public List<JobPostSkillSet> getSkills() {
        return skills;
    }

    public void setSkills(List<JobPostSkillSet> skills) {
        this.skills = skills;
    }

    public List<SeekerSkillSet> getSeekerSkills() {
        return seekerSkills;
    }

    public void setSeekerSkills(List<SeekerSkillSet> seekerSkills) {
        this.seekerSkills = seekerSkills;
    }
}
