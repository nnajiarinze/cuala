package com.tinkona.cuala.api.model;

import java.util.Date;

/**
 * Created by Nnaji.Arinze on 10/2/2016.
 */
public class Contact {
    private  int contactId;
    private String firstName;
    private String lastName;
    private String address;
    private Date dateOfBirth;
    private int age;
    private String city;
    private String lga;
    private String state;
    private String country;
    private String mobileNumber;
    private String email;

    public Contact(){
        this.contactId = 0;
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.dateOfBirth = null;
        this.age = 0;
        this.city = "";
        this.lga = "";
        this.state = "";
        this.country = "";
        this.mobileNumber = "";
        this.email = "";
    }
    public Contact(int contactId, String firstName, String lastName, String address, Date dateOfBirth, int age, String city, String lga, String state, String country, String mobileNumber, String email) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.city = city;
        this.lga = lga;
        this.state = state;
        this.country = country;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
