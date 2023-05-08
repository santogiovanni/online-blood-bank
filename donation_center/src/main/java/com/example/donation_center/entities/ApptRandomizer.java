package com.example.donation_center.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ApptRandomizer {

    private static String[] BLOOD_TYPES = { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };
    private static String[] FIRST_NAMES = { "Adam", "Benjamin", "Charles", "David", "Edward", "Frank", "George",
            "Henry", "Isaac", "James", "Kevin", "Alice", "Bethany", "Catherine",
            "Danielle", "Emily", "Fiona", "Grace", "Hannah", "Isabel", "Julia", "Kate" };
    private static String[] LAST_NAMES = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller",
            "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White",
            "Harris", "Martin", "Thompson", "Young", "Allen", "King", "Wright", "Scott",
            "Green", "Baker", "Adams", "Nelson", "Carter", "Mitchell", "Perez" };

    /**
     * 
     * @return
     */
    private static String generateRandomDate() {
        int year = 2023;
        int month = (int) (Math.random() * 12) + 1;
        int day = (int) (Math.random() * 28) + 1;
        return "" + month + "-" + day + "-" + year + "";

    }

    /**
     * 
     * @return
     */
    private static String generateRandomTime() {

        Random rand = new Random();
        String[] amOrPm = { "am", "pm" };

        int hour = (int) (Math.random() * 24);
        int minute = (int) (Math.random() * 60);
        hour = Math.floorMod(hour, 12);

        if (hour == 0)
            hour = 12;

        return "" + hour + ":" + (String.format("%02d", minute)) + " " + amOrPm[rand.nextInt(amOrPm.length)];

    }

    /**
     * 
     * @return
     */
    public static String generateName() {

        Random rand = new Random();
        return "" + FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)] + " " + LAST_NAMES[rand.nextInt(LAST_NAMES.length)]
                + "";
    }

    /**
     * 
     * @return
     */
    public static List<Schedule> generateRandomAppts(int numAppts) {

        List<Schedule> appts = new ArrayList<>();

        // Generate random blood donors with name, blood type, and appointment date
        for (int i = 1; i <= numAppts; i++) {
            String name = generateName();
            String bloodType = BLOOD_TYPES[(int) (Math.random() * BLOOD_TYPES.length)];
            String apptTime = generateRandomTime();
            String apptDate = generateRandomDate();

            appts.add(new Schedule(i, name, bloodType, apptTime, apptDate));
        }

        return appts;
    }
}