package com.example.demo.DataAccess.Entity;

import com.example.demo.DataAccess.Entity.JobPostActivity;
import com.example.demo.DataAccess.Entity.JobPostSkillSet;
import com.example.demo.DataAccess.Entity.JobType;
import com.example.demo.DataAccess.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "job_post")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "created_date")
    private java.sql.Date createdDate;

    @Column(name = "salary_high")
    private Integer salaryHigh;

    @Column(name = "salary_low")
    private Integer salaryLow;

    @Column(name = "compensation_term")
    private java.sql.Date compensationTerm;

    @JsonIgnoreProperties("posts")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "job_type_id")
    private JobType jobType;

    @JsonIgnoreProperties("posts")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "job_recruiter")
    private User user;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "post")
    @JsonIgnoreProperties("post")
    private List<JobPostActivity> activities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    @JsonIgnoreProperties("post")
    private List<JobPostSkillSet> skills = new ArrayList<>();

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(java.sql.Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getSalaryHigh() {
        return salaryHigh;
    }

    public void setSalaryHigh(Integer salaryHigh) {
        this.salaryHigh = salaryHigh;
    }

    public Integer getSalaryLow() {
        return salaryLow;
    }

    public void setSalaryLow(Integer salaryLow) {
        this.salaryLow = salaryLow;
    }

    public java.sql.Date getCompensationTerm() {
        return compensationTerm;
    }

    public void setCompensationTerm(java.sql.Date compensationTerm) {
        this.compensationTerm = compensationTerm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public List<JobPostActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<JobPostActivity> activities) {
        this.activities = activities;
    }

    public List<JobPostSkillSet> getSkills() {
        return skills;
    }

    public void setSkills(List<JobPostSkillSet> skills) {
        this.skills = skills;
    }
}
