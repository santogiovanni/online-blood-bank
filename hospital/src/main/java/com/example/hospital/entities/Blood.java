package com.example.hospital.entities;

public class Blood {

    private String bloodType;
    private Integer patientID;
    private Integer donationCenterID;

    public Blood() {

    }

    public Blood(String bloodType, Integer patientID, Integer donationCenterID) {
        this.bloodType = bloodType;
        this.patientID = patientID;
        this.donationCenterID = donationCenterID;
    }

    public String getBloodType() {
        return bloodType;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public Integer getDonationCenterID() {
        return donationCenterID;
    }
}
