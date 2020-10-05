package com.example.madfinal.Models;

public class TechOffers {
    private String OfferID;
    private String CustomerName;
    private String TechName;
    private String Issue;
    private String skip;
    private  String Cnum;

    public TechOffers() {
    }

    public String getOfferID() {
        return OfferID;
    }

    public void setOfferID(String offerID) {
        OfferID = offerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getTechName() {
        return TechName;
    }

    public void setTechName(String techName) {
        TechName = techName;
    }

    public String getIssue() {
        return Issue;
    }

    public void setIssue(String issue) {
        Issue = issue;
    }

    public String getSkip() {
        return skip;
    }

    public void setSkip(String skip) {
        this.skip = skip;
    }

    public String getCnum() {
        return Cnum;
    }

    public void setCnum(String cnum) {
        Cnum = cnum;
    }
}
