package com.example.blood_bank.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private List<Blood> bank = new ArrayList<>();

    /**
     * @return returns current inventory of blood units in blood bank
     */
    @GetMapping("/bank")
    public List<Blood> getBlood() {
        return bank;
    }

    /**
     * function clears blood bank
     */
    @DeleteMapping("/clearbank")
    public void clearBank() {
        bank.clear();
    }

    /**
     * adds unit of blood to bank
     * 
     * @param blood
     * @return
     */
    @PostMapping("/addblood")
    public ResponseEntity<Blood> addBlood(@Valid @RequestBody Blood blood) {

        bank.add(blood);
        return ResponseEntity.ok(blood);
    }

    /**
     * removes blood from blood bank
     * 
     * @param bloodType
     * @return returns list of removed blood types from blood bank
     */
    @DeleteMapping("/removeblood/{bloodtype}")
    public Blood[] removeBlood(@PathVariable("bloodtype") String bloodType) {

        List<Blood> removedBloodTypes = new ArrayList<>();

        for (Blood bloodUnit : bank) {

            if (bloodUnit.getBloodType().equals(bloodType)) {
                removedBloodTypes.add(bloodUnit);
            }
        }

        bank.removeAll(removedBloodTypes);

        return removedBloodTypes.toArray(new Blood[0]);
    }
}