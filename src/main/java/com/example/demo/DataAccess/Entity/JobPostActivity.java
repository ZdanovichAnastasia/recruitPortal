package com.example.demo.DataAccess.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "job_post_activity")
public class JobPostActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "apply_date")
    private java.sql.Date applyDate;

    @Column(name = "activity_type")
    private String type;

    @JsonIgnoreProperties("activities")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "seeker_profile_id")
    private SeekerProfile profile;

    @JsonIgnoreProperties("activities")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "job_post_id")
    private JobPost post;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.sql.Date getApplyDate() {
        return this.applyDate;
    }

    public void setApplyDate(java.sql.Date applyDate) {
        this.applyDate = applyDate;
    }

    public SeekerProfile getProfile() {
        return profile;
    }

    public void setProfile(SeekerProfile profile) {
        this.profile = profile;
    }

    public JobPost getPost() {
        return post;
    }

    public void setPost(JobPost post) {
        this.post = post;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
