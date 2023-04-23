package com.example.blood_donation_microservice.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blood_donation_microservice.dao.DonationCenterRepository;

@RestController
public class DonationCenterController {
    
    private DonationCenterRepository repository;

    @Autowired
    public DonationCenterController(DonationCenterRepository repository){
        this.repository = repository;
    }

    // build get blood donation REST API
    @GetMapping("/blood-donations")
    public List<DonationCenter> getAllAppointments(){
        return repository.findAll();
    }

    // retrieve info about a specific blood donation
    @GetMapping("/blood-donations/{Id}")
    public Optional<DonationCenter> getAppointment(@PathVariable("Id") int id){
        return repository.findById(id);
    }

    // @GetMapping("/remove-first-donation")
    // public List<DonationCenter> removeAppointment(){
    //     repository.deleteById(1);
    //     return repository.findAll();
    // }

    // build delete blood donation REST API
    @DeleteMapping("/remove-donation/{id}")
    public void deleteAppointment(@PathVariable("id") int id) {
        repository.deleteById(id);
    }

    // build create blood donation REST API
    // @RequestBody is used to convert the body of the HTTP request to the java class object
    @PostMapping("/create-donation")
    public ResponseEntity<DonationCenter> createAppointment(@Valid @RequestBody DonationCenter donor){
        repository.save(donor);
        return ResponseEntity.ok(donor);
    }

    // return error if there is not enough blood
    

    // build update blood donation REST API
    @PutMapping("/update-donation/{id}")
    public ResponseEntity<DonationCenter> updateAppointment(@PathVariable("id") int id, @RequestBody DonationCenter donorDetails){
        
        DonationCenter updateDonor = repository.findById(id)
            .orElseThrow();
    
        updateDonor.setName(donorDetails.getName());
        updateDonor.setBloodType(donorDetails.getBloodType());
        updateDonor.setAppt(donorDetails.getAppt());

        // updateDonor.setTime(donorDetails.getTime());
        // updateDonor.setDate(donorDetails.getDate());

        DonationCenter updatedDonor = repository.save(updateDonor);
        return ResponseEntity.ok(updatedDonor);
    }
}
