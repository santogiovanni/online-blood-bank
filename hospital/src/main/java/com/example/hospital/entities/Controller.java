package com.example.hospital.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    private List<Blood> hospitalInventory = new ArrayList<>();

    /**
     * 
     * @return
     */
    @GetMapping("/hospital")
    public List<Blood> getBlood() {
        return hospitalInventory;
    }

    /**
     * 
     * @return
     */
    @DeleteMapping(value = "/pullblood/{bloodtype}")
    public ResponseEntity<String> pullBloodType(@PathVariable(value = "bloodtype") String bloodType) {

        Blood blood = pullBlood(bloodType);

        if (blood == null) {
            return ResponseEntity.badRequest()
                    .body("There is no blood of " + bloodType + " blood type available in this donation center.");
        }

        hospitalInventory.add(blood);
        return ResponseEntity.ok(blood.getBloodType() + " blood type has been pulled from blood bank to hospital.");
    }

    /**
     * pushes unit of blood to blood bank after an appointment is made
     * To-Do:
     * 
     * @param blood unit of blood
     */
    private Blood pullBlood(String bloodType) {

        String host = System.getProperty("BLOOD_BANK_SERVICE", "localhost");
        String port = System.getProperty("BLOOD_BANK_PORT", "8081");
        String url = "http://" + host + ":" + port + "/removeblood/" + bloodType;
        RestTemplate restTemplate = new RestTemplate();
        Blood blood = null;
        try {

            blood = restTemplate.getForObject(url, Blood.class);
        } catch (final HttpClientErrorException e) {

            System.out.println("Blood of " + bloodType + " doesn't exist in blood bank; returning null.");
        }

        return blood;
    }
}