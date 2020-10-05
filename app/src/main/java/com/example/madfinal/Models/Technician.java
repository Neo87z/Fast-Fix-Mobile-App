package com.example.madfinal.Models;

public class Technician {
    private String FirstName;
    private String LastName;
    private String Password;
    private String ConPassword;
    private String Email;
    private String Phone;
    private String DisplayName;
    private  String Dob;
    private String About;
    private  int price;
    private String Android="False";
    private  String IOS="False";
    private  String Camera="False";
    private  String Laptops="False";
    private  String Television="False";
    private  String Other="False";

    public String getAndroid() {
        return Android;
    }

    public void setAndroid(String android) {
        Android = android;
    }

    public String getIOS() {
        return IOS;
    }

    public void setIOS(String IOS) {
        this.IOS = IOS;
    }

    public String getCamera() {
        return Camera;
    }

    public void setCamera(String camera) {
        Camera = camera;
    }

    public String getLaptops() {
        return Laptops;
    }

    public void setLaptops(String laptops) {
        Laptops = laptops;
    }

    public String getTelevision() {
        return Television;
    }

    public void setTelevision(String television) {
        Television = television;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Technician() {
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConPassword() {
        return ConPassword;
    }

    public void setConPassword(String conPassword) {
        ConPassword = conPassword;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }
}
