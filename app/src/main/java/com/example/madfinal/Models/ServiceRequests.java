package com.example.madfinal.Models;

public class ServiceRequests {
    private  String requestID;
    private  String title;
    private  String description;
    private  String date;
    private  String user;
    private  String phone;
    private  String skipval;
    private String assingedto;

    public ServiceRequests() {
    }

    public ServiceRequests(String requestID, String title, String description, String date, String user, String phone, String skipval, String assingedto) {
        this.requestID = requestID;
        this.title = title;
        this.description = description;
        this.date = date;
        this.user = user;
        this.phone = phone;
        this.skipval = skipval;
        this.assingedto = assingedto;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkipval() {
        return skipval;
    }

    public void setSkipval(String skipval) {
        this.skipval = skipval;
    }

    public String getAssingedto() {
        return assingedto;
    }

    public void setAssingedto(String assingedto) {
        this.assingedto = assingedto;
    }
}
