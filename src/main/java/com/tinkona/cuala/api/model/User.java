package com.tinkona.cuala.api.model;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public class User {
    private int id;
    private String fbId;
    private String name;
    private String email;
    private String matricNo;
    private String phone;
    private int gradYear;
    private String course;
    private String occupation;
    private String regNo;

    public User() {
    }

    public User(int id, String fbId, String name, String email, String matricNo,String regNo, String phone, int gradYear, String course, String occupation) {
        this.id = id;
        this.fbId = fbId;
        this.name = name;
        this.email = email;
        this.matricNo = matricNo;
        this.regNo = regNo;
        this.phone = phone;
        this.gradYear = gradYear;
        this.course = course;
        this.occupation = occupation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGradYear() {
        return gradYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
}
