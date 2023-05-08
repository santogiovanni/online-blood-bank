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
     * 
     * @return
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
     * 
     * @param bloodType
     * @return
     */
    @GetMapping("/removeblood/{bloodtype}")
    public ResponseEntity<Blood> removeBlood(@PathVariable(value = "bloodtype") String bloodType) {

        for (Blood bloodUnit : bank) {

            if (bloodUnit.getBloodType().equals(bloodType)) {

                bank.remove(bloodUnit);
                return ResponseEntity.ok(bloodUnit);

            }
        }
        return ResponseEntity.notFound().build();
    }

}