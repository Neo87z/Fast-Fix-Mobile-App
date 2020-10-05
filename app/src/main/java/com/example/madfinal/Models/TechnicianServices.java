package com.example.madfinal.Models;

public class TechnicianServices {
    private String techncianName;
    private String serviceID;
    private String servicetyep;
    private String aboutServivce;
    private String estimatedDelivery;
    private String deliverydate;
    private String skip;
    private  String price;

    public TechnicianServices() {
    }

    public TechnicianServices(String techncianName, String serviceID, String servicetyep, String aboutServivce, String estimatedDelivery, String deliverydate, String skip, String price) {
        this.techncianName = techncianName;
        this.serviceID = serviceID;
        this.servicetyep = servicetyep;
        this.aboutServivce = aboutServivce;
        this.estimatedDelivery = estimatedDelivery;
        this.deliverydate = deliverydate;
        this.skip = skip;
        this.price = price;
    }

    public String getTechncianName() {
        return techncianName;
    }

    public void setTechncianName(String techncianName) {
        this.techncianName = techncianName;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServicetyep() {
        return servicetyep;
    }

    public void setServicetyep(String servicetyep) {
        this.servicetyep = servicetyep;
    }

    public String getAboutServivce() {
        return aboutServivce;
    }

    public void setAboutServivce(String aboutServivce) {
        this.aboutServivce = aboutServivce;
    }

    public String getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public void setEstimatedDelivery(String estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }

    public String getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(String deliverydate) {
        this.deliverydate = deliverydate;
    }

    public String getSkip() {
        return skip;
    }

    public void setSkip(String skip) {
        this.skip = skip;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
