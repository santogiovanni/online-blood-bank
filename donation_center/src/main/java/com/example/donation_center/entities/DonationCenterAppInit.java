package com.example.blood_donation_microservice.entities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.blood_donation_microservice.dao.DonationCenterRepository;

@Component
public class DonationCenterAppInit implements CommandLineRunner{

    private DonationCenterRepository repository;

    @Autowired
    public DonationCenterAppInit(DonationCenterRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        repository.saveAll(

            List.of(new DonationCenter("Stephen", "AB+", )))

            .forEach(System.out::println);
    }
    
}
