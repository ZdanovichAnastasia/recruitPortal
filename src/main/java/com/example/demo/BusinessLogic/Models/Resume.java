package com.example.demo.BusinessLogic.Models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Resume {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    @JacksonXmlProperty(localName = "Skills")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> skills;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Resume(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Resume(String firstName, String lastName, String phone, String email, List<String> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String serialize() throws JsonProcessingException {
        JacksonXmlModule jacksonXmlModule = new JacksonXmlModule();
        jacksonXmlModule.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(jacksonXmlModule);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return xmlMapper.writeValueAsString(this);
    }

    public String getSimpleName() throws Exception {
        if (this != null) return this.getClass().getSimpleName();
        else throw new Exception();
    }
}
