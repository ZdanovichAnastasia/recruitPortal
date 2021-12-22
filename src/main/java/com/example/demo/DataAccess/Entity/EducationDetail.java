package com.example.demo.DataAccess.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "education_detail")
public class EducationDetail {

    public EducationDetail() {
    }

    public EducationDetail(Integer admissionYear, Integer graduationYear, String university, String speciality) {
        this.graduationYear = graduationYear;
        this.speciality = speciality;
        this.university = university;
        this.admissionYear = admissionYear;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "university")
    private String university;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    @Column(name="admission_year")
    private Integer admissionYear;

    //@JsonIgnoreProperties("educations")
    @ManyToOne()
    @JoinColumn(name = "seeker_profile_id")
    private SeekerProfile profile;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGraduationYear() {
        return this.graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getSpeciality() {
        return this.speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getUniversity() {
        return this.university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public SeekerProfile getProfile() {
        return profile;
    }

    public void setProfile(SeekerProfile profile) {
        this.profile = profile;
    }

    public Integer getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(Integer admissionYear) {
        this.admissionYear = admissionYear;
    }

}
