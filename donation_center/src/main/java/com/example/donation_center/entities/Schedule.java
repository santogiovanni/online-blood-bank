package com.example.donation_center.entities;

import java.util.Objects;

public class Schedule {

    // add a comment about age, weight, email
    private Integer id;
    private String name;
    private String bloodtype;
    private String time;
    private String date;

    // default constructor
    public Schedule() {
    }

    public Schedule(Integer id, String name, String bloodtype, String time, String date) {
        this.id = id;
        this.name = name;
        this.bloodtype = bloodtype;
        this.time = time;
        this.date = date;
    }

    public Schedule(String name, String bloodtype, String time, String date) {
        this.name = name;
        this.bloodtype = bloodtype;
        this.time = time;
        this.date = date;
    }

    // getter and setter methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodType() {
        return bloodtype;
    }

    public void setBloodType(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Schedule donor = (Schedule) o;
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
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Schedule orElseThrow() {
        return null;
    }
}