package com.example.demo.DataAccess.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_type")
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "job_type_name")
    private String jobTypeName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobType")
    @JsonIgnoreProperties("jobType")
    private List<JobPost> posts=new ArrayList<>();

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTypeName() {
        return this.jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }

    public List<JobPost> getPosts() {
        return posts;
    }

    public void setPosts(List<JobPost> posts) {
        this.posts = posts;
    }
}
