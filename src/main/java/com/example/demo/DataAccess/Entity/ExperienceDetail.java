package com.example.demo.DataAccess.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "experience_detail")
public class ExperienceDetail {

    public ExperienceDetail() { }

    public ExperienceDetail(String timeOfExperience, String jobTitle, String companyName) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.timeOfExperience = timeOfExperience;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "time_of_experience")
    private String timeOfExperience;

    @JsonIgnoreProperties("experiences")
    @ManyToOne()
    @JoinColumn(name = "seeker_profile_id")
    private SeekerProfile profile;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getTimeOfExperience() {
        return timeOfExperience;
    }

    public void setTimeOfExperience(String timeOfExperience) {
        this.timeOfExperience = timeOfExperience;
    }

    public SeekerProfile getProfile() {
        return profile;
    }

    public void setProfile(SeekerProfile profile) {
        this.profile = profile;
    }
}
