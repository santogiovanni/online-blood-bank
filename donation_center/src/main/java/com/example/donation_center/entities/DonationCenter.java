package com.example.blood_donation_microservice.entities;

// import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DonationCenter {
    
    // add a comment about age, weight, email
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String bloodtype;
    private LocalDateTime appt;
    // private LocalDate appt;
    // private String time;
    // private String date;

    // default constructor
    public DonationCenter () {
    }

    public DonationCenter (Integer id, String name, String bloodtype, LocalDateTime appt){
        this.id = id;
        this.name = name;
        this.bloodtype = bloodtype;
        this.appt = appt;
    }

    public DonationCenter (String name, String bloodtype, LocalDateTime appt){
        this.name = name;
        this.bloodtype = bloodtype;
        this.appt = appt;
    }

    // getter and setter methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getBloodType(){
        return bloodtype;
    }

    public void setBloodType(String bloodtype){
        this.bloodtype = bloodtype;
    }

    public LocalDateTime getAppt(){
        return appt;
    }

    public void setAppt(LocalDateTime appt){
        this.appt = appt;
    }

    // public String getTime(){
    //     return time;
    // }

    // public void setTime(String time){
    //     this.time = time;
    // }

    // public String getDate(){
    //     return date;
    // }

    // public void setDate(String date){
    //     this.date = date;
    // }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonationCenter donor = (DonationCenter) o;
        return Objects.equals(id, donor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Blood Donor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bloodtype='" + bloodtype + '\'' +
                ", appt='" + appt + '\'' +
                '}';
    }
}
