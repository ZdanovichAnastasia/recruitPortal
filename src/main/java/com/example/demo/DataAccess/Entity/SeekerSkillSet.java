package com.example.demo.DataAccess.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "seeker_skill_set")
public class SeekerSkillSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skill_level")
    private String skillLevel;

    @JsonIgnoreProperties("seekerSkills")
    @ManyToOne()
    @JoinColumn(name = "seeker_profile_id")
    private SeekerProfile profile;


    @JsonIgnoreProperties("seekerSkills")
    @ManyToOne()
    @JoinColumn(name = "skill_set_id")
    private SkillSet seekerSkillSet;

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

    public SeekerProfile getProfile() {
        return profile;
    }

    public void setProfile(SeekerProfile profile) {
        this.profile = profile;
    }

    public SkillSet getSeekerSkillSet() {
        return seekerSkillSet;
    }

    public void setSeekerSkillSet(SkillSet seekerSkillSet) {
        this.seekerSkillSet = seekerSkillSet;
    }
}
