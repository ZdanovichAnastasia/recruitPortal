package com.example.demo.DataAccess.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "job_post_skill_set")
public class JobPostSkillSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skill_level")
    private String skillLevel;


    @JsonIgnoreProperties("skills")
    @ManyToOne()
    @JoinColumn(name = "job_post_id")
    private JobPost post;

    @JsonIgnoreProperties("skills")
    @ManyToOne()
    @JoinColumn(name = "skill_set_id")
    private SkillSet skillSet;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillLevel() {
        return this.skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }


    public JobPost getPost() {
        return post;
    }

    public void setPost(JobPost post) {
        this.post = post;
    }

    public SkillSet getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(SkillSet skillSet) {
        this.skillSet = skillSet;
    }
}
