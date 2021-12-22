package com.example.demo.DataAccess.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seeker_profile")
public class SeekerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "age")
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    @JsonIgnoreProperties("profile")
    private List<EducationDetail> educations=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    @JsonIgnoreProperties("profile")
    private List<ExperienceDetail> experiences=new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    @JsonIgnoreProperties("profile")
    private List<SeekerSkillSet> seekerSkills = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    @JsonIgnoreProperties("profile")
    private List<JobPostActivity> activities=new ArrayList<>();

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<EducationDetail> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDetail> educations) {
        this.educations = educations;
    }

    public List<ExperienceDetail> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceDetail> experiences) {
        this.experiences = experiences;
    }

    public List<SeekerSkillSet> getSeekerSkills() {
        return seekerSkills;
    }

    public void setSeekerSkills(List<SeekerSkillSet> seekerSkills) {
        this.seekerSkills = seekerSkills;
    }

    public List<JobPostActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<JobPostActivity> activities) {
        this.activities = activities;
    }
}
