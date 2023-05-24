package com.example.hospital.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    private List<Blood> hospitalInventory = new ArrayList<>();

    /**
     * @return returns current inventory of hospital blood units
     */
    @GetMapping("/hospital")
    public List<Blood> getBlood() {
        return hospitalInventory;
    }

    /**
     * pulls units of blood by bloodtype from blood bank to hospital
     * 
     * @param blood unit of blood via bloodType
     * @return
     */
    @DeleteMapping(value = "/pullBlood")
    public ResponseEntity<String> pullBloodType(@RequestParam("bloodtype") String bloodType) {

        Blood[] pulledBlood = pullBlood(bloodType);

        if (pulledBlood == null || pulledBlood.length == 0) {
            return ResponseEntity.badRequest()
                    .body("There is no blood of " + bloodType + " blood type available in this blood bank.");
        }

        hospitalInventory.addAll(Arrays.asList(pulledBlood));

        return ResponseEntity.ok(bloodType + " blood type has been pulled from blood bank to hospital.");
    }

    private static String getEnv(String variableName, String defaultValue) {
        String value = System.getenv(variableName);
        return (value == null) ? defaultValue : value;
    }

    /**
     * pulls units of blood from blood bank to hospital via exchange rest template
     * 
     * @param blood unit of blood via bloodType
     * @return
     */
    private Blood[] pullBlood(String bloodType) {

        String host = getEnv("BLOOD_BANK_SERVICE_HOST", "localhost");
        String port = System.getProperty("BLOOD_BANK_PORT", "8081");
        String url = "http://" + host + ":" + port + "/removeblood/" + bloodType;
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Blood[]> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Blood[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            }
        } catch (RestClientException e) {
            // Handle any exceptions or error responses from the blood bank service
            e.printStackTrace();
        }

        return null; // Blood not found or request failed
    }

}