package com.example.blood_donation_microservice.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DonationRandomizer {
    
    private static final String[]BLOOD_TYPES = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private static LocalDateTime generateRandomAppointment(){

        int year = 2023;
        int month = (int) (Math.random() * 12) + 1;
        int day = (int) (Math.random() * 28) + 1;
        int hour = (int) (Math.random() * 24);
        int minute = (int) (Math.random() * 60);
        int second = (int) (Math.random() * 60);
        return LocalDateTime.of(year, month, day, hour, minute, second);
    
    }


    public static List<DonationCenter> generateRandomBloodDonors(int numDonors) {
        
        List <DonationCenter> donors = new ArrayList<>();

        // Generate random blood donors with name, blood type, and appointment date
        for (int i = 0; i < numDonors; i++) {
            String name = "Donor " + (i + 1);
            String bloodType = BLOOD_TYPES[(int) (Math.random() * BLOOD_TYPES.length)];
            LocalDateTime appointmentDateTime = generateRandomAppointment();
            donors.add(new DonationCenter(name, bloodType, appointmentDateTime));
        }

        // Shuffle the list to randomize the order of donors
        Collections.shuffle(donors);

        return donors;
    }
}



// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
// LocalDate date = LocalDate.parse("29-Mar-2019", formatter);

public static void main(String[] args) {

    List<DonationCenter> donors = generateRandomBloodDonors(10);

    // Print out the list of donors with their name, blood type, and appointment date
    for (DonationCenter donor : donors) {
        System.out.println(donor.getName() + " (" + donor.getBloodType() + ") - Appointment Date: " + donor.getAppt().format(DATE_FORMAT));
    }
}

