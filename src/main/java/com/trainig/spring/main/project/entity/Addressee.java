package com.trainig.spring.main.project.entity;

public class Addressee {

    private long addresseeId;
    private String addresseeName;
    private String addresseeEmail;

    public Addressee() {
    }

    public Addressee(String addresseeName, String addresseeEmail) {
        this.addresseeName = addresseeName;
        this.addresseeEmail = addresseeEmail;
    }

    public Addressee(long addresseeId, String addresseeName, String addresseeEmail) {
        this.addresseeId = addresseeId;
        this.addresseeName = addresseeName;
        this.addresseeEmail = addresseeEmail;
    }

    public long getAddresseeId() {
        return addresseeId;
    }

    public void setAddresseeId(long addresseeId) {
        this.addresseeId = addresseeId;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
    }

    public String getAddresseeEmail() {
        return addresseeEmail;
    }

    public void setAddresseeEmail(String addresseeEmail) {
        this.addresseeEmail = addresseeEmail;
    }

}
