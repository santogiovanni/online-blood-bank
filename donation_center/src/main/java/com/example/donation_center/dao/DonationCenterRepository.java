package com.example.blood_donation_microservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blood_donation_microservice.entities.DonationCenter;

public interface DonationCenterRepository extends JpaRepository <DonationCenter, Integer>{

    
    
}
